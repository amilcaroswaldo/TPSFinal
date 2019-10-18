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
public class Sesion {

    public static String nombreUsuario;
    public static String password;
    public static short idUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String toString() {
        return "Sesion{" + "nombreUsuario=" + nombreUsuario + ", password=" + password + ", idUsuario=" + idUsuario + '}';
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(short idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Sesion() {
    }

}
