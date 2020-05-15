/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.modeloProceso;

import java.sql.Time;

/**
 *
 * @author juan.serranoUSAM
 */
public class InsertarSucursal {
    private String nombresuc;
    private int munsuc; 
    private String dir; 
    private Time aperura;
    private Time cierre;
    private String cel;
    private int idrest;

    public String getNombresuc() {
        return nombresuc;
    }

    public void setNombresuc(String nombresuc) {
        this.nombresuc = nombresuc;
    }

    public int getMunsuc() {
        return munsuc;
    }

    public void setMunsuc(int munsuc) {
        this.munsuc = munsuc;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Time getAperura() {
        return aperura;
    }

    public void setAperura(Time aperura) {
        this.aperura = aperura;
    }

    public Time getCierre() {
        return cierre;
    }

    public void setCierre(Time cierre) {
        this.cierre = cierre;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public int getIdrest() {
        return idrest;
    }

    public void setIdrest(int idrest) {
        this.idrest = idrest;
    }
    
    
}
