/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso_Datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amilcar
 */
@Entity
@Table(name = "HISTORIAL_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialAlumno.findAll", query = "SELECT h FROM HistorialAlumno h"),
    @NamedQuery(name = "HistorialAlumno.findByIdHistorialAlumno", query = "SELECT h FROM HistorialAlumno h WHERE h.idHistorialAlumno = :idHistorialAlumno"),
    @NamedQuery(name = "HistorialAlumno.findByFechaInicio", query = "SELECT h FROM HistorialAlumno h WHERE h.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "HistorialAlumno.findByFechaFin", query = "SELECT h FROM HistorialAlumno h WHERE h.fechaFin = :fechaFin")})
public class HistorialAlumno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_HISTORIAL_ALUMNO")
    private Short idHistorialAlumno;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    public HistorialAlumno() {
    }

    public HistorialAlumno(Short idHistorialAlumno) {
        this.idHistorialAlumno = idHistorialAlumno;
    }

    public Short getIdHistorialAlumno() {
        return idHistorialAlumno;
    }

    public void setIdHistorialAlumno(Short idHistorialAlumno) {
        this.idHistorialAlumno = idHistorialAlumno;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorialAlumno != null ? idHistorialAlumno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialAlumno)) {
            return false;
        }
        HistorialAlumno other = (HistorialAlumno) object;
        if ((this.idHistorialAlumno == null && other.idHistorialAlumno != null) || (this.idHistorialAlumno != null && !this.idHistorialAlumno.equals(other.idHistorialAlumno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso_Datos.HistorialAlumno[ idHistorialAlumno=" + idHistorialAlumno + " ]";
    }
    
}
