/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.beans;

import com.TF.mantenimiento.ControlReservas;
import com.TF.persistencia.Clientes;
import com.TF.persistencia.Reservas;
import com.TF.persistencia.Sucursales;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan.serranoUSAM
 */
@ManagedBean
@SessionScoped
public class Reserva {

    private ControlReservas cre = new ControlReservas();
    private Reservas re = new Reservas();
    private String fecha;
    private String areas;
    private int are;

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Reservas getRe() {
        return re;
    }

    public void setRe(Reservas re) {
        this.re = re;
    }

    public Reserva() {
    }

    public ControlReservas getCre() {
        return cre;
    }

    public void setCre(ControlReservas cre) {
        this.cre = cre;
    }

    // ////////////////////******* INICIAN LOS METODOS **********//////////////////////
    // reservas q posee el cliente
    public List<Reservas> listarReservas() {
        int per = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cli");

        List<Reservas> reser = null;
        reser = cre.reservasCliente(per);
        return reser;
    }

    // registrar reserva
    public void reservar() {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Clientes cli = new Clientes();
            cli.setIdCliente((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cli"));
            re.setIdCliente(cli);
            Sucursales s = new Sucursales();
            s.setIdSucursal((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sucursal"));
            re.setSucursal(s);
            re.setAreaRestaurante(areas);
            re.setEstado("activa");
            System.out.println("reserva: " + fecha);
            re.setFechaReserva(formato.parse(fecha));
            cre.ingresarReserva(re);
        } catch (Exception e) {
            System.out.println("error en reservar(): " + e);
        }

    }

    public List<Reservas> reservasSucursal() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        List<Reservas> reser = new ArrayList<Reservas>();
        try {
            Integer suc = (Integer) context.getSessionMap().get("sucursal");

            if (suc == null) {
                suc = 0;
            }

            reser = cre.reservaSucursal(suc);
            context.getSessionMap().put("sucName", reser.get(0).getSucursal().getSucursal());

        } catch (Exception e) {
        }
        return reser;
    }
}
