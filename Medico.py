from flask import Flask, jsonify, request
import mysql.connector
from mysql.connector import Error

app = Flask(__name__)
password = input("Ingresa la contraseña de MySQL: ")

def inicializar_base_de_datos():
    try:
        conexion = mysql.connector.connect(
            host="localhost",
            port=3306,
            user="root",
            password=password
        )

        cursor = conexion.cursor()
        cursor.execute("CREATE DATABASE IF NOT EXISTS medicos")
        cursor.execute("USE medicos")
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS medicos (
                cedula VARCHAR(20) PRIMARY KEY,
                nombre VARCHAR(100),
                especialidad VARCHAR(50),
                permisos BOOLEAN
            )
        """)

        cursor.execute("DELETE FROM medicos")
        query_insertar = "INSERT INTO medicos (cedula, nombre, especialidad, permisos) VALUES (%s, %s, %s, %s)"

        medicos = [
            ("MG01", "Dr. Juan Perez", "MEDICOGENERAL", True),
            ("MG02", "Dra. Ana Gomez", "MEDICOGENERAL", True),
            ("MG03", "Dr. Luis Garcia", "MEDICOGENERAL", False),
            ("CA01", "Dr. Roberto Sanchez", "CARDIOLOGIA", True),
            ("PS03", "Dra. Fernanda Lopez", "PSIQUIATRIA", True),
            ("ON01", "Dr. Miguel Torres", "ONCOLOGIA", True),
            ("PD01", "Dra. Sofia Martinez", "PEDIATRIA", True)
        ]

        cursor.executemany(query_insertar, medicos)
        conexion.commit()

        print("-> [SISTEMA] Base de datos e inserts de Médicos cargados correctamente.")
        
        cursor.close()
        conexion.close()

    except Error as e:
        print(f"CRITICAL: Error al inicializar la base de datos: {e}")
        exit(1)

# Configuración directa a la DB de Médicos
def get_db_connection():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password=password,
        database="medicos",
        connect_timeout=5
    )

@app.route('/validar_especialidad', methods=['GET'])
def validar_especialidad():
    cedula = request.args.get('cedula', '')
    especialidad_requerida = request.args.get('especialidad', '')
    
    conn = None
    try:
        conn = get_db_connection()
        cursor = conn.cursor(dictionary=True)
        
        # Ejecutamos la consulta
        query = "SELECT especialidad, permisos FROM medicos WHERE cedula = %s"
        cursor.execute(query, (cedula,))
        medico = cursor.fetchone()
        
        cursor.close()

        if medico:
            db_esp = str(medico.get('especialidad') or "").strip().upper()
            req_esp = str(especialidad_requerida or "").strip().upper()
            
            tiene_permiso = bool(medico.get('permisos', 0))
            especialidad_ok = (db_esp == req_esp)

            print(f"--- LOG: Cedula {cedula} ---")
            print(f"DB: {db_esp} | REQ: {req_esp} | MATCH: {especialidad_ok}")
            print(f"PERMISOS: {tiene_permiso}")

            return jsonify({
                "autorizado": bool(especialidad_ok and tiene_permiso),
                "motivo": "OK" if especialidad_ok else f"Especialidad DB '{db_esp}' no coincide con '{req_esp}'"
            }), 200
        
        return jsonify({"autorizado": False, "motivo": "Cédula no encontrada"}), 200

    except Error as e:
        print(f"DEBUG: Error de Base de Datos: {e}")
        return jsonify({"error": "Error de conexión a base de datos"}), 500
    except Exception as e:
        print(f"DEBUG: Error inesperado: {e}")
        return jsonify({"error": str(e)}), 500
    finally:
        if conn and conn.is_connected():
            conn.close()

if __name__ == "__main__":
    inicializar_base_de_datos()
    app.run(debug=False, port=5001)

