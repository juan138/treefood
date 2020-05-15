/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.beans;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author master
 */
@ManagedBean
@RequestScoped
public class ErrorVista {

    /**
     * Creates a new instance of ErrorVista
     */
    public ErrorVista() {
    }
    
    private Exception error;

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }
    
    public void error() throws IOException{
        this.error = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("er");
    }
    
}
