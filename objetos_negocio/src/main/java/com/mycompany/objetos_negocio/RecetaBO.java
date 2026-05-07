package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.ProductoDAO;
import Clases.RecetaDAO;
import DTO.RecetaDTO;
import Entidades.DetalleReceta;
import Entidades.Medicamento;
import Entidades.Producto;
import Entidades.Receta;
import Enums.Especialidades;
import Enums.EstadoReceta;
import GestorMedico.IValidacionMedico;
import GestorMedico.ValidacionMedico;
import IBO.IRecetaBO;
import Interfaces.IProductoDAO;
import Interfaces.IRecetaDAO;
import Mappers.RecetaMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase BO para las operaciones de las recetas.
 * @author Dario
 */
public class RecetaBO implements IRecetaBO{
    
    private IRecetaDAO recetaDAO;
    private IProductoDAO productoDAO;
    private IValidacionMedico sistemaExterno;
    
    public RecetaBO(){
        this.recetaDAO = new RecetaDAO();
        this.sistemaExterno = new ValidacionMedico();
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public RecetaDTO buscarRecetaPorFolio(String folio) throws NegocioException {
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if(receta == null){
                throw new NegocioException("No se encontro ninguna receta con ese folio.");
            }
            String cedula = receta.getCedulaMedico();
            List<DetalleReceta> detallesAutorizados = new ArrayList<>();
            
            for (DetalleReceta detalle : receta.getDetalles()) {
            // 1. Buscamos el producto por el ID que viene en el detalle
                Producto p = productoDAO.obtenerProductoPorId(detalle.getIdMedicamento());

                // 2. Verificamos si ese producto es un Medicamento (tiene especialidades)
                if (p instanceof Medicamento med) {
                    List<Especialidades> requeridas = med.getEspecialidad();

                    boolean autorizado = false;
                    // Si no requiere especialidades, es de venta libre
                    if (requeridas == null || requeridas.isEmpty()) {
                        autorizado = true;
                    } else {
                        for (Especialidades esp : requeridas) {
                            if (sistemaExterno.esMedicoAutorizado(cedula, esp.name())) {
                                autorizado = true;
                                break;
                            }
                        }
                    }

                    // SI ESTÁ AUTORIZADO, LO AGREGAMOS
                    if (autorizado) {
                        detallesAutorizados.add(detalle);
                    } else {
                        // Opcional: imprimir en consola para saber qué se quitó
                        System.out.println("Medicamento bloqueado por falta de especialidad: " + med.getNombre());
                    }
                } else {
                    // Si es un producto normal (agua, galletas), pasa directo
                    detallesAutorizados.add(detalle);
                }
            }

            // IMPORTANTE: Actualizamos los detalles de la receta con solo los autorizados
            receta.setDetalles(detallesAutorizados);

            if (detallesAutorizados.isEmpty()) {
                throw new NegocioException("El médico no está autorizado para recetar ninguno de los medicamentos de esta receta.");
            }
            return RecetaMapper.adaptarADTO(receta);
        } catch (NegocioException ex){
            throw ex;
        }
    }

    
    @Override
    public void actualizarEstado(String folio, EstadoReceta nuevoEstado) throws NegocioException {
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if (receta == null) {
                throw new NegocioException("No se encontro la receta con folio.");
            }
            if (receta.getEstado() != EstadoReceta.ACTIVA) {
                throw new NegocioException("No se puede cambiar el estado de la receta.");
            }
            receta.setEstado(nuevoEstado);
            recetaDAO.actualizarEstadoReceta(receta);
        } catch (NegocioException ex) {
            throw ex;
        }
    }
    
    @Override
    public void restarMedicamento(String folio, Long idMedicamento, Integer cantidad) throws NegocioException {
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if (receta == null) {
                throw new NegocioException("La receta no existe.");
            }
            if (receta.getEstado() != EstadoReceta.ACTIVA) {
                throw new NegocioException("La receta no es valida.");
            }
            DetalleReceta detalleEncontrado = null;
            for (DetalleReceta detalleReceta : receta.getDetalles()) {
                if (detalleReceta.getIdMedicamento().equals(idMedicamento)) {
                    detalleEncontrado = detalleReceta;
                    break;
                }
            }
            if (detalleEncontrado == null) {
                throw new NegocioException("El medicamento no forma parte de esta receta.");
            }
            int totalSurtido = detalleEncontrado.getCantidadSurtida() - cantidad;
            if (totalSurtido > detalleEncontrado.getCantidadRecetada()) {
                throw new NegocioException("No se puedes surtir mas.");
            }
            recetaDAO.restarMedicamentos(receta, idMedicamento, cantidad);
        } catch (NegocioException ex) {
            throw ex;
        }
    }

    @Override
    public void sumarMedicamento(String folio, Long idMedicamento, Integer cantidad) throws NegocioException {
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);

            if (receta == null) {
                throw new NegocioException("La receta no existe.");
            }
            if (receta.getEstado() != EstadoReceta.ACTIVA) {
                throw new NegocioException("La receta no es valida para surtir.");
            }
            DetalleReceta detalleEncontrado = null;
            for (DetalleReceta detalleReceta : receta.getDetalles()) {
                if (detalleReceta.getIdMedicamento().equals(idMedicamento)) {
                    detalleEncontrado = detalleReceta;
                    break;
                }
            }
            if (detalleEncontrado == null) {
                throw new NegocioException("El medicamento no forma parte de esta receta.");
            }
            recetaDAO.sumarMedicamentos(receta, idMedicamento, cantidad);
        } catch (NegocioException ex) {
            throw ex;
        }
    }
    
}
