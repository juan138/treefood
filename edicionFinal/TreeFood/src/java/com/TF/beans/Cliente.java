/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.beans;

import com.TF.mantenimiento.ControlReservas;
import com.TF.mantenimiento.ValidacionUsuarios;
import com.TF.modeloProceso.InsertarCliente;
import com.TF.persistencia.Clientes;
//import com.TF.persistencia.Personas;
import com.TF.persistencia.Reservas;
import com.TF.persistencia.Usuarios;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import org.primefaces.component.focus.Focus;

/** 
 *
 * @author juan.serranoUSAM
 */ 
@ManagedBean
@SessionScoped
public class Cliente {

    public Cliente() {
    }

    private Usuarios us = new Usuarios();
    private Clientes cli = new Clientes();
    private Reservas res = new Reservas();
    
    private ValidacionUsuarios vu = new ValidacionUsuarios();
    private ControlReservas cre = new ControlReservas();
    private int pers;
    private String user = "";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Usuarios getUs() {
        return us;
    }

    public void setUs(Usuarios us) {
        this.us = us;
    }

    public Clientes getCli() {
        return cli;
    }

    public void setCli(Clientes cli) {
        this.cli = cli;
    }

    public Reservas getRes() {
        return res;
    }

    public void setRes(Reservas res) {
        this.res = res;
    }

    public ValidacionUsuarios getVu() {
        return vu;
    }

    public void setVu(ValidacionUsuarios vu) {
        this.vu = vu;
    }

    public ControlReservas getCre() {
        return cre;
    }

    public void setCre(ControlReservas cre) {
        this.cre = cre;
    }

    // ////////////////////*********** INICIO DE METODOS ***********//////////////////
    // metodo para cargar datos personales
    public void datosPersonales() throws IOException {
         ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            cli =null;
            pers = (int) context.getSessionMap().get("persona");
            user = (String) context.getSessionMap().get("user");
            
            this.cli = vu.clientePersona(pers);
            context.getSessionMap().put("cli", cli.getIdCliente());
            
        } catch (Exception e) {
            System.out.println("error datosPersonales():  " + e);
            context.redirect("index.xhtml");
        }
    }

    // metodo para modificar los datos personales del cliente
    public void updateCliente() {
        try {
            InsertarCliente icli = new InsertarCliente();
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            icli.setIdper(pers);
            icli.setNombre(cli.getIdPersona().getNombres());
            icli.setApellido(cli.getIdPersona().getApellidos());
            icli.setEdad(cli.getIdPersona().getEdad());
            icli.setCorreo(cli.getEmail());
            icli.setTel(cli.getTelefono());
            vu.updateCliente(icli);
            updateUser();
            
            context.redirect("perfil.xhtml");
            
        } catch (Exception e) {
            System.out.println("error updateCliente():  " + e);
        }

    } 

    // 
    public void updateUser() {
        try {
            
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        int idus = (int) context.getSessionMap().get("idus");
        
        us.setIdUsuario(idus);
        us.setNickname(user);
        
        boolean resp = vu.updateUsuario(us);
        this.us.setPass("");
        
 
        } catch (Exception e) {
            System.out.println("error updateUser(): "+e);
        }
        
    } 
    
    
}
