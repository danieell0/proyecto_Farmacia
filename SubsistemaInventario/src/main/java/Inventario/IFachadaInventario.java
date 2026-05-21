/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Inventario;

import Bo.NegocioException;
import DTO.LoteDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SolicitudDTO;

/**
 *
 * @author Jorge
 */
public interface IFachadaInventario {

    public SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioException;

    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException;

    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException;

    public LoteDTO obtenerLote(String codigoLote) throws NegocioException;
    
}
