/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso_Datos;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amilcar
 */
@Entity
@Table(name = "USUARIO_MATE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioMate.findAll", query = "SELECT u FROM UsuarioMate u"),
    @NamedQuery(name = "UsuarioMate.findByIdUsuaMate", query = "SELECT u FROM UsuarioMate u WHERE u.idUsuaMate = :idUsuaMate")})
public class UsuarioMate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "usuario_mate_seq", sequenceName = "usuario_mate_seq", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="usuario_mate_seq")
    @Column(name = "ID_USUA_MATE")
    private Short idUsuaMate;
    @JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA")
    @ManyToOne
    private Materia idMateria;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public UsuarioMate() {
    }

    public UsuarioMate(Short idUsuaMate) {
        this.idUsuaMate = idUsuaMate;
    }

    public Short getIdUsuaMate() {
        return idUsuaMate;
    }

    public void setIdUsuaMate(Short idUsuaMate) {
        this.idUsuaMate = idUsuaMate;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuaMate != null ? idUsuaMate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioMate)) {
            return false;
        }
        UsuarioMate other = (UsuarioMate) object;
        if ((this.idUsuaMate == null && other.idUsuaMate != null) || (this.idUsuaMate != null && !this.idUsuaMate.equals(other.idUsuaMate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso_Datos.UsuarioMate[ idUsuaMate=" + idUsuaMate + " ]";
    }
    
}
