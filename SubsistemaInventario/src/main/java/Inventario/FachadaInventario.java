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
 * Clase que implementa la fachada del módulo de inventario.
 *
 * Implementa la interfaz {@link IFachadaInventario}.
 *
 * Se encarga de proporcionar acceso a las operaciones relacionadas con
 * solicitudes, movimientos y lotes mediante el uso del controlador de
 * inventario.
 *
 * @author Jorge
 */
public class FachadaInventario implements IFachadaInventario {

    private ControlInventario control;
    private static final Logger logger = Logger.getLogger(FachadaInventario.class.getSimpleName());

    /**
     * Constructor de la clase FachadaInventario.
     *
     * Inicializa la instancia del controlador de inventario.
     */
    public FachadaInventario() {
        control = new ControlInventario();
    }

    /**
     * Busca una solicitud pendiente utilizando su código identificador.
     *
     * @param codigoSolicitud Código de la solicitud a buscar.
     * @return Solicitud encontrada en formato DTO o {@code null} si no existe o
     * no está pendiente.
     * @throws NegocioException Error al realizar la operación de negocio.
     */
    @Override
    public SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioException {
        return control.buscarSolicitud(codigoSolicitud);
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
        return control.registrarMovimientoEntrada(movimiento);
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
        return control.registrarMovimientoSalida(movimiento);
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
        return control.obtenerLote(codigoLote);
    }

}
