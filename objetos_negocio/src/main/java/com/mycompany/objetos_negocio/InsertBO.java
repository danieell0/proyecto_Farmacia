/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import Clases.insertDAO;
import IBO.IInsertBO;
import Interfaces.IInsertDAO;

/**
 *
 * @author Jorge
 */
public class InsertBO implements IInsertBO {

    private insertDAO insertDAO;

    public InsertBO() {
        insertDAO = new insertDAO();
    }

    @Override
    public void cargarDatos() {
        insertDAO.cargarDatos();
    }

}
