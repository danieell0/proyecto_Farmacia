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
public class FachadaInventario implements IFachadaInventario {

    private ControlInventario control;
    private static final Logger logger = Logger.getLogger(FachadaInventario.class.getSimpleName());

    public FachadaInventario() {
        control = new ControlInventario();
    }

    @Override
    public SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioException {
        return control.buscarSolicitud(codigoSolicitud);
    }

    @Override
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException {
        return control.registrarMovimientoEntrada(movimiento);
    }

    @Override
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException {
        return control.registrarMovimientoSalida(movimiento);
    }

    @Override
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        return control.obtenerLote(codigoLote);
    }

}
