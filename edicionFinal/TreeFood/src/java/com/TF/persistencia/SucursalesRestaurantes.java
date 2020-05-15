/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.persistencia;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author juan.serranoUSAM
 */
@Entity
@Table(name = "sucursales_restaurantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalesRestaurantes.findAll", query = "SELECT s FROM SucursalesRestaurantes s")
    , @NamedQuery(name = "SucursalesRestaurantes.findByIdPivoSucursalRestaurante", query = "SELECT s FROM SucursalesRestaurantes s WHERE s.idPivoSucursalRestaurante = :idPivoSucursalRestaurante")})
public class SucursalesRestaurantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pivo_sucursal_restaurante")
    private Integer idPivoSucursalRestaurante;
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante")
    @ManyToOne
    private Restaurantes idRestaurante;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne
    private Sucursales idSucursal;

    public SucursalesRestaurantes() {
    }

    public SucursalesRestaurantes(Integer idPivoSucursalRestaurante) {
        this.idPivoSucursalRestaurante = idPivoSucursalRestaurante;
    }

    public Integer getIdPivoSucursalRestaurante() {
        return idPivoSucursalRestaurante;
    }

    public void setIdPivoSucursalRestaurante(Integer idPivoSucursalRestaurante) {
        this.idPivoSucursalRestaurante = idPivoSucursalRestaurante;
    }

    public Restaurantes getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Restaurantes idRestaurante) {
        this.idRestaurante = idRestaurante;
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
        hash += (idPivoSucursalRestaurante != null ? idPivoSucursalRestaurante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SucursalesRestaurantes)) {
            return false;
        }
        SucursalesRestaurantes other = (SucursalesRestaurantes) object;
        if ((this.idPivoSucursalRestaurante == null && other.idPivoSucursalRestaurante != null) || (this.idPivoSucursalRestaurante != null && !this.idPivoSucursalRestaurante.equals(other.idPivoSucursalRestaurante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.TF.persistencia.SucursalesRestaurantes[ idPivoSucursalRestaurante=" + idPivoSucursalRestaurante + " ]";
    }
    
}
