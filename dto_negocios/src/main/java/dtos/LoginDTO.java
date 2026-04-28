/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author Benjamin
 */
public class LoginDTO {
    private String idUsuarioTexto;
    private String password;

    public LoginDTO(){};
    
    public LoginDTO(String idUsuarioTexto, String password) {
        this.idUsuarioTexto = idUsuarioTexto;
        this.password = password;
    }

    
    public String getIdUsuarioTexto() {
        return idUsuarioTexto;
    }

    public void setIdUsuarioTexto(String idUsuarioTexto) {
        this.idUsuarioTexto = idUsuarioTexto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
