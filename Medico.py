from flask import Flask, jsonify, request
import mysql.connector
from mysql.connector import Error

app = Flask(__name__)

# Configuración directa a la DB de Médicos
def get_db_connection():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="357642",
        database="medicos",
        connect_timeout=5 # Evita que Java espere eternamente si la DB no responde
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
            # Limpieza y comparación segura
            db_esp = str(medico.get('especialidad') or "").strip().upper()
            req_esp = str(especialidad_requerida or "").strip().upper()
            
            # El campo permisos en MySQL suele ser TINYINT(1)
            # Aseguramos la conversión a booleano real
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
    app.run(debug=True, port=5001)

