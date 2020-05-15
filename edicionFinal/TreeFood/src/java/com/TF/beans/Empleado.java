/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.beans;

import com.TF.mantenimiento.ControlEmpleados;
import com.TF.modeloProceso.InsertarEmpleado;
import com.TF.persistencia.Empleados;
import com.TF.persistencia.EmpleadosSucursales;
import com.TF.persistencia.Personas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author juan.serranoUSAM
 */
@ManagedBean
@RequestScoped
public class Empleado {

    /**
     * Creates a new instance of Empleado
     */
    public Empleado() {
    }

    private Empleados emp = new Empleados();
    private Personas per = new Personas();
    private InsertarEmpleado iemp= new InsertarEmpleado();
    private EmpleadosSucursales empsucursal = new EmpleadosSucursales();
    private String horaEnt;
    private String horaSal;
    private String tipo;
    
    private ControlEmpleados cEmp = new ControlEmpleados();

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHoraEnt() {
        return horaEnt;
    }

    public void setHoraEnt(String horaEnt) {
        this.horaEnt = horaEnt;
    }

    public String getHoraSal() {
        return horaSal;
    }

    public void setHoraSal(String horaSal) {
        this.horaSal = horaSal;
    }

    
    public Empleados getEmp() {
        return emp;
    }

    public void setEmp(Empleados emp) {
        this.emp = emp;
    }

    public Personas getPer() {
        return per;
    }

    public void setPer(Personas per) {
        this.per = per;
    }

    public InsertarEmpleado getIemp() {
        return iemp;
    }

    public void setIemp(InsertarEmpleado iemp) {
        this.iemp = iemp;
    }

    public EmpleadosSucursales getEmpsucursal() {
        return empsucursal;
    }

    public void setEmpsucursal(EmpleadosSucursales empsucursal) {
        this.empsucursal = empsucursal;
    }

    
    public ControlEmpleados getcEmp() {
        return cEmp;
    }

  
    

    public void setcEmp(ControlEmpleados cEmp) {
        this.cEmp = cEmp;
    }

     public List<EmpleadosSucursales> listarEmpleados() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        List<EmpleadosSucursales> listEmp = new ArrayList<>();

        try {

            Integer suc = (Integer) context.getSessionMap().get("sucursal");
            if (suc == null) {
                suc = 0;
            }

            listEmp = cEmp.selectEmpSucursal(suc);

        } catch (Exception e) {
            System.out.println("error listar empleados() " + e);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("er", e);
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistaError.xhtml");
        }
        return listEmp;
    }

    public void eliminarEmpleado(int id) throws IOException {
        try {
            boolean resp = cEmp.deleteEmpleado(id);
            if (resp) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("gerente.xhtml");
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("er", e);
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistaError.xhtml");
        }
    }

    public void llenarEditEmpleado(int id) throws IOException {
        try {
            ControlEmpleados cemp = new ControlEmpleados();
            List<EmpleadosSucursales> lista = cemp.EmpSucursal(id);

            per.setIdPersona(lista.get(0).getIdEmpleado().getIdPersona().getIdPersona());
            per.setNombres(lista.get(0).getIdEmpleado().getIdPersona().getNombres());
            per.setApellidos(lista.get(0).getIdEmpleado().getIdPersona().getApellidos());
            per.setEdad(lista.get(0).getIdEmpleado().getIdPersona().getEdad());

            FacesContext.getCurrentInstance().getExternalContext().redirect("gerentemodificarempleado.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("er", e);
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistaError.xhtml");
        }
    }
    
    public void updateEmpleado() throws IOException{
        try {
            ControlEmpleados cemp = new ControlEmpleados();
            cemp.updatePerEmp(per);
            FacesContext.getCurrentInstance().getExternalContext().redirect("gerente.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("er", e);
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistaError.xhtml");
        }
    }
    
    public void nuevoEmpleado() throws IOException{
        try {
            ControlEmpleados cemp = new ControlEmpleados();
            iemp.setSuc((Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sucursal"));
            iemp.setHent(horaEnt);
            iemp.setHsal(horaSal);
            iemp.setTipoper(Integer.parseInt(tipo));
            System.out.println("respuesta: "+cemp.registrarEmpleado(iemp));
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("er", e);
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistaError.xhtml");
        }finally{
            horaEnt ="";
            horaSal="";
        }
    }
    
    

}
