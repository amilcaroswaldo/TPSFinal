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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amilcar
 */
@Entity
@Table(name = "GRADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grado.findAll", query = "SELECT g FROM Grado g"),
    @NamedQuery(name = "Grado.findByIdGrado", query = "SELECT g FROM Grado g WHERE g.idGrado = :idGrado"),
    @NamedQuery(name = "Grado.findByGrado", query = "SELECT g FROM Grado g WHERE g.grado = :grado")})
public class Grado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_GRADO")
    private Short idGrado;
    @Basic(optional = false)
    @Column(name = "GRADO")
    private String grado;
    @OneToMany(mappedBy = "idGrado")
    private Collection<Seccion> seccionCollection;

    public Grado() {
    }

    public Grado(Short idGrado) {
        this.idGrado = idGrado;
    }

    public Grado(Short idGrado, String grado) {
        this.idGrado = idGrado;
        this.grado = grado;
    }

    public Short getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Short idGrado) {
        this.idGrado = idGrado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @XmlTransient
    public Collection<Seccion> getSeccionCollection() {
        return seccionCollection;
    }

    public void setSeccionCollection(Collection<Seccion> seccionCollection) {
        this.seccionCollection = seccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrado != null ? idGrado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grado)) {
            return false;
        }
        Grado other = (Grado) object;
        if ((this.idGrado == null && other.idGrado != null) || (this.idGrado != null && !this.idGrado.equals(other.idGrado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso_Datos.Grado[ idGrado=" + idGrado + " ]";
    }
    
}
