/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package subsistemaGestionEmpleados;

import DTO.BajaDTO;
import DTO.CuentaAccesoDTO;
import DTO.EmpleadoDTO;
import DTO.ReporteDTO;
import DTO.SolicitanteDTO;
import Entidades.EmpleadoPermisos;

/**
 *
 * @author Benjamin
 */
public interface IFachadaGestionEmpleados {
    public boolean registrarSolicitante(SolicitanteDTO solicitante);
    
    public SolicitanteDTO buscarSolicitante(Long id);
    
    public boolean contratarSolicitante(SolicitanteDTO solicitante);
    
    public boolean rechazarSolicitante(SolicitanteDTO solicitante);
    
    public EmpleadoDTO buscarEmpleado(Long id);
    
    public boolean actualizarEmpleado(Long id, EmpleadoDTO empleadoNuevo);
    
    public boolean guardarReporte(ReporteDTO reporte);
    
    public ReporteDTO buscarReporte(Long id);
    
    public CuentaAccesoDTO actualizarCuentaAcceso(CuentaAccesoDTO cuentaAccesoNueva);
    
    public BajaDTO registrarBaja(BajaDTO baja);
    
    public boolean desactivarCuenta(Long id);
}
