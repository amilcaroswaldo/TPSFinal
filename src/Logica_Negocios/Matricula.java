/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.ClassMatricula;
import Acceso_Datos.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amilcar
 */
public class Matricula {

    ClassMatricula classMatri = new ClassMatricula();
    Conexion conn = new Conexion();
    //CallableStatement cst;
    ResultSet rs;
    Statement sentence;

    public void matricular(int idProfe, int idAlum, int grado) {
        try {
            //forma 1
//            PreparedStatement pS = conn.getConnection().prepareStatement("{CALC materia_pago_matricula (?,?,?)}");
//            pS.setInt(1, idProfe );
//            pS.setInt(2, idAlum );
//            pS.setInt(3, grado);
//            rs= pS.executeQuery();
            
            //forma2
            Connection cn = conn.getConnection();
            CallableStatement cst = cn.prepareCall("{CALC materia_pago_matricula (?,?,?)}");
            cst.setInt(1, idProfe);
            cst.setInt(2, idAlum);
            cst.setInt(3, grado);
            cst.execute();

            //forma3
            //CallableStatement cst;
//            Connection cn = conn.getConnection();
//            String sql = "execute materia_pago_matricula(?,?,?)";
//            cst = cn.prepareCall(sql);
//            cst.registerOutParameter(1, idProfe);
//            cst.registerOutParameter(2, idAlum);
//            cst.registerOutParameter(3, grado);
//            cst.execute();
        } catch (Exception e) {
        }
    }

    public void mostrarAlumnosGrado(JTable tabla, Short idGrado) {
        DefaultTableModel modelo = null;
        String[] titulo = {"Grado", "Sección", "Nombre", "Apellido"};
        modelo = new DefaultTableModel(null, titulo);
        String sql = "select g.grado,s.seccion,a.nombre,a.apellido from matricula m "
                + "join grado g on g.id_grado=m.id_grado "
                + "join seccion s on s.id_seccion=g.id_seccion "
                + "join alumno a on a.id_alumno=m.id_alumno "
                + "where g.id_grado=" + idGrado;

        String[] filas = new String[4];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.getConnection().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 4; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                modelo.addRow(filas);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
        }
    }
}
