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
 * Interfaz que define las operaciones disponibles en la fachada del módulo de
 * inventario.
 *
 * Proporciona acceso a las funcionalidades relacionadas con solicitudes,
 * movimientos y lotes dentro del sistema.
 *
 * @author Jorge
 */
public interface IFachadaInventario {

    /**
     * Busca una solicitud pendiente utilizando su código identificador.
     *
     * @param codigoSolicitud Código de la solicitud a buscar.
     * @return Solicitud encontrada en formato DTO o {@code null} si no existe o
     * no está pendiente.
     * @throws NegocioException Error al realizar la operación de negocio.
     */
    public SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioException;

    /**
     * Registra un movimiento de entrada dentro del sistema.
     *
     * @param movimiento Movimiento de entrada en formato DTO.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de entrada.
     */
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException;

    /**
     * Registra un movimiento de salida dentro del sistema.
     *
     * @param movimiento Movimiento de salida en formato DTO.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de salida.
     */
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException;

    /**
     * Obtiene un lote utilizando su código identificador.
     *
     * @param codigoLote Código del lote a buscar.
     * @return Lote encontrado en formato DTO o {@code null} si no existe.
     * @throws NegocioException Error al obtener el lote.
     */
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException;

}
