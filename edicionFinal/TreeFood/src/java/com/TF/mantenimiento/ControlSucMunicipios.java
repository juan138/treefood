/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.mantenimiento;

import com.TF.persistencia.Departamentos;
import com.TF.persistencia.Municipios;
import com.TF.persistencia.Sucursales;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author juan.serranoUSAM
 */
public class ControlSucMunicipios {
    
    //1. metodo consultar minicipios
    public List<Municipios> consultarMunicipios(){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select m from Municipios m");
            em.getTransaction().commit();
            List<Municipios> muni = query.getResultList();
            return muni;
        } catch (Exception e) {
            return null;
        }finally{
            em.clear();
        }
    }
    
    //2.  metodo consultar departamento
    public List<Departamentos> consultarDepartamentos(){
        EntityManager em = JpaUtil.getEmf().createEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select d from Departamentos d");
            em.getTransaction().commit();
            List<Departamentos> dep = query.getResultList();
            return dep;
        } catch (Exception e) {
            return null;
        }finally{
            em.clear();
        }
   }
    //3.  mustra la sucursal por su municipio
    public List<Sucursales> sucursalMunicipio(Integer id){
       EntityManager em = JpaUtil.getEmf().createEntityManager();
       em.getTransaction().begin();
        try {
            Query query = em.createQuery("select s from Sucursales s where s.municipio= :num");
            query.setParameter("num", id);
            em.getTransaction().commit();
            List<Sucursales> suc = query.getResultList();
            return suc;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }finally{
            em.close();
        }
    }
    
    
}
