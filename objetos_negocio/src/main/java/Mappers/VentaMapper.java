package Mappers;

import DTO.VentaDTO;
import Entidades.DetalleVenta;
import Entidades.Venta;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class VentaMapper {

    private DetalleVentaMapper detalleMapper = new DetalleVentaMapper();

    public Venta toEntity(VentaDTO dto) {
        if (dto == null) {
            return null;
        }
        Venta venta = new Venta();
        venta.setIdVenta(dto.getIdVenta());
        venta.setFecha(dto.getFecha());
        venta.setTotal(dto.getTotal());
        venta.setIdEmpleado(dto.getIdEmpleado());
        venta.setIdCliente(dto.getIdCliente());
        venta.setTipo(dto.getTipo());
        venta.setPuntosGenerados(dto.getPuntosGenerados());

        if (dto.getDetalles() != null) {
            List<DetalleVenta> detalles = dto.getDetalles().stream().map(d -> detalleMapper.toEntity(d)).toList();
            venta.setDetalles(detalles);
        }
        return venta;
    }
}
