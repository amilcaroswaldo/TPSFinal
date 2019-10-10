/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.ClassNota;
import Acceso_Datos.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amilcar
 */
public class Nota {    

    ClassNota classMatri = new ClassNota();
    Conexion conn;
    ResultSet rs;
    Statement sentence;
    public Nota() {
        conn = new Conexion();
    }

    public void mostrarAlumnosGrado(JTable tabla, Short idGrado) {
        DefaultTableModel modelo = null;
        String[] titulo = {"ID Alumno", "Alumno", "Nota 1", "Nota 2", "Nota 3", "Promedio"};
        modelo = new DefaultTableModel(null, titulo);
        String sql = "SELECT a.id_alumno,a.nombre||' ' || a.apellido  FROM matricula m "
                + "JOIN grado g on m.id_grado = g.id_grado "
                + "JOIN alumno a on m.id_alumno = a.id_alumno "
                + "where g.id_grado=" + idGrado;

        String[] filas = new String[2];
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.getConnection().createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < 2; i++) {
                    filas[i] = rs.getString(i + 1);
                }
                modelo.addRow(filas);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
        }
    }
    
     public void agregarNotas(int idAlum,int idMate, char periodo, double nota1,double nota2, double nota3) {
        try {
            Connection cn = conn.getConnection();
            CallableStatement cst = conn.getConnection().prepareCall("{call notas (?,?,?,?,?,?)}");
            cst.setInt(1, idAlum);
            cst.setInt(2, idMate);
            cst.setInt(3, periodo);
            cst.setDouble(4, nota1);
            cst.setDouble(5, nota2);
            cst.setDouble(6, nota3);
            cst.execute();
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Conexion erronea"+e.getMessage());
        }
    }
}
