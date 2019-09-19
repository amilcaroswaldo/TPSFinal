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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amilcar
 */
@Entity
@Table(name = "SECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findByIdSeccion", query = "SELECT s FROM Seccion s WHERE s.idSeccion = :idSeccion"),
    @NamedQuery(name = "Seccion.findBySeccion", query = "SELECT s FROM Seccion s WHERE s.seccion = :seccion")})
public class Seccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SECCION")
    private Short idSeccion;
    @Column(name = "SECCION")
    private String seccion;
    @JoinColumn(name = "ID_GRADO", referencedColumnName = "ID_GRADO")
    @ManyToOne
    private Grado idGrado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public Seccion() {
    }

    public Seccion(Short idSeccion) {
        this.idSeccion = idSeccion;
    }

    public Short getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Short idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public Grado getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(Grado idGrado) {
        this.idGrado = idGrado;
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
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso_Datos.Seccion[ idSeccion=" + idSeccion + " ]";
    }
    
}
