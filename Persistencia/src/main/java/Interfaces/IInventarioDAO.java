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
 *
 * @author Jorge
 */
public interface IInventarioDAO {
    public Solicitud buscarSolicitud(String claveSolicitud)throws PersistenciaException;

    public Boolean crearMovimientoEntrada(MovimientoEntrada movimiento)throws PersistenciaException;

    public Boolean crearMovimientoSalida(MovimientoSalida movimiento)throws PersistenciaException;

    public Boolean actualizarEstadoSolicitud(String codigoSolicitud)throws PersistenciaException;
    
    public Boolean guardarLote(Lote lote)throws PersistenciaException;
    
    public Lote obtenerLote(String codigoLote)throws PersistenciaException;
    
}
