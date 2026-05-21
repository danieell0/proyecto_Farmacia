/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.MovimientoDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import Entidades.Movimiento;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;

/**
 *
 * @author Jorge
 */
public class MovimientoMapper {
    public static MovimientoDTO toDTO(Movimiento movimiento) {
        if (movimiento == null) {
            return null;
        }
        if (movimiento instanceof MovimientoEntrada entrada) {
            MovimientoEntradaDTO dto =new MovimientoEntradaDTO();
            dto.setIdMovimiento(entrada.getIdMovimiento());
            dto.setFechaHora(entrada.getFechaHora());
            dto.setIdEmpleado(entrada.getIdEmpleado());
            dto.setCodigoSolicitud(entrada.getCodigoSolicitud());
            dto.setLote(LoteMapper.toDTO(entrada.getLote()));
            return dto;
        }
        if (movimiento instanceof MovimientoSalida salida) {
            MovimientoSalidaDTO dto =new MovimientoSalidaDTO();
            dto.setIdMovimiento(salida.getIdMovimiento());
            dto.setFechaHora(salida.getFechaHora());
            dto.setIdEmpleado(salida.getIdEmpleado());
            dto.setCodigoSolicitud(salida.getCodigoSolicitud());
            dto.setProducto(ProductoMapper.toDTO(salida.getProducto()));
            dto.setCantidad(salida.getCantidad());
            dto.setMotivo(salida.getMotivo());
            dto.setObservacion(salida.getObservacion());
            dto.setCantidadAnterior(salida.getCantidadAnterior());
            dto.setCantidadNueva(salida.getCantidadNueva());
            return dto;
        }
        MovimientoDTO dto = new MovimientoDTO();
        dto.setIdMovimiento(movimiento.getIdMovimiento());
        dto.setFechaHora(movimiento.getFechaHora());
        dto.setIdEmpleado(movimiento.getIdEmpleado());
        dto.setCodigoSolicitud(movimiento.getCodigoSolicitud());
        return dto;
    }

    public static Movimiento toEntity(MovimientoDTO dto) {
        if (dto == null) {
            return null;
        }
        if (dto instanceof MovimientoEntradaDTO entradaDTO) {
            MovimientoEntrada entrada =new MovimientoEntrada();
            entrada.setIdMovimiento(entradaDTO.getIdMovimiento());
            entrada.setFechaHora(entradaDTO.getFechaHora());
            entrada.setIdEmpleado(entradaDTO.getIdEmpleado());
            entrada.setCodigoSolicitud(entradaDTO.getCodigoSolicitud());
            entrada.setLote(LoteMapper.toEntity(entradaDTO.getLote()));
            return entrada;
        }
        if (dto instanceof MovimientoSalidaDTO salidaDTO) {
            MovimientoSalida salida =new MovimientoSalida();
            salida.setIdMovimiento(salidaDTO.getIdMovimiento());
            salida.setFechaHora(salidaDTO.getFechaHora());
            salida.setIdEmpleado(salidaDTO.getIdEmpleado());
            salida.setCodigoSolicitud(salidaDTO.getCodigoSolicitud());
            salida.setProducto(ProductoMapper.toEntity(salidaDTO.getProducto()));
            salida.setCantidad(salidaDTO.getCantidad());
            salida.setMotivo(salidaDTO.getMotivo());
            salida.setObservacion(salidaDTO.getObservacion());
            salida.setCantidadAnterior(salidaDTO.getCantidadAnterior());
            salida.setCantidadNueva(salidaDTO.getCantidadNueva());
            return salida;
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(dto.getIdMovimiento());
        movimiento.setFechaHora(dto.getFechaHora());
        movimiento.setIdEmpleado(dto.getIdEmpleado());
        movimiento.setCodigoSolicitud(dto.getCodigoSolicitud());
        return movimiento;
    }
}