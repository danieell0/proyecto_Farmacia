/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Movimiento;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;
import EntidadesMongo.MovimientoEntradaMongo;
import EntidadesMongo.MovimientoMongo;
import EntidadesMongo.MovimientoSalidaMongo;

public class MovimientoMapperMongo {
    public static Movimiento entityToDomain(MovimientoMongo mongo) {
        if (mongo == null) {
            return null;
        }
        if (mongo instanceof MovimientoEntradaMongo entradaMongo) {
            MovimientoEntrada entrada = new MovimientoEntrada();
            entrada.setIdMovimiento(entradaMongo.getIdMovimiento());
            entrada.setFechaHora(entradaMongo.getFechaHora());
            entrada.setIdEmpleado(entradaMongo.getIdEmpleado());
            entrada.setCodigoSolicitud(entradaMongo.getCodigoSolicitud());
            entrada.setLote(LoteMapperMongo.entityToDomain(entradaMongo.getLote()));
            return entrada;
        }
        if (mongo instanceof MovimientoSalidaMongo salidaMongo) {
            MovimientoSalida salida = new MovimientoSalida();
            salida.setIdMovimiento(salidaMongo.getIdMovimiento());
            salida.setFechaHora(salidaMongo.getFechaHora());
            salida.setIdEmpleado(salidaMongo.getIdEmpleado());
            salida.setCodigoSolicitud(salidaMongo.getCodigoSolicitud());
            salida.setProducto(ProductoMapperMongo.entityToDomain(salidaMongo.getProducto()));
            salida.setCantidad(salidaMongo.getCantidad());
            salida.setMotivo(salidaMongo.getMotivo());
            salida.setObservacion(salidaMongo.getObservacion());
            salida.setCantidadAnterior(salidaMongo.getCantidadAnterior());
            salida.setCantidadNueva(salidaMongo.getCantidadNueva());
            return salida;
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setIdMovimiento(mongo.getIdMovimiento());
        movimiento.setFechaHora(mongo.getFechaHora());
        movimiento.setIdEmpleado(mongo.getIdEmpleado());
        movimiento.setCodigoSolicitud(mongo.getCodigoSolicitud());
        return movimiento;
    }
    
    public static MovimientoMongo domainToEntity(Movimiento movimento) {
        if (movimento == null) {
            return null;
        }
        if (movimento instanceof MovimientoEntrada entrada) {
            MovimientoEntradaMongo entradaMongo= new MovimientoEntradaMongo();
            entradaMongo.setIdMovimiento(entrada.getIdMovimiento());
            entradaMongo.setIdEmpleado(entrada.getIdEmpleado());
            entradaMongo.setFechaHora(entrada.getFechaHora());
            entradaMongo.setCodigoSolicitud(entrada.getCodigoSolicitud());
            entradaMongo.setLote(LoteMapperMongo.domainToEntity(entrada.getLote()));
            return entradaMongo;
        }
        if (movimento instanceof MovimientoSalida salida) {
            MovimientoSalidaMongo salidaMongo= new MovimientoSalidaMongo();
            salidaMongo.setIdMovimiento(salida.getIdMovimiento());
            salidaMongo.setFechaHora(salida.getFechaHora());
            salidaMongo.setIdEmpleado(salida.getIdEmpleado());
            salidaMongo.setCodigoSolicitud(salida.getCodigoSolicitud());
            salidaMongo.setProducto(ProductoMapperMongo.domainToEntity(salida.getProducto()));
            salidaMongo.setCantidad(salida.getCantidad());
            salidaMongo.setMotivo(salida.getMotivo());
            salidaMongo.setObservacion(salida.getObservacion());
            salidaMongo.setCantidadAnterior(salida.getCantidadAnterior());
            salidaMongo.setCantidadNueva(salida.getCantidadNueva());
            return salidaMongo;
        }
        MovimientoMongo mongo = new MovimientoMongo();
        mongo.setIdMovimiento(movimento.getIdMovimiento());
        mongo.setIdEmpleado(movimento.getIdEmpleado());
        mongo.setFechaHora(movimento.getFechaHora());
        mongo.setCodigoSolicitud(movimento.getCodigoSolicitud());
        return mongo;
    }
}
