/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso_Datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amilcar
 */
@Entity
@Table(name = "TIPO_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t"),
    @NamedQuery(name = "TipoUsuario.findByIdTipo", query = "SELECT t FROM TipoUsuario t WHERE t.idTipo = :idTipo"),
    @NamedQuery(name = "TipoUsuario.findByTipo", query = "SELECT t FROM TipoUsuario t WHERE t.tipo = :tipo")})
public class TipoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "tipo_usuario_seq", sequenceName = "tipo_usuario_seq", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="tipo_usuario_seq")
    @Column(name = "ID_TIPO")
    private Short idTipo;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @OneToMany(mappedBy = "idTipo")
    private Collection<HistorialTrabajador> historialTrabajadorCollection;
    @OneToMany(mappedBy = "idTipo")
    private Collection<Usuario> usuarioCollection;

    public TipoUsuario() {
    }

    public TipoUsuario(Short idTipo) {
        this.idTipo = idTipo;
    }

    public TipoUsuario(Short idTipo, String tipo) {
        this.idTipo = idTipo;
        this.tipo = tipo;
    }

    public Short getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Short idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public Collection<HistorialTrabajador> getHistorialTrabajadorCollection() {
        return historialTrabajadorCollection;
    }

    public void setHistorialTrabajadorCollection(Collection<HistorialTrabajador> historialTrabajadorCollection) {
        this.historialTrabajadorCollection = historialTrabajadorCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipo != null ? idTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.idTipo == null && other.idTipo != null) || (this.idTipo != null && !this.idTipo.equals(other.idTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
}
