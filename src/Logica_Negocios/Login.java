/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import java.util.List;
import Acceso_Datos.Sesion;
import Acceso_Datos.Usuario;

/**
 *
 * @author Amilcar
 */
public class Login {
    UsuarioJpaController controlUsuario = new UsuarioJpaController();
    Sesion classUsuario = new Sesion();
    Boolean varBool;
    String nombre, contrase;
    public  boolean validarUsuario(String nombreUsuario, String passw){
        List<Usuario> listUsuario = controlUsuario.findUsuarioEntities();
        for (Usuario item : listUsuario) {
            nombre = item.getNomusuario();
            contrase=item.getPass();
            if (nombreUsuario.equals(nombre) && passw.equals(contrase)) {
                varBool= true;
                classUsuario.setNombreUsuario(item.getNomusuario());
                classUsuario.setPassword(item.getPass());
                classUsuario.setIdUsuario(item.getIdUsuario());
                break;
            }
            else{
                 varBool= false;
            }
        }
        return varBool;
       
    }
    
}
