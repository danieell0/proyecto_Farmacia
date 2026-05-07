from flask import Flask, jsonify

app = Flask(__name__)

class MedicoDTO:
    def __init__(self, cedula, nombre, especialidad, permisos):
        self.cedula = cedula
        self.nombre = nombre
        self.especialidad = especialidad
        self.permisos = permisos

# Mocks
repositorio = {
    "12345678": MedicoDTO("12345678", "Dr. Simi", "MEDICOGENERAL", True),
    "87654321": MedicoDTO("87654321", "Dr. Goku", "PEDIATRIA", True),
    "55556666": MedicoDTO("55556666", "Dr. Jorge", "MEDICOGENERAL", True)
}

# Definimos la ruta de la API
@app.route('/medico/<cedula>', methods=['GET'])
def consultar_medico(cedula):
    if cedula in repositorio:
        # .__dict__ convierte el objeto a diccionario para enviarlo como JSON
        return jsonify(repositorio[cedula].__dict__)
    else:
        return jsonify({"error": "No encontrado"}), 404
    
@app.route('/validar/<cedula>/<especialidad>', methods=['GET'])
def validar_medico(cedula, especialidad):
    medico = repositorio.get(cedula)
    
    if medico:
        # Validamos que la especialidad coincida y tenga permisos
        # .upper() es para evitar problemas de mayúsculas/minúsculas
        autorizado = (medico.especialidad == especialidad and medico.permisos)
        return jsonify({"autorizado": autorizado}), 200
    
    return jsonify({"autorizado": False}), 200 # Cambiado a 200 para que Java reciba el JSON

if __name__ == "__main__":
    # Corre el servidor en el puerto 5000
    app.run(debug=True, port=5001)
    


