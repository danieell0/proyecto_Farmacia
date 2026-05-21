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
import objetosNegocio.IEmpleadoBO;

/**
 *
 * @author Benjamin
 */
public class ControlGestionEmpleados {
    // Variable privada apuntando a la interfaz del BO (Inversión de dependencias)
    private IEmpleadoBO empleadoBO;

    public ControlGestionEmpleados() {
        this.empleadoBO = empleadoBO;
    }

    // ==========================================
    // MÉTODOS OPERATIVOS (Delegados desde la Fachada)
    // ==========================================

    public boolean registrarSolicitante(SolicitanteDTO solicitante) {
        return false;
    }

    public SolicitanteDTO buscarSolicitante(Long id) {
        return null;
    }

    public boolean contratarSolicitante(SolicitanteDTO solicitante) {
        return false;
    }

    public boolean rechazarSolicitante(SolicitanteDTO solicitante) {
        return false;
    }

    public EmpleadoDTO buscarEmpleado(Long id) {
        return null;
    }

    public boolean actualizarEmpleado(Long id, EmpleadoDTO empleadoNuevo) {
        return false;
    }

    public boolean guardarReporte(ReporteDTO reporte) {
        return false;
    }

    public ReporteDTO buscarReporte(Long id) {
        return null;
    }

    public CuentaAccesoDTO actualizarCuentaAcceso(CuentaAccesoDTO cuentaAccesoNueva) {
        return null;
    }

    public BajaDTO registrarBaja(BajaDTO baja) {
        return null;
    }

    public boolean desactivarCuenta(Long id) {
        return false;
    }

    // ==========================================
    // MÉTODOS DE VALIDACIÓN INTERNA
    // ==========================================

    public boolean validarSolicitante(SolicitanteDTO solicitante) {
        return false;
    }

    public boolean validarEmpleado(EmpleadoDTO empleado) {
        return false;
    }

    public boolean validarReporte(ReporteDTO reporte) {
        return false;
    }

    public boolean validarCuentaAcceso(Long id, String contraseña) {
        return false;
    }

    public boolean validarBaja(BajaDTO baja) { // Asumo BajaDTO por el contexto
        return false;
    }

    public boolean validarCuenta(Long id) {
        return false;
    }
}
