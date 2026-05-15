package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.RecetaDAO;
import DTO.RecetaDTO;
import Entidades.Receta;
import Enums.EstadoReceta;
import IBO.IRecetaBO;
import Interfaces.IRecetaDAO;
import Mappers.RecetaMapper;

/**
 * Clase BO con validaciones minimmas para las operaciones de las recetas.
 * @author Dario
 */
public class RecetaBO implements IRecetaBO{
    
    private IRecetaDAO recetaDAO;

    /**
     * Contructor de la clase.
     */
    public RecetaBO(){
        this.recetaDAO = new RecetaDAO();
    }

    /**
     * Obtiene la receta por el folio de esta.
     * @param folio Folio de la receta a encontrar.
     * @return La receta encontrada.
     * @throws NegocioException La causa del error.
     */
    @Override
    public RecetaDTO buscarRecetaPorFolio(String folio) throws NegocioException {
        if (folio == null || folio.trim().isEmpty()) {
            throw new NegocioException("El folio de la receta no puede estar vacio.");
        }
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if (receta == null) {
                throw new NegocioException("No se encontro ninguna receta con ese folio.");
            }
            return RecetaMapper.adaptarADTO(receta);
        } catch (NegocioException ex) {
            throw ex;
        }
    }

    /**
     * Se cambia el estado de la receta dependiendo si esta se surtio
     * completamente o a pasado la fecha limite para utilizarla.
     * @param folio Folio de la receta que se le modificara el estado.
     * @param nuevoEstado Nuevo estado que se le asignara a la receta.
     * @return El resultado de la operacion.
     * @throws NegocioException La causa del error.
     */
    @Override
    public Boolean actualizarEstado(String folio, EstadoReceta nuevoEstado) throws NegocioException {
        if (folio == null || folio.trim().isEmpty() || nuevoEstado == null) {
            throw new NegocioException("Datos de actualizacion invalidos.");
        }
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if (receta == null) {
                throw new NegocioException("No se encontro la receta con folio.");
            }
            receta.setEstado(nuevoEstado);
            recetaDAO.actualizarEstadoReceta(receta);
            return true;
        } catch (NegocioException ex) {
            throw ex;
        }
    }
    
    /**
     * Resta la cantidad de medicamentos utilizables sumando la cantidad surtida a la receta.
     * @param folio Folio de la receta a la que se le sumara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le sumara la cantidad surtida.
     * @param cantidad Cantidad que se le sumara del medicamento a la cantidad surtida.
     * @return El resultado de la operacion.
     * @throws NegocioException La causa del error.
     */
    @Override
    public Boolean restarMedicamento(String folio, String idMedicamento, Integer cantidad) throws NegocioException {
        if (folio == null || idMedicamento == null || cantidad == null || cantidad <= 0) {
            throw new NegocioException("Los datos para restar el medicamento son invalidos.");
        }
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if (receta == null) {
                throw new NegocioException("La receta no existe.");
            }
            recetaDAO.restarMedicamentos(receta, idMedicamento, cantidad);
            return true;
        } catch (NegocioException ex) {
            throw ex;
        }
    }

    /**
     * Suma la cantidad de medicamentos utilizables resatando la cantidad surtida a la receta.
     * @param folio Folio de la receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad que se le restara del medicamento a la cantidad surtida.
     * @return El resultado de la operacion.
     * @throws NegocioException La causa del error.
     */
    @Override
    public Boolean sumarMedicamento(String folio, String idMedicamento, Integer cantidad) throws NegocioException {
        if (folio == null || idMedicamento == null || cantidad == null || cantidad <= 0) {
            throw new NegocioException("Los datos para sumar el medicamento son invalidos.");
        }
        try {
            Receta receta = recetaDAO.obtenerRecetaPorFolio(folio);
            if (receta == null) {
                throw new NegocioException("La receta no existe.");
            }
            recetaDAO.sumarMedicamentos(receta, idMedicamento, cantidad);
            return true;
        } catch (NegocioException ex) {
            throw ex;
        }
    }
    
}
