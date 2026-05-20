/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;

import Bo.NegocioException;
import DTO.LoteDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SolicitudDTO;
import excepciones.NegocioExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class Fachada implements IFachada {

    private ControlInventario control;
    private static final Logger logger = Logger.getLogger(Fachada.class.getSimpleName());

    public Fachada() {
        control=new ControlInventario();
    }

    @Override
    public SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioException {
        try {
            return control.buscarSolicitud(codigoSolicitud);
        } catch (NegocioExcepcion ex) {
            logger.log(Level.SEVERE, "Error al buscar la solicitud", ex);
            throw new NegocioException("Error al buscar la solicitud");
        }
    }

    @Override
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException {
        try {
            return control.registrarMovimientoEntrada(movimiento);
        } catch (NegocioExcepcion ex) {
            logger.log(Level.SEVERE, "Error al registrar el movimiento de entrada", ex);
            throw new NegocioException("Error al registrar el movimiento de entrada");
        }
    }

    @Override
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException {
        try { 
            return control.registrarMovimientoSalida(movimiento);
        } catch (NegocioExcepcion ex) {
            logger.log(Level.SEVERE,"Error al registrar el movimiento de salida",ex);
            throw new NegocioException("Error al registrar el mocimiento de salida");
        }
    }

    @Override
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        try {
            return control.obtenerLote(codigoLote);
        } catch (NegocioExcepcion ex) {
            logger.log(Level.SEVERE,"Error al obtener el lote",ex);
            throw new NegocioException("Error al obtener el lote");
        }
    }

}
