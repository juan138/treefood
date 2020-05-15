/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import com.TF.modeloProceso.InsertarEmpleado;
import com.TF.persistencia.Empleados;
import com.TF.persistencia.EmpleadosSucursales;
import com.TF.persistencia.Personas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author juan.serranoUSAM
 */
public class ControlEmpleados {
    
    // 1. metodo para registrar empleado o gerente
    public boolean registrarEmpleado(InsertarEmpleado iemp){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            StoredProcedureQuery queryInsert = em.createNamedStoredProcedureQuery("registroEmpleado");
            queryInsert.setParameter("usua", iemp.getUsua());
            queryInsert.setParameter("pass", iemp.getPass());
            queryInsert.setParameter("nombre", iemp.getNombre());
            queryInsert.setParameter("apellido", iemp.getApellido());
            queryInsert.setParameter("eda", iemp.getEda());
            queryInsert.setParameter("tipoper", iemp.getTipoper());
            queryInsert.setParameter("idgerente", iemp.getIdgerente());
            queryInsert.setParameter("suc", iemp.getSuc());
            queryInsert.setParameter("hent", iemp.getHent());
            queryInsert.setParameter("hsal", iemp.getHsal());
            queryInsert.execute();
            
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 2.  metodo para asignar sucursal a empleado
    public boolean ingresarEmpSuc(EmpleadosSucursales emp){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(emp);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 3. actualizar horarios y sucursal de empleado
    public boolean updateSucEmpleado(EmpleadosSucursales emp){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            EmpleadosSucursales empl = em.find(EmpleadosSucursales.class, emp.getIdEmpleadoSucursal());
            empl.setIdEmpleado(emp.getIdEmpleado());
            empl.setIdSucursal(emp.getIdSucursal());
            empl.setHoraEntrada(emp.getHoraEntrada());
            empl.setHoraSalida(emp.getHoraSalida());
            em.merge(empl);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 4. actualizar emcargado de empleado
    public boolean updateEncEmpleado(Empleados emp){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Empleados empl = em.find(Empleados.class, emp.getIdEmpleado());
            empl.setIdPersona(emp.getIdPersona());
            empl.setIdGerente(emp.getIdGerente());
            em.merge(empl);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 5. actualizar los datos personales del empleado
    public boolean updatePerEmp(Personas per){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Personas pers = em.find(Personas.class, per.getIdPersona());
            pers.setNombres(per.getNombres());
            pers.setApellidos(per.getApellidos());
            pers.setEdad(per.getEdad());
            pers.setIdTipoPersona(per.getIdTipoPersona());
            em.merge(pers);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 6. mostrar empleados por sucursal
    public List<EmpleadosSucursales> selectEmpSucursal(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select e from EmpleadosSucursales e where e.idSucursal.idSucursal= :sucursal");
            query.setParameter("sucursal", id);
            em.getTransaction().commit();
            List<EmpleadosSucursales> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
    
    // 7. mostrar datos personales de empleados
    public Empleados empleadoPersona(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select e from Empleados e where e.idPersona= :persona");
            query.setParameter("persona", id);
            List<Empleados> lista = query.getResultList();
            em.getTransaction().commit();
            Empleados emp = new Empleados();
            emp.setIdEmpleado(lista.get(0).getIdEmpleado());
            emp.setIdPersona(lista.get(0).getIdPersona());
            emp.setIdGerente(lista.get(0).getIdGerente());
            return emp;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
    
    public boolean deleteEmpleado(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
         System.out.println("respuesta: "+id);
        em.getTransaction().begin();
        try {
            StoredProcedureQuery queryInsert = em.createNamedStoredProcedureQuery("eliminarEmpleado");
            queryInsert.setParameter("idpersona", id);
            
            queryInsert.execute();
            
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 9. mostrar empleados por sucursal
    public List<EmpleadosSucursales> EmpSucursal(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select e from EmpleadosSucursales e where e.idEmpleado.idEmpleado= :emp");
            query.setParameter("emp", id);
            em.getTransaction().commit();
            List<EmpleadosSucursales> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
}
