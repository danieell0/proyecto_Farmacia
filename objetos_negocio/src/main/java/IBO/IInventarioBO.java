/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IBO;

import Bo.NegocioException;
import DTO.LoteDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SolicitudDTO;

/**
 *
 * @author Jorge
 */
public interface IInventarioBO {

    public SolicitudDTO buscarSolicitud(String claveSolicitud) throws NegocioException;

    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException;

    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException;

    public LoteDTO obtenerLote(String codigoLote) throws NegocioException;

    public Boolean guardarLote(LoteDTO lote) throws NegocioException;

    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws NegocioException;
    
    public String generarIdMovimiento()throws NegocioException ;
    
}
