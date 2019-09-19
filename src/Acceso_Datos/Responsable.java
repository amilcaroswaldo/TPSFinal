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
@Table(name = "RESPONSABLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Responsable.findAll", query = "SELECT r FROM Responsable r"),
    @NamedQuery(name = "Responsable.findByIdResponsable", query = "SELECT r FROM Responsable r WHERE r.idResponsable = :idResponsable"),
    @NamedQuery(name = "Responsable.findByNombre", query = "SELECT r FROM Responsable r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Responsable.findByApellido", query = "SELECT r FROM Responsable r WHERE r.apellido = :apellido"),
    @NamedQuery(name = "Responsable.findByDireccion", query = "SELECT r FROM Responsable r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Responsable.findByDui", query = "SELECT r FROM Responsable r WHERE r.dui = :dui"),
    @NamedQuery(name = "Responsable.findByTelefono", query = "SELECT r FROM Responsable r WHERE r.telefono = :telefono")})
public class Responsable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_RESPONSABLE")
    private Short idResponsable;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "DUI")
    private String dui;
    @Column(name = "TELEFONO")
    private String telefono;
    @OneToMany(mappedBy = "idResponsable")
    private Collection<Alumno> alumnoCollection;

    public Responsable() {
    }

    public Responsable(Short idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Responsable(Short idResponsable, String nombre, String apellido, String direccion) {
        this.idResponsable = idResponsable;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public Short getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Short idResponsable) {
        this.idResponsable = idResponsable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<Alumno> getAlumnoCollection() {
        return alumnoCollection;
    }

    public void setAlumnoCollection(Collection<Alumno> alumnoCollection) {
        this.alumnoCollection = alumnoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResponsable != null ? idResponsable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Responsable)) {
            return false;
        }
        Responsable other = (Responsable) object;
        if ((this.idResponsable == null && other.idResponsable != null) || (this.idResponsable != null && !this.idResponsable.equals(other.idResponsable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso_Datos.Responsable[ idResponsable=" + idResponsable + " ]";
    }
    
}
