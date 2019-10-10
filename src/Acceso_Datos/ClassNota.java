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
public class ClassNota {
    int idNota;
   int idAlumno;
   int idPeriodo;
   int idUsuarioMate;
   int idUsuario;
   int idUsuaMate;
   double nota1;
   double nota2;
   double nota3;

    public ClassNota() {
    }

    public ClassNota(int idNota, int idAlumno, int idPeriodo, int idUsuarioMate, int idUsuario, int idUsuaMate, double nota1, double nota2, double nota3) {
        this.idNota = idNota;
        this.idAlumno = idAlumno;
        this.idPeriodo = idPeriodo;
        this.idUsuarioMate = idUsuarioMate;
        this.idUsuario = idUsuario;
        this.idUsuaMate = idUsuaMate;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
    }

    @Override
    public String toString() {
        return "Nota{" + "idNota=" + idNota + ", idAlumno=" + idAlumno + ", idPeriodo=" + idPeriodo + ", idUsuarioMate=" + idUsuarioMate + ", idUsuario=" + idUsuario + ", idUsuaMate=" + idUsuaMate + ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3 + '}';
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getIdUsuarioMate() {
        return idUsuarioMate;
    }

    public void setIdUsuarioMate(int idUsuarioMate) {
        this.idUsuarioMate = idUsuarioMate;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuaMate() {
        return idUsuaMate;
    }

    public void setIdUsuaMate(int idUsuaMate) {
        this.idUsuaMate = idUsuaMate;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }
}
