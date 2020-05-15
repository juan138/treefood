/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.beans;

import com.TF.mantenimiento.GestionSucRestaurante;
import com.TF.persistencia.AreasSucursales;
import com.TF.persistencia.SucursalesRestaurantes;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan.serranoUSAM
 */
@ManagedBean
@SessionScoped
public class SucursalRest {

    private GestionSucRestaurante gr = new GestionSucRestaurante();
    private AreasSucursales area = new AreasSucursales();
    private List<SucursalesRestaurantes> sr = null;
    private int idrest;
    private String areas;

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public GestionSucRestaurante getGr() {
        return gr;
    }

    public void setGr(GestionSucRestaurante gr) {
        this.gr = gr;
    }

    public AreasSucursales getArea() {
        return area;
    }

    public void setArea(AreasSucursales area) {
        this.area = area;
    }

    public List<SucursalesRestaurantes> getSr() {
        return sr;
    }

    public void setSr(List<SucursalesRestaurantes> sr) {
        this.sr = sr;
    }

    public int getIdrest() {
        return idrest;
    }

    public void setIdrest(int idrest) {
        this.idrest = idrest;
    }

   
    
    // /////////////////******* INICIAN METODOS ***************//////////////////
    public SucursalRest() {
    }
    
    // selector para panda
    public List<SucursalesRestaurantes> sucpanda(){
        
        return gr.listSucRest(1);
    }
    
    // areas para panda
    public List<AreasSucursales> areasuc(){
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sucursal", idrest);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("areas", areas);
        
        return gr.listAreaSuc(idrest);
    }
    
    // selector para laca-laca
    public List<SucursalesRestaurantes> sucLaca(){
        return gr.listSucRest(2);
    }
    
    public void var(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sucursal", idrest);
        
        System.out.println(" reserva : "+idrest);
    }
    
}
