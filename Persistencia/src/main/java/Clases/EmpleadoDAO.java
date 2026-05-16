/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Empleado;
import EntidadesMongo.EmpleadoMongo;
import Enums.EstatusEmpleado;
import Enums.RolPuesto;
import Interfaces.IEmpleadoDAO;
import MapperMongo.EmpleadoMapperMongo;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Benjamin
 */
public class EmpleadoDAO implements IEmpleadoDAO{
/*
   private List<Empleado> tablaEmpleados;

    public EmpleadoDAO() {
        tablaEmpleados = new ArrayList<>();
        
        // Mockeamos usando Entidades (nota que ya no usamos el DTO aquí)
        Empleado emp1 = new Empleado(123L, "Juan", "Perez", "Gomez", "555-0001", 
                                     RolPuesto.LIDER, LocalDate.of(1990, 5, 20), EstatusEmpleado.ACTIVO);
                                     
        Empleado emp2 = new Empleado(456L, "Maria", "Lopez", "Diaz", "555-0002", 
                                     RolPuesto.CAJERO, LocalDate.of(1995, 8, 15), EstatusEmpleado.ACTIVO);
                                     
        Empleado emp3 = new Empleado(789L, "Carlos", "Ruiz", "Soto", "555-0003", 
                                     RolPuesto.CAJERO, LocalDate.of(1998, 2, 10), EstatusEmpleado.INACTIVO);

        tablaEmpleados.add(emp1);
        tablaEmpleados.add(emp2);
        tablaEmpleados.add(emp3);
    }

    @Override
    public Empleado obtenerEmpleadoPorId(Long idEmpleado) {
        for (Empleado empleado : tablaEmpleados) {
            if (Objects.equals(empleado.getIdEmpleado(), idEmpleado)) {
                return empleado;
            }
        }
        return null;
    }
    */
    
    //coleccion de empleados de mongo
    private MongoCollection<EmpleadoMongo> coleccionEmpleados;
    
    public EmpleadoDAO(){
        //se obtiene la coleccion de empleados de mongo
        this.coleccionEmpleados = ManejadorConexiones.obtenerColeccionEmpleados();
    }
    
    /**
     * Metodo que obtiene un empleado por id, lo busca en la base de datos y 
     * recupera la entidad mongo y regresa la entidad convertida usando el mapper
     * @param idEmpleado
     * @return 
     */
    @Override
    public Empleado obtenerEmpleadoPorId(String idEmpleado){
        // lo busca en la bd y lo devuelve como mongo empleado
        EmpleadoMongo resultadoMongo = coleccionEmpleados.find(eq("idEmpleado", idEmpleado)).first();
        
        // el mapper lo pasa a entidad java para que todo fluya bien
        return EmpleadoMapperMongo.aEntidadJava(resultadoMongo);
    }        
}
