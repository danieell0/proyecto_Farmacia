package fachada;

/**
 * Clase factory de la estrategia de venta.
 * @author Dario
 */
public class VentaStrategyFactory {
    
    /**
     * Crea la estrategia de venta.
     * @param tipoPago El tipo de pago de la venta.
     * @return La estrategia de la venta.
     */
    public static IVentaStrategy crear(String tipoPago) {
        return switch (tipoPago.toUpperCase()) {
            case "EFECTIVO" -> new VentaNormalStrategy();
            case "PUNTOS"   -> new VentaPuntosStrategy();
            default -> throw new IllegalArgumentException("Tipo de pago no soportado: " + tipoPago);
        };
    }
}
