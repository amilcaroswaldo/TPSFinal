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
public class MatriMateria {
    int idMatriMateria;
   int idMatricula;
   int idMateria;

    public MatriMateria() {
    }

    public MatriMateria(int idMatriMateria, int idMatricula, int idMateria) {
        this.idMatriMateria = idMatriMateria;
        this.idMatricula = idMatricula;
        this.idMateria = idMateria;
    }

    @Override
    public String toString() {
        return "MatriMateria{" + "idMatriMateria=" + idMatriMateria + ", idMatricula=" + idMatricula + ", idMateria=" + idMateria + '}';
    }

    public int getIdMatriMateria() {
        return idMatriMateria;
    }

    public void setIdMatriMateria(int idMatriMateria) {
        this.idMatriMateria = idMatriMateria;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
}
