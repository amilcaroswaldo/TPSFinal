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
@Table(name = "MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria"),
    @NamedQuery(name = "Materia.findByMateria", query = "SELECT m FROM Materia m WHERE m.materia = :materia")})
public class Materia implements Serializable {
    @OneToMany(mappedBy = "idMateria")
    private Collection<UsuarioMate> usuarioMateCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
     @SequenceGenerator(name = "materia_seq", sequenceName = "materia_seq", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="materia_seq")
    @Column(name = "ID_MATERIA")
    private Short idMateria;
    @Basic(optional = false)
    @Column(name = "MATERIA")
    private String materia;

    public Materia() {
    }

    public Materia(Short idMateria) {
        this.idMateria = idMateria;
    }

    public Materia(Short idMateria, String materia) {
        this.idMateria = idMateria;
        this.materia = materia;
    }

    public Short getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Short idMateria) {
        this.idMateria = idMateria;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return materia;
    }

    @XmlTransient
    public Collection<UsuarioMate> getUsuarioMateCollection() {
        return usuarioMateCollection;
    }

    public void setUsuarioMateCollection(Collection<UsuarioMate> usuarioMateCollection) {
        this.usuarioMateCollection = usuarioMateCollection;
    }
    
}
