package subsistemaRecetas;

import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import Enums.EstadoReceta;
import java.util.ArrayList;
import java.util.List;
import subsistemaRecetas.control.ControlBuscarReceta;
import subsistemaRecetas.control.ControlEstadoReceta;
import subsistemaRecetas.control.ControlOperacionesReceta;
import subsistemaRecetas.control.ControlValidarReceta;

/**
 * Fachada del subsistema de receta.
 * @author Dario
 */
public class FachadaSubsistemaReceta implements IControlRecetas{

    private final ControlBuscarReceta controlBuscar = new ControlBuscarReceta();
    private final ControlValidarReceta controlValidar = new ControlValidarReceta();
    private final ControlOperacionesReceta controlOperaciones = new ControlOperacionesReceta();
    private final ControlEstadoReceta controlEstado = new ControlEstadoReceta();

    private final List<RecetaDTO> recetasActivas = new ArrayList<>();

    /**
     * Valida si la receta es apta para usarse basandose en fecha,
     * estado actual y coincidencia con el medicamento.
     * Esta descuenta temporalmente los medicamentos de la receta para asegurar
     * que no se usen en otra venta.
     * @param folio Folio de la receta que se validara y 
     * reservaran sus productos.
     * @param idProducto ID de los productos involucrados en la receta.
     * @param cantidad Cantidad de los productos involucrados en la receta.
     * @return Si la receta se puede usar o no.
     */
    @Override
    public boolean validarYReservar(String folio, Long idProducto, Integer cantidad) {
        RecetaDTO receta = obtenerRecetaInterna(folio);
        if (receta == null) {
            receta = controlBuscar.obtenerRecetaPorFolio(folio);
        }
        if (receta != null) {
            boolean esValida = controlValidar.validarFechaReceta(receta) &&
                               controlEstado.obtenerEstadoDeReceta(receta) == EstadoReceta.ACTIVA &&
                               controlValidar.validarExistenciaEnReceta(receta, idProducto) &&
                               controlValidar.validarMedicamentosReceta(receta, idProducto, cantidad);
            if (esValida) {
                if (obtenerRecetaInterna(folio) == null) {
                    recetasActivas.add(receta);
                }
                controlOperaciones.restarMedicamentos(receta, idProducto, cantidad);
                return true;
            }
        }
        return false;
    }

    /**
     * Cancela la reserva de los productos agregados al carrito
     * (Cuando se cancela una venta).
     * @param folio Folio de la receta.
     * @param idProducto ID del producto que se se devolveran sus unidades reservadas.
     * @param cantidad Cantidad del producto.
     * @return Si la operacion fue exitosa.
     */
    @Override
    public boolean cancelarReserva(String folio, Long idProducto, Integer cantidad) {
        RecetaDTO receta = obtenerRecetaInterna(folio);
        if (receta != null) {
            controlOperaciones.sumarMedicamentos(receta, idProducto, cantidad);
            return true;
        }
        return false;
    }
    
    /**
     * Confirma los descuentos que se hicieron en el metodo de validarYReservar
     * (Cuando se confirma una venta).
     */
    @Override
    public void confirmarDescuentoReceta() {
        for (RecetaDTO receta : recetasActivas) {
            boolean surtidaCompletamente = true;
            for (DetalleRecetaDTO detalles : receta.getDetalles()) {
                if (detalles.getCantidadSurtida() < detalles.getCantidadRecetada()) {
                    surtidaCompletamente = false;
                    break;
                }
            }
            if (surtidaCompletamente) {
                controlEstado.actualizarEstadoReceta(receta, EstadoReceta.SURTIDA);
            }
        }
        limpiarRecetasGuardadas();
    }

    /**
     * Limpia las recetas guardadas temporalmente.
     */
    @Override
    public void limpiarRecetasGuardadas() {
        this.recetasActivas.clear();
    }
    
    /**
     * Obtiene las recetas guardadas temporalmente.
     * @param folio Folio de la receta guardada.
     * @return La receta o nada si no lo encontro.
     */
    @Override
    public RecetaDTO obtenerRecetaInterna(String folio) {
        for (RecetaDTO receta : recetasActivas) {
            String folioDTO = String.valueOf(receta.getFolio());
            if (folioDTO.equals(folio)) {
                return receta;
            }
        }
        return null;
    }
    
    /**
     * Verifica si la receta existe.
     * @param folio Folio de la receta.
     * @return Si la receta existe.
     */
    @Override
    public boolean existeReceta(String folio) {
        RecetaDTO receta = controlBuscar.obtenerRecetaPorFolio(folio);
        if (receta != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Busca las productos en recetas activas.
     * @param idProducto ID de la receta activa a buscar.
     * @param cantidad La cantidad de productos en la receta.
     * @return Si se encontro o no.
     */
    @Override
    public String buscarEnRecetasActivas(Long idProducto, Integer cantidad) {
        for (RecetaDTO receta : recetasActivas) {
            if (controlValidar.validarExistenciaEnReceta(receta, idProducto) && 
                controlValidar.validarMedicamentosReceta(receta, idProducto, cantidad)) {
                controlOperaciones.restarMedicamentos(receta, idProducto, cantidad);
                return receta.getFolio();
            }
        }
        return null;
    }
    
}
