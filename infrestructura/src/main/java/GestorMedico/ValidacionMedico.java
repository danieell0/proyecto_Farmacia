/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestorMedico;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 *
 * @author Dario
 */
public class ValidacionMedico implements IValidacionMedico{
    
    @Override
    public boolean esMedicoAutorizado(String cedula, String especialidad) {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            String url = String.format("http://localhost:5001/validar/%s/%s", cedula, especialidad);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return MedicoJsonMapper.interpretarRespuesta(response.body());
            }
            
        } catch (IOException e) {
            System.err.println("Error de red " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Conexion interrupida " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return false;
    }
}
