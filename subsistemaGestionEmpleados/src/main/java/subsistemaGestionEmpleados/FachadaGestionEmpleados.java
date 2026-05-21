/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsistemaGestionEmpleados;

import DTO.BajaDTO;
import DTO.CuentaAccesoDTO;
import DTO.EmpleadoDTO;
import DTO.ReporteDTO;
import DTO.SolicitanteDTO;

/**
 *
 * @author Benjamin
 */
public class FachadaGestionEmpleados implements IFachadaGestionEmpleados{
    // Relación de asociación dirigida hacia el Control
    private ControlGestionEmpleados controlGestionEmpleados;

    public FachadaGestionEmpleados() {
        // Instanciamos el control cuando nace la fachada
        this.controlGestionEmpleados = new ControlGestionEmpleados();
    }

    @Override
    public boolean registrarSolicitante(SolicitanteDTO solicitante) {
        return false; 
    }

    @Override
    public SolicitanteDTO buscarSolicitante(Long id) {
        return null;
    }

    @Override
    public boolean contratarSolicitante(SolicitanteDTO solicitante) {
        return false;
    }

    @Override
    public boolean rechazarSolicitante(SolicitanteDTO solicitante) {
        return false;
    }

    @Override
    public EmpleadoDTO buscarEmpleado(Long id) {
        return null;
    }

    @Override
    public boolean actualizarEmpleado(Long id, EmpleadoDTO empleadoNuevo) {
        return false;
    }

    @Override
    public boolean guardarReporte(ReporteDTO reporte) {
        return false;
    }

    @Override
    public ReporteDTO buscarReporte(Long id) {
        return null;
    }

    @Override
    public CuentaAccesoDTO actualizarCuentaAcceso(CuentaAccesoDTO cuentaAccesoNueva) {
        return null;
    }

    @Override
    public BajaDTO registrarBaja(BajaDTO baja) {
        return null;
    }

    @Override
    public boolean desactivarCuenta(Long id) {
        return false;
    }
}
