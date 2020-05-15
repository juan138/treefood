/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.beans;

import com.TF.mantenimiento.ValidacionUsuarios;
import com.TF.modeloProceso.InsertarCliente;
import com.TF.persistencia.Usuarios;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author juan.serranoUSAM
 */
@ManagedBean
@SessionScoped
public class Usuario {

    private ValidacionUsuarios val_user = new ValidacionUsuarios();
    private Usuarios user = new Usuarios();
    private InsertarCliente incliente = new InsertarCliente();

    // ///////////////********** GETTERS Y SETTERS************/////////////// 
    public Usuario() {
    }

    public InsertarCliente getIncliente() {
        return incliente;
    }

    public void setIncliente(InsertarCliente incliente) {
        this.incliente = incliente;
    }

    public ValidacionUsuarios getVal_user() {
        return val_user;
    }

    public void setVal_user(ValidacionUsuarios val_user) {
        this.val_user = val_user;
    }

    public Usuarios getUser() {
        return user;
    }

    public void setUser(Usuarios user) {
        this.user = user;
    }

    // ///////////////////******** INICIO DE METODOS ***********//////////////////////
    // metodo para registrar un nuevo-cliente
    public void nuevoCliente() throws IOException {
        String msg = "";
        boolean cor = val_user.correoCliente(incliente.getCorreo());
        if (cor) {
            msg = "correo existente";
        } else {
            boolean ncli = val_user.nuevoCliente(incliente);
            if (ncli) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                msg = "sus datos se guardaron con exito";
            } else {
                msg = "error al registrarse";
            }
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    // metodo para validar usuario
    public void login(Usuarios use) {
        String msg = "lol";
        Usuarios us = new Usuarios();
        us = val_user.validarUsuario(use);

        if (us != null) {
            msg = "usuario si existe";

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipo", us.getIdPersona().getIdTipoPersona().getIdTipoPer());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", us.getNickname());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("persona", us.getIdPersona().getIdPersona());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idus", us.getIdUsuario());
            
            sesion();
        } else {
            msg = "usuario no existe";
        }
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Your message: " + msg));
    }

    // metodo valida la sesion
    public void sesion() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            Integer tipo = (Integer) context.getSessionMap().get("tipo");
            System.out.println("tipo :" + tipo);
            if (tipo != null) {
                if (tipo == 1) {
                    context.redirect("perfil.xhtml");
                    
                } else if (tipo == 2) {
                    context.redirect("vistaEmpleadoPanda.xhtml");
                } else if (tipo == 3) {
                    context.redirect("gerente.xhtml");
                }
            } else {
                context.redirect("index.xhtml");
            }
        } catch (Exception e) {
            System.out.println("error de sesion " + e);
        }
    }

    // metodo para cerrar y limpiar las variables de sesion
    public void cerrarSesion() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.getSessionMap().clear();
        Integer tipo = (Integer) context.getSessionMap().get("tipo");
        
        context.redirect("index.xhtml");
    }
    
    
    // metodo para validar la sesion
    public void validarSession() throws IOException{
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Integer tipo = (Integer) context.getSessionMap().get("tipo");
        if(tipo == null){
            context.redirect("index.xhtml");
        }
    }
    
}
