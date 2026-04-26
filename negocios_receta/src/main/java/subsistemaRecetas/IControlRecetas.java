package subsistemaRecetas;

import DTO.RecetaDTO;

/**
 * Interfaz que determina los metodos de la clase FachadaSubsistemaReceta.
 * @author Dario
 */
public interface IControlRecetas {
    
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
    public abstract boolean validarYReservar(
            String folio, Long idProducto, Integer cantidad
    );
    
    /**
     * Cancela la reserva de los productos agregados al carrito
     * (Cuando se cancela una venta).
     * @param folio Folio de la receta.
     * @param idProducto ID del producto que se se devolveran sus unidades reservadas.
     * @param cantidad Cantidad del producto.
     * @return Si la operacion fue exitosa.
     */
    public abstract boolean cancelarReserva(
            String folio, Long idProducto, Integer cantidad
    );
    
    /**
     * Confirma los descuentos que se hicieron en el metodo de validarYReservar
     * (Cuando se confirma una venta).
     */
    public abstract void confirmarDescuentoReceta();
    
    /**
     * Limpia las recetas guardadas temporalmente.
     */
    public abstract void limpiarRecetasGuardadas();
    
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
    public abstract boolean existeReceta(String folio);
    
    /**
     * Busca las productos en recetas activas.
     * @param idProducto ID de la receta activa a buscar.
     * @param cantidad La cantidad de productos en la receta.
     * @return Si se encontro o no.
     */
    public abstract String buscarEnRecetasActivas(Long idProducto, Integer cantidad);

}
