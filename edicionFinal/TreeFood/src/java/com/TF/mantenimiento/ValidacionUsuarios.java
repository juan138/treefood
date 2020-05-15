/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import com.TF.modeloProceso.InsertarCliente;
import com.TF.persistencia.Clientes;
import com.TF.persistencia.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author juan.serranoUSAM
 */
public class ValidacionUsuarios {


    // 1.  este metodo seria el login o iniciar sesion
    public Usuarios validarUsuario(Usuarios us) {
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        Usuarios user = null;
        try {
            
            Query query = em.createQuery("select u from Usuarios u where u.nickname= :user and u.pass= :contra");
            query.setParameter("user", us.getNickname());
            query.setParameter("contra", us.getPass());
            List<Usuarios> use = query.getResultList();
            user = new Usuarios();
            user.setNickname(use.get(0).getNickname());
            user.setPass(use.get(0).getPass());
            user.setIdPersona(use.get(0).getIdPersona());
            user.setIdUsuario(use.get(0).getIdUsuario());
            em.close();
            return user;
        } catch (Exception e) {
            System.out.println("esto es un error ************////////////********* "+e);
            return null;
        } finally {
            
        }
    }
    
    //2.  metodo permite balidar si el correo ya existe en la base de datos
    public boolean correoCliente(String cor){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select c from Clientes c where c.email= :correo");
            query.setParameter("correo", cor);
            em.getTransaction().commit();
            List<Clientes> cli = query.getResultList();
            if(cli.size() > 0){
                return true;
            }else{
                return false;
            }
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }

    //3.  este metodo permite consultar  los datos como correo y otros segun el codigo de persona 
    // que se obtendra al iniciar sesion
    public Clientes clientePersona(int id_persona){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
         List<Clientes> cli = null;
         Clientes cl =null;
        try {
            Query query = em.createQuery("select c from Clientes c where c.idPersona.idPersona= :persona");
            query.setParameter("persona", id_persona);
            em.getTransaction().commit();
            cli = query.getResultList();
            query.getResultList().clear();
            cl = new Clientes();
            cl.setIdCliente(cli.get(0).getIdCliente());
            cl.setTelefono(cli.get(0).getTelefono());
            cl.setEmail(cli.get(0).getEmail());
            cl.setIdPersona(cli.get(0).getIdPersona());
             
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            cli.clear();
            em.close();
        }
        return cl; 
    }
    
    //4.  crear nuevo USUARIO o cliente
    public boolean nuevoCliente(InsertarCliente inc){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
           StoredProcedureQuery queryInsert = em.createNamedStoredProcedureQuery("registroCliente");
           queryInsert.setParameter("usua", inc.getUsua());
           queryInsert.setParameter("pass", inc.getPass());
           queryInsert.setParameter("nombre", inc.getNombre());
           queryInsert.setParameter("apellido", inc.getApellido());
           queryInsert.setParameter("edad", inc.getEdad());
           queryInsert.setParameter("tp", 1);
           queryInsert.setParameter("correo", inc.getCorreo());
           queryInsert.setParameter("tel", inc.getTel());
           
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
    
    //5.  metodo para modificar los datos personales del cliente
    public boolean updateCliente(InsertarCliente inc){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            StoredProcedureQuery queryUpdate = em.createNamedStoredProcedureQuery("actualizarCliente");
            queryUpdate.setParameter("idper", inc.getIdper());
            queryUpdate.setParameter("nombre", inc.getNombre());
            queryUpdate.setParameter("apellido", inc.getApellido());
            queryUpdate.setParameter("edad", inc.getEdad());
            queryUpdate.setParameter("correo", inc.getCorreo());
            queryUpdate.setParameter("tel", inc.getTel());
            
            queryUpdate.execute();
            
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("error en el procedimiento: "+e);
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    //6.  metodo para actualizar usuario y contraseña
    public boolean updateUsuario(Usuarios us){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Usuarios user = em.find(Usuarios.class, us.getIdUsuario());
            user.setNickname(us.getNickname());
            user.setPass(us.getPass());
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    //7.  metodo para eliminar usuario
    public boolean eliminarUsuario(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
           Usuarios us = em.find(Usuarios.class, id);
           em.remove(us);
           em.getTransaction().commit();
           return true;
        } catch (Exception e) {
          em.getTransaction().rollback();
          return false;
        }finally{
            em.close();
        }
    }
}
