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
public class VariablesCompartidas {


    int idProfesor=0;

    public VariablesCompartidas() {
    }

    @Override
    public String toString() {
        return "VariablesCompartidas{" + "idProfesor=" + idProfesor + '}';
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }
}
