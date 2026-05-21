/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Lote;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;
import Entidades.Producto;
import Entidades.Solicitud;
import Excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones de persistencia relacionadas con el
 * módulo de inventario.
 *
 * Declara los métodos necesarios para gestionar solicitudes, movimientos y
 * lotes dentro de la base de datos.
 *
 * @author Jorge
 */
public interface IInventarioDAO {

    /**
     * Busca una solicitud pendiente utilizando su código identificador.
     *
     * @param claveSolicitud Código de la solicitud a buscar.
     * @return Solicitud encontrada o {@code null} si no existe o no está
     * pendiente.
     * @throws PersistenciaException Error al realizar la consulta en la base de
     * datos.
     */
    public Solicitud buscarSolicitud(String claveSolicitud) throws PersistenciaException;

    /**
     * Crea y registra un movimiento de entrada en la base de datos.
     *
     * @param movimiento Movimiento de entrada a registrar.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws PersistenciaException Error al guardar el movimiento de entrada.
     */
    public Boolean crearMovimientoEntrada(MovimientoEntrada movimiento) throws PersistenciaException;

    /**
     * Crea y registra un movimiento de salida en la base de datos.
     *
     * @param movimiento Movimiento de salida a registrar.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws PersistenciaException Error al guardar el movimiento de salida.
     */
    public Boolean crearMovimientoSalida(MovimientoSalida movimiento) throws PersistenciaException;

    /**
     * Actualiza el estado de una solicitud a completada.
     *
     * @param codigoSolicitud Código de la solicitud a actualizar.
     * @return {@code true} si la solicitud fue actualizada correctamente,
     * {@code false} en caso contrario.
     * @throws PersistenciaException Error al actualizar el estado de la
     * solicitud.
     */
    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws PersistenciaException;

    /**
     * Guarda un lote en la base de datos.
     *
     * @param lote Lote que se desea registrar.
     * @return {@code true} si el lote fue guardado correctamente, {@code false}
     * en caso contrario.
     * @throws PersistenciaException Error al guardar el lote.
     */
    public Boolean guardarLote(Lote lote) throws PersistenciaException;

    /**
     * Obtiene un lote utilizando su código identificador.
     *
     * @param codigoLote Código del lote a buscar.
     * @return Lote encontrado o {@code null} si no existe.
     * @throws PersistenciaException Error al obtener el lote desde la base de
     * datos.
     */
    public Lote obtenerLote(String codigoLote) throws PersistenciaException;

    /**
     * Genera un identificador único para un movimiento.
     *
     * @return Identificador generado para el movimiento.
     * @throws PersistenciaException Error al generar el identificador del
     * movimiento.
     */
    public String generarIdMovimiento() throws PersistenciaException;

}
