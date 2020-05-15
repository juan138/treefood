/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "tipo_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPersonas.findAll", query = "SELECT t FROM TipoPersonas t")
    , @NamedQuery(name = "TipoPersonas.findByIdTipoPer", query = "SELECT t FROM TipoPersonas t WHERE t.idTipoPer = :idTipoPer")
    , @NamedQuery(name = "TipoPersonas.findByTipoPer", query = "SELECT t FROM TipoPersonas t WHERE t.tipoPer = :tipoPer")})
public class TipoPersonas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_per")
    private Integer idTipoPer;
    @Column(name = "tipo_per")
    private String tipoPer;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPersona")
    private List<Personas> personasList;

    public TipoPersonas() {
    }

    public TipoPersonas(Integer idTipoPer) {
        this.idTipoPer = idTipoPer;
    }

    public Integer getIdTipoPer() {
        return idTipoPer;
    }

    public void setIdTipoPer(Integer idTipoPer) {
        this.idTipoPer = idTipoPer;
    }

    public String getTipoPer() {
        return tipoPer;
    }

    public void setTipoPer(String tipoPer) {
        this.tipoPer = tipoPer;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPer != null ? idTipoPer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPersonas)) {
            return false;
        }
        TipoPersonas other = (TipoPersonas) object;
        if ((this.idTipoPer == null && other.idTipoPer != null) || (this.idTipoPer != null && !this.idTipoPer.equals(other.idTipoPer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.TF.persistencia.TipoPersonas[ idTipoPer=" + idTipoPer + " ]";
    }
    
}
