/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;

import Bo.NegocioException;
import DTO.LoteDTO;
import DTO.MovimientoDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SolicitudDTO;
import com.mycompany.objetos_negocio.InventarioBO;
import excepciones.NegocioExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class ControlInventario {

    protected InventarioBO inventarioBO;
    private static final Logger logger = Logger.getLogger(ControlInventario.class.getSimpleName());

    protected ControlInventario() {
        inventarioBO = new InventarioBO();
    }

    protected SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioExcepcion {
        try {
            return inventarioBO.buscarSolicitud(codigoSolicitud);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE,"Error al buscar la solicitud",ex);
            throw new NegocioExcepcion("Error al buscar la solicitud");
        }
    }

    protected Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioExcepcion {
        try {
            return inventarioBO.registrarMovimientoEntrada(movimiento);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE,"Error al registrar el movimiento de entrada",ex);
            throw new NegocioExcepcion("Error al registrar el movimiento de entrada");
        }
    }

    protected Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimientoSalida) throws NegocioExcepcion {
        try {
            return inventarioBO.registrarMovimientoSalida(movimientoSalida);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE,"Error al registrar el movimiento de salida",ex);
            throw new NegocioExcepcion("Error al registrar el movimiento de salida");
        }
    }

    protected LoteDTO obtenerLote(String codigoLote) throws NegocioExcepcion {
        try {
            return inventarioBO.obtenerLote(codigoLote);
        } catch (NegocioException ex) {
             logger.log(Level.SEVERE,"Error al obtener el lote",ex);
            throw new NegocioExcepcion("Error al obtener el lote");
        }
    }


}
