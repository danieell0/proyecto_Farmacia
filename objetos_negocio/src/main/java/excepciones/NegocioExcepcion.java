/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package excepciones;

/**
 *
 * @author Benjamin
 */
public class NegocioExcepcion extends Exception {

    /**
     * Creates a new instance of <code>NegocioExcepcion</code> without detail
     * message.
     */
    public NegocioExcepcion() {
    }

    /**
     * Constructs an instance of <code>NegocioExcepcion</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NegocioExcepcion(String msg) {
        super(msg);
    }
}
