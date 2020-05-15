/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import com.TF.persistencia.Reservas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author juan.serranoUSAM
 */
public class ControlReservas {
    
    //1. Metodo para registrar una nueva reserva
    public boolean ingresarReserva(Reservas re){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(re);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 2. Metodo para actualizar reserva
    public boolean actualizarReserva( Reservas re){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
          Reservas res = em.find(Reservas.class, re.getIdReserva());
          res.setIdCliente(re.getIdCliente());
          res.setFechaReserva(re.getFechaReserva());
          res.setSucursal(re.getSucursal());
          res.setCantidadComensales(re.getCantidadComensales());
          res.setNumeroContacto(re.getNumeroContacto());
          res.setEmail(re.getEmail());
          res.setEstado(re.getEstado());
          res.setAreaRestaurante(re.getAreaRestaurante());
          em.merge(res);
          em.getTransaction().commit();
          return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 3.  consultar las reservas por id sera usada para modificarla
    public Reservas reservaById(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Reservas re = em.find(Reservas.class, id);
            em.getTransaction().commit();
            return re;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
    
    // 4. permite consultar las reservas que tiene el cliente
    public List<Reservas> reservasCliente(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select r from Reservas r where r.idCliente.idCliente= :cliente and r.estado= :est");
            query.setParameter("cliente", id);
            query.setParameter("est", "activa");
            em.getTransaction().commit();
            List<Reservas> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
    
    // 5.  permite consultar las reservas por cada sucursal en especifico
    public List<Reservas> reservaSucursal(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select r from Reservas r where r.sucursal.idSucursal= :suc and r.estado= :est");
            query.setParameter("suc",id);
            query.setParameter("est", "activa");
            em.getTransaction().commit();
            List<Reservas> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
    
}
