/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import com.TF.modeloProceso.InsertarSucursal;
import com.TF.persistencia.AreasSucursales;
import com.TF.persistencia.Sucursales;
import com.TF.persistencia.SucursalesRestaurantes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author juan.serranoUSAM
 */
public class GestionSucRestaurante {
    
    // 1. metodo para ingresar las sucursal y asignare el restaurante al que pertenece
    public boolean insertSucursal(InsertarSucursal insuc){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            StoredProcedureQuery insertQuery = em.createNamedStoredProcedureQuery("registroSucursal");
            insertQuery.setParameter("nombresuc", insuc.getNombresuc());
            insertQuery.setParameter("munsuc", insuc.getMunsuc());
            insertQuery.setParameter("dir", insuc.getDir());
            insertQuery.setParameter("apertura", insuc.getAperura());
            insertQuery.setParameter("cierre", insuc.getCierre());
            insertQuery.setParameter("cel", insuc.getCel());
            insertQuery.setParameter("idrest", insuc.getIdrest());
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // 2.  metodo pasra actualizar la sucursal
    public boolean updateSucursal(Sucursales suc){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Sucursales upsuc = em.find(Sucursales.class, suc.getIdSucursal());
            upsuc.setSucursal(suc.getSucursal());
            upsuc.setMunicipio(suc.getMunicipio());
            upsuc.setDireccion(suc.getDireccion());
            upsuc.setHoraApertura(suc.getHoraApertura());
            upsuc.setHoraCierre(suc.getHoraCierre());
            upsuc.setTelefono(suc.getTelefono());
            em.merge(upsuc);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 3.  metodo para mostrar las sucursales que posee el restaurante
    public List<SucursalesRestaurantes> listSucRest(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select s from SucursalesRestaurantes s where s.idRestaurante.idRestaurante= :rest");
            query.setParameter("rest", id);
            em.getTransaction().commit();
            List<SucursalesRestaurantes> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            return null;
        }
    }

    // 4. metodo listar sucursal por id permitira consultarla para ser modificada
    public Sucursales sucById(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select s from Sucursales s where s.idSucursal= :suc");
            query.setParameter("suc", id);
            em.getTransaction().commit();
            List<Sucursales> lista = query.getResultList();
            Sucursales suc = new Sucursales();
            suc.setIdSucursal(lista.get(0).getIdSucursal());
            suc.setSucursal(lista.get(0).getSucursal());
            suc.setMunicipio(lista.get(0).getMunicipio());
            suc.setDireccion(lista.get(0).getDireccion());
            suc.setHoraApertura(lista.get(0).getHoraApertura());
            suc.setHoraCierre(lista.get(0).getHoraCierre());
            suc.setTelefono(lista.get(0).getTelefono());
            
            return suc;
        } catch (Exception e) {
            em.getTransaction().rollback();
           return null; 
        }finally{
            em.close();
        }
    }
    
    // 5.  metodo para mostra las areas que posee la sucursal
    public List<AreasSucursales> listAreaSuc(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select a from AreasSucursales a where a.idSucursal.idSucursal= :suc");
            query.setParameter("suc", id);
            em.getTransaction().commit();
            List<AreasSucursales> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.clear();
        }
    }
    
    // 6. metodo para ingresar una area para la sucursal
    public boolean insertAreaSuc(AreasSucursales ar){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(ar);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    //7. metodo para eliminar la area de la sucursal
    public boolean eliminarArea(int id){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            AreasSucursales ar = em.find(AreasSucursales.class, id);
            em.remove(ar);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        }finally{
            em.close();
        }
    }
    
    // 8. metodo para actualizar el nombre del area
    public boolean updateArea(AreasSucursales ars){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            AreasSucursales ar = em.find(AreasSucursales.class, ars.getIdArea());
            ar.setArea(ars.getArea());
            ar.setIdSucursal(ars.getIdSucursal());
            em.merge(ar);
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
