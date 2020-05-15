/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.persistencia;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.serranoUSAM
 */
@Entity
@Table(name = "sucursales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursales.findAll", query = "SELECT s FROM Sucursales s")
    , @NamedQuery(name = "Sucursales.findByIdSucursal", query = "SELECT s FROM Sucursales s WHERE s.idSucursal = :idSucursal")
    , @NamedQuery(name = "Sucursales.findBySucursal", query = "SELECT s FROM Sucursales s WHERE s.sucursal = :sucursal")
    , @NamedQuery(name = "Sucursales.findByDireccion", query = "SELECT s FROM Sucursales s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Sucursales.findByHoraApertura", query = "SELECT s FROM Sucursales s WHERE s.horaApertura = :horaApertura")
    , @NamedQuery(name = "Sucursales.findByHoraCierre", query = "SELECT s FROM Sucursales s WHERE s.horaCierre = :horaCierre")
    , @NamedQuery(name = "Sucursales.findByTelefono", query = "SELECT s FROM Sucursales s WHERE s.telefono = :telefono")})

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "registroSucursal",
            procedureName = "insertSucursal",
            parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "nombresuc"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = int.class, name = "munsuc"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "dir"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Time.class, name = "apertura"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = Time.class, name = "cierre"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "cel"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = int.class, name = "idrest")
            })
})

public class Sucursales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private Integer idSucursal;
    @Column(name = "sucursal")
    private String sucursal;
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "hora_apertura")
    @Temporal(TemporalType.TIME)
    private Date horaApertura;
    @Basic(optional = false)
    @Column(name = "hora_cierre")
    @Temporal(TemporalType.TIME)
    private Date horaCierre;
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "idSucursal")
    private List<SucursalesRestaurantes> sucursalesRestaurantesList;
    @OneToMany(mappedBy = "sucursal")
    private List<Reservas> reservasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal")
    private List<EmpleadosSucursales> empleadosSucursalesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal")
    private List<AreasSucursales> areasSucursalesList;
    @JoinColumn(name = "municipio", referencedColumnName = "id_municipio")
    @ManyToOne
    private Municipios municipio;

    public Sucursales() {
    }

    public Sucursales(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Sucursales(Integer idSucursal, Date horaApertura, Date horaCierre) {
        this.idSucursal = idSucursal;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(Date horaApertura) {
        this.horaApertura = horaApertura;
    }

    public Date getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(Date horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<SucursalesRestaurantes> getSucursalesRestaurantesList() {
        return sucursalesRestaurantesList;
    }

    public void setSucursalesRestaurantesList(List<SucursalesRestaurantes> sucursalesRestaurantesList) {
        this.sucursalesRestaurantesList = sucursalesRestaurantesList;
    }

    @XmlTransient
    public List<Reservas> getReservasList() {
        return reservasList;
    }

    public void setReservasList(List<Reservas> reservasList) {
        this.reservasList = reservasList;
    }

    @XmlTransient
    public List<EmpleadosSucursales> getEmpleadosSucursalesList() {
        return empleadosSucursalesList;
    }

    public void setEmpleadosSucursalesList(List<EmpleadosSucursales> empleadosSucursalesList) {
        this.empleadosSucursalesList = empleadosSucursalesList;
    }

    @XmlTransient
    public List<AreasSucursales> getAreasSucursalesList() {
        return areasSucursalesList;
    }

    public void setAreasSucursalesList(List<AreasSucursales> areasSucursalesList) {
        this.areasSucursalesList = areasSucursalesList;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSucursal != null ? idSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursales)) {
            return false;
        }
        Sucursales other = (Sucursales) object;
        if ((this.idSucursal == null && other.idSucursal != null) || (this.idSucursal != null && !this.idSucursal.equals(other.idSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.TF.persistencia.Sucursales[ idSucursal=" + idSucursal + " ]";
    }
    
}
