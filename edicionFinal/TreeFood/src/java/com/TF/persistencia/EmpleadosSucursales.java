/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.serranoUSAM
 */
@Entity
@Table(name = "empleados_sucursales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpleadosSucursales.findAll", query = "SELECT e FROM EmpleadosSucursales e")
    , @NamedQuery(name = "EmpleadosSucursales.findByIdEmpleadoSucursal", query = "SELECT e FROM EmpleadosSucursales e WHERE e.idEmpleadoSucursal = :idEmpleadoSucursal")
    , @NamedQuery(name = "EmpleadosSucursales.findByHoraEntrada", query = "SELECT e FROM EmpleadosSucursales e WHERE e.horaEntrada = :horaEntrada")
    , @NamedQuery(name = "EmpleadosSucursales.findByHoraSalida", query = "SELECT e FROM EmpleadosSucursales e WHERE e.horaSalida = :horaSalida")})
public class EmpleadosSucursales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empleado_sucursal")
    private Integer idEmpleadoSucursal;
    @Basic(optional = false)
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Basic(optional = false)
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados idEmpleado;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursales idSucursal;

    public EmpleadosSucursales() {
    }

    public EmpleadosSucursales(Integer idEmpleadoSucursal) {
        this.idEmpleadoSucursal = idEmpleadoSucursal;
    }

    public EmpleadosSucursales(Integer idEmpleadoSucursal, Date horaEntrada, Date horaSalida) {
        this.idEmpleadoSucursal = idEmpleadoSucursal;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public Integer getIdEmpleadoSucursal() {
        return idEmpleadoSucursal;
    }

    public void setIdEmpleadoSucursal(Integer idEmpleadoSucursal) {
        this.idEmpleadoSucursal = idEmpleadoSucursal;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Empleados getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleados idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Sucursales getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursales idSucursal) {
        this.idSucursal = idSucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleadoSucursal != null ? idEmpleadoSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpleadosSucursales)) {
            return false;
        }
        EmpleadosSucursales other = (EmpleadosSucursales) object;
        if ((this.idEmpleadoSucursal == null && other.idEmpleadoSucursal != null) || (this.idEmpleadoSucursal != null && !this.idEmpleadoSucursal.equals(other.idEmpleadoSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.TF.persistencia.EmpleadosSucursales[ idEmpleadoSucursal=" + idEmpleadoSucursal + " ]";
    }
    
}
