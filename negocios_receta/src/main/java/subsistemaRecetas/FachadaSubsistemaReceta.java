package subsistemaRecetas;

import Bo.NegocioException;
import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import Enums.Especialidades;
import Enums.EstadoReceta;
import java.util.ArrayList;
import java.util.List;

/**
 * Fachada del subsistema de receta.
 * @author Dario
 */
public class FachadaSubsistemaReceta implements IFachadaSubsistemaRecetas{

    private final ControlBuscarReceta controlBuscar = new ControlBuscarReceta();
    private final ControlValidarReceta controlValidar = new ControlValidarReceta();
    private final ControlOperacionesReceta controlOperaciones = new ControlOperacionesReceta();
    private final ControlEstadoReceta controlEstado = new ControlEstadoReceta();

    private final List<RecetaDTO> recetasActivas = new ArrayList<>();

    /**
     * Valida si la receta es apta para usarse basandose en fecha,
     * estado actual, coincidencia con el medicamento y especialidad del medico.
     * Esta descuenta temporalmente los medicamentos de la receta para asegurar
     * que no se usen en otra venta.
     * @param folio Folio de la receta que se validara y 
     * reservaran sus productos.
     * @param idProducto ID de los productos involucrados en la receta.
     * @param cantidad Cantidad de los productos involucrados en la receta.
     * @param especialidadProducto Especialidad necesaria para recetar el Producto.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la receta se puede usar o no.
     */
    @Override
    public Boolean validarYReservar(String folio, String idProducto, Integer cantidad, Especialidades especialidadProducto) throws NegocioException {
        RecetaDTO receta = recetasActivas.stream()
            .filter(r -> r.getFolio().equals(folio))
            .findFirst()
            .orElseGet(() -> controlBuscar.obtenerRecetaPorFolio(folio));
        if (receta == null) {
            return false;
        }
        boolean fechaValida = controlValidar.validarFechaReceta(receta);
        EstadoReceta estadoActual = controlEstado.obtenerEstadoDeReceta(receta);
        if (!fechaValida || estadoActual != EstadoReceta.ACTIVA) {
            if (!fechaValida && estadoActual == EstadoReceta.ACTIVA) {
                controlEstado.actualizarEstadoReceta(receta, EstadoReceta.CADUCADA);
            }
            return false;
        }
        controlValidar.validarMedicamentosReceta(
                receta,
                idProducto,
                cantidad,
                especialidadProducto
        );
        controlOperaciones.restarMedicamentos(receta, idProducto, cantidad);
        boolean yaExiste = recetasActivas.stream()
                .anyMatch(r -> r.getFolio().equals(folio));
        if (!yaExiste) {
            recetasActivas.add(receta);
        }
        return true;
    }

    /**
     * Cancela la reserva de los productos agregados al carrito
     * (Cuando se cancela una venta).
     * @param folio Folio de la receta.
     * @param idProducto ID del producto que se se devolveran sus unidades reservadas.
     * @param cantidad Cantidad del producto.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la operacion fue exitosa.
     */
    @Override
    public Boolean cancelarReserva(String folio, String idProducto, Integer cantidad) throws NegocioException {
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
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la operacion fue exitosa.
     */
    @Override
    public Boolean confirmarDescuentoReceta() throws NegocioException {
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
        return true;
    }

    /**
     * Limpia las recetas guardadas temporalmente.
     * @return Si la operacion fue exitosa.
     */
    @Override
    public Boolean limpiarRecetasGuardadas() {
        this.recetasActivas.clear();
        return this.recetasActivas.isEmpty();
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
    public Boolean existeReceta(String folio) {
        RecetaDTO receta = controlBuscar.obtenerRecetaPorFolio(folio);
        return receta != null;
    }
    
    /**
     * Busca las productos en recetas activas.
     * @param idProducto ID de la receta activa a buscar.
     * @param cantidad La cantidad de productos en la receta.
     * @param especialidadProducto Especialidad para recetar el Producto.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si se encontro o no.
     */
    @Override
    public String buscarEnRecetasActivas(String idProducto, Integer cantidad, Especialidades especialidadProducto) throws NegocioException {
        for (RecetaDTO receta : recetasActivas) {
            if (controlValidar.validarExistenciaEnReceta(receta, idProducto)) {
                try {
                    boolean valido = controlValidar.validarMedicamentosReceta(receta, idProducto, cantidad, especialidadProducto);
                    if (valido) {
                        controlOperaciones.restarMedicamentos(receta, idProducto, cantidad);
                        return receta.getFolio();
                    }
                } catch (NegocioException e) {
                    
                }
            }
        }
        return null;
    }
    
    /**
     * Devuelve los productos a la receta si se cancela la venta.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return El resultado de la operacion.
     */
    @Override
    public Boolean cancelarYDevolverRecetas() throws NegocioException{
        for (RecetaDTO receta : recetasActivas) {
            for (DetalleRecetaDTO detalle : receta.getDetalles()) {
                int surtido = detalle.getCantidadSurtida();
                if (surtido > 0) {
                    controlOperaciones.sumarMedicamentos(receta, detalle.getIdMedicamento(), surtido);
                }
            }
        }
        this.recetasActivas.clear();
        return this.recetasActivas.isEmpty();
    }
    
}
