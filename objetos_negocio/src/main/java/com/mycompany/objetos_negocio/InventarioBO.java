/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.InventarioDAO;
import Clases.ProductoDAO;
import ConexionMongo.ManejadorConexiones;
import DTO.DetalleLoteDTO;
import DTO.LoteDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SolicitudDTO;
import Entidades.Lote;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;
import Entidades.Producto;
import Entidades.Solicitud;
import Excepciones.PersistenciaException;
import IBO.IInventarioBO;
import Interfaces.IInventarioDAO;
import Interfaces.IProductoDAO;
import Mappers.LoteMapper;
import Mappers.MovimientoMapper;
import Mappers.SolicitudMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa las reglas de negocio relacionadas con el módulo de
 * inventario.
 *
 * Implementa la interfaz {@link IInventarioBO}.
 *
 * Se encarga de gestionar solicitudes, movimientos y lotes utilizando la capa
 * de persistencia correspondiente.
 *
 * @author Jorge
 */
public class InventarioBO implements IInventarioBO {

    private IInventarioDAO inventarioDAO;
    private static final Logger logger = Logger.getLogger(InventarioBO.class.getSimpleName());

    /**
     * Constructor de la clase InventarioBO.
     *
     * Inicializa la instancia del DAO de inventario.
     */
    public InventarioBO() {
        inventarioDAO = new InventarioDAO();
    }

    /**
     * Busca una solicitud pendiente utilizando su código identificador.
     *
     * @param claveSolicitud Código de la solicitud a buscar.
     * @return Solicitud encontrada en formato DTO o {@code null} si no existe o
     * no está pendiente.
     * @throws NegocioException Error al realizar la operación de negocio.
     */
    @Override
    public SolicitudDTO buscarSolicitud(String claveSolicitud) throws NegocioException {
        try {
            return SolicitudMapper.toDTO(inventarioDAO.buscarSolicitud(claveSolicitud));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al buscar la solicitud");
            throw new NegocioException("Error al buscar la solicitud");
        }
    }

    /**
     * Registra un movimiento de entrada dentro del sistema.
     *
     * @param movimiento Movimiento de entrada en formato DTO.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de entrada.
     */
    @Override
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException {
        try {
            MovimientoEntrada entidad = (MovimientoEntrada) MovimientoMapper.toEntity(movimiento);
            return inventarioDAO.crearMovimientoEntrada(entidad);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar el movimiento de entrada");
            throw new NegocioException("Error al registrar el movimiento de entrada");
        }
    }

    /**
     * Registra un movimiento de salida dentro del sistema.
     *
     * @param movimiento Movimiento de salida en formato DTO.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de salida.
     */
    @Override
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException {
        try {
            MovimientoSalida salida = (MovimientoSalida) MovimientoMapper.toEntity(movimiento);
            return inventarioDAO.crearMovimientoSalida(salida);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar el movimiento de salida");
            throw new NegocioException("Error al registrar el movimiento de salida");
        }
    }

    /**
     * Obtiene un lote utilizando su código identificador.
     *
     * @param codigoLote Código del lote a buscar.
     * @return Lote encontrado en formato DTO o {@code null} si no existe.
     * @throws NegocioException Error al obtener el lote.
     */
    @Override
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        try {
            return LoteMapper.toDTO(inventarioDAO.obtenerLote(codigoLote));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al obtener el lote");
            throw new NegocioException("Error al obtener el lote");
        }
    }

    /**
     * Guarda un lote dentro del sistema.
     *
     * @param lote Lote en formato DTO que se desea registrar.
     * @return {@code true} si el lote fue guardado correctamente, {@code false}
     * en caso contrario.
     * @throws NegocioException Error al guardar el lote.
     */
    @Override
    public Boolean guardarLote(LoteDTO lote) throws NegocioException {
        try {
            return inventarioDAO.guardarLote(LoteMapper.toEntity(lote));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al guardar el lote");
            throw new NegocioException("Error al guardar el lote");
        }
    }

    /**
     * Actualiza el estado de una solicitud a completada.
     *
     * @param codigoSolicitud Código de la solicitud a actualizar.
     * @return {@code true} si la solicitud fue actualizada correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al actualizar el estado de la solicitud.
     */
    @Override
    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws NegocioException {
        try {
            return inventarioDAO.actualizarEstadoSolicitud(codigoSolicitud);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el estado de la solicitud");
            throw new NegocioException("Error al actualizar el estado de la solicitud");
        }
    }

    /**
     * Genera un identificador único para un movimiento.
     *
     * @return Identificador generado para el movimiento.
     * @throws NegocioException Error al generar el identificador del
     * movimiento.
     */
    @Override
    public String generarIdMovimiento() throws NegocioException {
        try {
            return inventarioDAO.generarIdMovimiento();
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al generar el id del movimiento");
            throw new NegocioException("Error al generar el id del movimiento");
        }
    }
}
