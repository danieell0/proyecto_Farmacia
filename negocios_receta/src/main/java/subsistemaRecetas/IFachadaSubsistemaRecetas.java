package subsistemaRecetas;

import Bo.NegocioException;
import DTO.RecetaDTO;
import Enums.Especialidades;

/**
 * Interfaz que determina los metodos de la clase FachadaSubsistemaReceta.
 * @author Dario
 */
public interface IFachadaSubsistemaRecetas {
    
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
     *  @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la receta se puede usar o no.
     */
    public abstract Boolean validarYReservar(
            String folio, String idProducto, Integer cantidad, Especialidades especialidadProducto
    ) throws NegocioException;
    
    /**
     * Cancela la reserva de los productos agregados al carrito
     * (Cuando se cancela una venta).
     * @param folio Folio de la receta.
     * @param idProducto ID del producto que se se devolveran sus unidades reservadas.
     * @param cantidad Cantidad del producto.
     *  @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la operacion fue exitosa.
     */
    public abstract Boolean cancelarReserva(
            String folio, String idProducto, Integer cantidad
    ) throws NegocioException;
    
    /**
     * Confirma los descuentos que se hicieron en el metodo de validarYReservar
     * (Cuando se confirma una venta).
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la operacion fue exitosa.
     */
    public abstract Boolean confirmarDescuentoReceta() throws NegocioException;
    
    /**
     * Limpia las recetas guardadas temporalmente.
     * @return Si la operacion fue exitosa.
     */
    public abstract Boolean limpiarRecetasGuardadas();
    
    /**
     * Obtiene las recetas guardadas temporalmente.
     * @param folio Folio de la receta guardada.
     * @return La receta o nada si no lo encontro.
     */
    public abstract RecetaDTO obtenerRecetaInterna(String folio);

    /**
     * Verifica si la receta existe.
     * @param folio Folio de la receta.
     * @return Si la receta existe.
     */
    public abstract Boolean existeReceta(String folio);
    
    /**
     * Busca las productos en recetas activas.
     * @param idProducto ID de la receta activa a buscar.
     * @param cantidad La cantidad de productos en la receta.
     * @param especialidadProducto Especialidad para recetar el Producto.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si se encontro o no.
     */
    public abstract String buscarEnRecetasActivas(
            String idProducto, Integer cantidad, Especialidades especialidadProducto
    ) throws NegocioException;

    /**
     * Devuelve los productos a la receta si se cancela la venta.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return El resultado de la operacion.
     */
    public Boolean cancelarYDevolverRecetas() throws NegocioException;
}
