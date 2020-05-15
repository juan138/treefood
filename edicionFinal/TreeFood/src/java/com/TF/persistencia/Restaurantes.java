/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.serranoUSAM
 */
@Entity
@Table(name = "restaurantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurantes.findAll", query = "SELECT r FROM Restaurantes r")
    , @NamedQuery(name = "Restaurantes.findByIdRestaurante", query = "SELECT r FROM Restaurantes r WHERE r.idRestaurante = :idRestaurante")
    , @NamedQuery(name = "Restaurantes.findByNombreSociedad", query = "SELECT r FROM Restaurantes r WHERE r.nombreSociedad = :nombreSociedad")
    , @NamedQuery(name = "Restaurantes.findByNombreEmpresa", query = "SELECT r FROM Restaurantes r WHERE r.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Restaurantes.findByTelefonoColcenter", query = "SELECT r FROM Restaurantes r WHERE r.telefonoColcenter = :telefonoColcenter")
    , @NamedQuery(name = "Restaurantes.findByDireccionOficina", query = "SELECT r FROM Restaurantes r WHERE r.direccionOficina = :direccionOficina")})
public class Restaurantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_restaurante")
    private Integer idRestaurante;
    @Column(name = "nombre_sociedad")
    private String nombreSociedad;
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name = "telefono_colcenter")
    private String telefonoColcenter;
    @Column(name = "direccion_oficina")
    private String direccionOficina;
    @OneToMany(mappedBy = "idRestaurante")
    private List<SucursalesRestaurantes> sucursalesRestaurantesList;

    public Restaurantes() {
    }

    public Restaurantes(Integer idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public Integer getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Integer idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombreSociedad() {
        return nombreSociedad;
    }

    public void setNombreSociedad(String nombreSociedad) {
        this.nombreSociedad = nombreSociedad;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTelefonoColcenter() {
        return telefonoColcenter;
    }

    public void setTelefonoColcenter(String telefonoColcenter) {
        this.telefonoColcenter = telefonoColcenter;
    }

    public String getDireccionOficina() {
        return direccionOficina;
    }

    public void setDireccionOficina(String direccionOficina) {
        this.direccionOficina = direccionOficina;
    }

    @XmlTransient
    public List<SucursalesRestaurantes> getSucursalesRestaurantesList() {
        return sucursalesRestaurantesList;
    }

    public void setSucursalesRestaurantesList(List<SucursalesRestaurantes> sucursalesRestaurantesList) {
        this.sucursalesRestaurantesList = sucursalesRestaurantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRestaurante != null ? idRestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurantes)) {
            return false;
        }
        Restaurantes other = (Restaurantes) object;
        if ((this.idRestaurante == null && other.idRestaurante != null) || (this.idRestaurante != null && !this.idRestaurante.equals(other.idRestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.TF.persistencia.Restaurantes[ idRestaurante=" + idRestaurante + " ]";
    }
    
}
