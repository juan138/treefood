/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TF.persistencia;

import java.io.Serializable;
import java.sql.Time;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author juan.serranoUSAM
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p")
    , @NamedQuery(name = "Personas.findByIdPersona", query = "SELECT p FROM Personas p WHERE p.idPersona = :idPersona")
    , @NamedQuery(name = "Personas.findByNombres", query = "SELECT p FROM Personas p WHERE p.nombres = :nombres")
    , @NamedQuery(name = "Personas.findByApellidos", query = "SELECT p FROM Personas p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Personas.findByEdad", query = "SELECT p FROM Personas p WHERE p.edad = :edad")})

@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "registroCliente",
        procedureName = "insertCliente",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="usua"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="pass"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="nombre"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="apellido"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="edad"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="tp"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="correo"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="tel")
        }),
        
        @NamedStoredProcedureQuery(
        name="actualizarCliente",
        procedureName = "updateCliente",
        parameters={
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name="idper"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name="nombre"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name="apellido"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class, name="edad"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name="correo"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "tel")
        }),
        
        @NamedStoredProcedureQuery(
        name="registroEmpleado",
        procedureName = "insertEmpleado",
        parameters={
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="usua"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="pass"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="nombre"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="apellido"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="eda"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="tipoper"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="idgerente"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="suc"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="hent"),
            @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class,name="hsal")
        }),
        
        @NamedStoredProcedureQuery(
        name="eliminarEmpleado",
        procedureName = "deleteEmpleado",
        parameters={
            @StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="idpersona")
        })
})

public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "edad")
    private Integer edad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Empleados> empleadosList;
    @OneToMany(mappedBy = "idPersona")
    private List<Usuarios> usuariosList;
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id_tipo_per")
    @ManyToOne(optional = false)
    private TipoPersonas idTipoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Clientes> clientesList;

    public Personas() {
    }

    public Personas(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @XmlTransient
    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    @XmlTransient
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public TipoPersonas getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(TipoPersonas idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    @XmlTransient
    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.TF.persistencia.Personas[ idPersona=" + idPersona + " ]";
    }
    
}
