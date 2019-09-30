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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amilcar
 */
@Entity
@Table(name = "HISTORIAL_TRABAJADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialTrabajador.findAll", query = "SELECT h FROM HistorialTrabajador h"),
    @NamedQuery(name = "HistorialTrabajador.findByIdHistorialTrabajador", query = "SELECT h FROM HistorialTrabajador h WHERE h.idHistorialTrabajador = :idHistorialTrabajador"),
    @NamedQuery(name = "HistorialTrabajador.findByFechaInicio", query = "SELECT h FROM HistorialTrabajador h WHERE h.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "HistorialTrabajador.findByFechaFin", query = "SELECT h FROM HistorialTrabajador h WHERE h.fechaFin = :fechaFin")})
public class HistorialTrabajador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "historial_trabajador_seq", sequenceName = "historial_trabajador_seq", allocationSize = 1) 
    @GeneratedValue(strategy= GenerationType.IDENTITY , generator="historial_trabajador_seq")
    @Column(name = "ID_HISTORIAL_TRABAJADOR")
    private Short idHistorialTrabajador;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @JoinColumn(name = "ID_TIPO", referencedColumnName = "ID_TIPO")
    @ManyToOne
    private TipoUsuario idTipo;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idUsuario;

    public HistorialTrabajador() {
    }

    public HistorialTrabajador(Short idHistorialTrabajador) {
        this.idHistorialTrabajador = idHistorialTrabajador;
    }

    public Short getIdHistorialTrabajador() {
        return idHistorialTrabajador;
    }

    public void setIdHistorialTrabajador(Short idHistorialTrabajador) {
        this.idHistorialTrabajador = idHistorialTrabajador;
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

    public TipoUsuario getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(TipoUsuario idTipo) {
        this.idTipo = idTipo;
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
        hash += (idHistorialTrabajador != null ? idHistorialTrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialTrabajador)) {
            return false;
        }
        HistorialTrabajador other = (HistorialTrabajador) object;
        if ((this.idHistorialTrabajador == null && other.idHistorialTrabajador != null) || (this.idHistorialTrabajador != null && !this.idHistorialTrabajador.equals(other.idHistorialTrabajador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acceso_Datos.HistorialTrabajador[ idHistorialTrabajador=" + idHistorialTrabajador + " ]";
    }
    
}
