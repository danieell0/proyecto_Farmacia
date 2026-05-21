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
 * Interfaz que define las reglas de negocio relacionadas con el módulo de
 * inventario.
 *
 * Contiene los métodos necesarios para gestionar solicitudes, movimientos y
 * lotes dentro del sistema.
 *
 * @author Jorge
 */
public interface IInventarioBO {

    /**
     * Busca una solicitud pendiente utilizando su código identificador.
     *
     * @param claveSolicitud Código de la solicitud a buscar.
     * @return Solicitud encontrada o {@code null} si no existe o no está
     * pendiente.
     * @throws NegocioException Error al realizar la operación de negocio.
     */
    public SolicitudDTO buscarSolicitud(String claveSolicitud) throws NegocioException;

    /**
     * Registra un movimiento de entrada dentro del sistema.
     *
     * @param movimiento Movimiento de entrada a registrar.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de entrada.
     */
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException;

    /**
     * Registra un movimiento de salida dentro del sistema.
     *
     * @param movimiento Movimiento de salida a registrar.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de salida.
     */
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException;

    /**
     * Obtiene un lote utilizando su código identificador.
     *
     * @param codigoLote Código del lote a buscar.
     * @return Lote encontrado o {@code null} si no existe.
     * @throws NegocioException Error al obtener el lote.
     */
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException;

    /**
     * Guarda un lote dentro del sistema.
     *
     * @param lote Lote que se desea registrar.
     * @return {@code true} si el lote fue guardado correctamente, {@code false}
     * en caso contrario.
     * @throws NegocioException Error al guardar el lote.
     */
    public Boolean guardarLote(LoteDTO lote) throws NegocioException;

    /**
     * Actualiza el estado de una solicitud a completada.
     *
     * @param codigoSolicitud Código de la solicitud a actualizar.
     * @return {@code true} si la solicitud fue actualizada correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al actualizar el estado de la solicitud.
     */
    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws NegocioException;

    /**
     * Genera un identificador único para un movimiento.
     *
     * @return Identificador generado para el movimiento.
     * @throws NegocioException Error al generar el identificador del
     * movimiento.
     */
    public String generarIdMovimiento() throws NegocioException;

}
