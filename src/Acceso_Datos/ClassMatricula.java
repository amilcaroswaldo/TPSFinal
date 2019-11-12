/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso_Datos;

/**
 *
 * @author Amilcar
 */
public class ClassMatricula {
     public ClassMatricula() {
    }

    public ClassMatricula( int idUsuario, int idAlumno, int idGrado) {
        this.idUsuario = idUsuario;
        this.idAlumno = idAlumno;
        this.idGrado = idGrado;
    }

    @Override
    public String toString() {
        return "Matricula{" + " idUsuario=" + idUsuario + ", idAlumno=" + idAlumno + ", idGrado=" + idGrado + '}';
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(int idGrado) {
        this.idGrado = idGrado;
    }


   int idUsuario;
   int idAlumno;
   int idGrado;

}
