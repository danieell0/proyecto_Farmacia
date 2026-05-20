package Mappers;

import DTO.DetalleVentaDTO;
import Entidades.DetalleVenta;
import Entidades.Producto;

/**
 *
 * @author Jorge
 */
public class DetalleVentaMapper {
     private ProductoMapper mapper = new ProductoMapper();
     
    public DetalleVenta toEntity(DetalleVentaDTO dto){
        if(dto==null){
            return null;
        }
        DetalleVenta detalle= new DetalleVenta();
        detalle.setIdDetalle(dto.getIdVenta());
        detalle.setIdVenta(dto.getIdVenta());
        Producto producto=mapper.toEntity(dto.getProducto());
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());
        return detalle;
    }
}
