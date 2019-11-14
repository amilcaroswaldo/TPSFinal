/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import Acceso_Datos.Grado;
import Acceso_Datos.Materia;
import Acceso_Datos.VariablesCompartidas;
import Logica_Negocios.GradoJpaController;
import Logica_Negocios.MateriaJpaController;
import Logica_Negocios.Nota;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class FRNotas extends javax.swing.JInternalFrame {

    GradoJpaController controlGrado = new GradoJpaController();
    MateriaJpaController controlMateria = new MateriaJpaController();
    VariablesCompartidas classVars = new VariablesCompartidas();
    Nota controlNota = new Nota();
    Short idGrado, idMateria, idAlum;
    double nota1, nota2, nota3, prom;
    char periodo;
    String perio;

    public FRNotas() {
        initComponents();
        controlGrado.comboGrado(cbxGrado);
        controlMateria.comboMateriaProfesor(cbxMateria, Short.parseShort("2"));
        // controlMateria.comboMateriaProfesor(cbxMateria,Short.parseShort( classVars.getIdProfesor()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbxGrado = new javax.swing.JComboBox<Grado>();
        jLabel2 = new javax.swing.JLabel();
        cbxMateria = new javax.swing.JComboBox<Materia>();
        btnMostar = new javax.swing.JButton();
        cbxPeriodo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        lblNombeP = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNotas = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Grado");

        cbxGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        jLabel2.setText("Materia");

        cbxMateria.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));

        btnMostar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mostrar.png"))); // NOI18N
        btnMostar.setText("Mostrar alumnos");
        btnMostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostarActionPerformed(evt);
            }
        });

        cbxPeriodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Periodo 1", "Periodo 2", "Periodo 3", "Periodo 4" }));

        jLabel3.setText("Periodo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1)
                        .addGap(155, 155, 155)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(71, 71, 71))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbxGrado, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(cbxMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnMostar)
                                .addGap(10, 10, 10)))
                        .addGap(53, 53, 53)
                        .addComponent(cbxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnMostar)
                .addContainerGap())
        );

        lblNombeP.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombeP.setText("--");

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("REGISTRO DE NOTAS");

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tableNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNotas);

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sobresalir.png"))); // NOI18N
        btnExportar.setText("Exportar a excel");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar notas");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(btnExportar)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 851, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombeP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(367, 367, 367))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombeP)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostarActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tableNotas.getModel();
        modelo.setRowCount(0);
        idGrado = cbxGrado.getItemAt(cbxGrado.getSelectedIndex()).getIdGrado();
        controlNota.mostrarAlumnosGrado(tableNotas, idGrado);
    }//GEN-LAST:event_btnMostarActionPerformed

    private void tableNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNotasMouseClicked
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tableNotas.getModel();
        
        nota1 = Double.valueOf(modelo.getValueAt(tableNotas.getSelectedRow(), 2) +"");
        nota2 = Double.parseDouble(modelo.getValueAt(tableNotas.getSelectedRow(), 3).toString());
        nota3 = Double.parseDouble(modelo.getValueAt(tableNotas.getSelectedRow(), 4).toString());
        prom = Math.round((nota1 + nota2 + nota3) / 3);
        modelo.setValueAt(prom, tableNotas.getSelectedRow(), 5);
    }//GEN-LAST:event_tableNotasMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        idMateria = cbxGrado.getItemAt(cbxGrado.getSelectedIndex()).getIdGrado();
        perio = cbxPeriodo.getItemAt(cbxPeriodo.getSelectedIndex()).toString();
        periodo();
        for (int i = 0; i <= tableNotas.getRowCount(); i++) {
           // JOptionPane.showMessageDialog(null, tableNotas.getValueAt(i, 0) + "");
            idAlum = Short.parseShort(tableNotas.getValueAt(i, 0) + "");
            nota1 = Double.parseDouble(tableNotas.getValueAt(i, 2) + "");
            nota2 = Double.parseDouble(tableNotas.getValueAt(i, 3) + "");
            nota3 = Double.parseDouble(tableNotas.getValueAt(i, 4) + "");
            controlNota.agregarNotas(idAlum, idMateria, periodo, nota1, nota2, nota3);
           // JOptionPane.showMessageDialog(null, "Proceso terminado");
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void periodo() {
        if (perio.equals("Periodo 1")) {
            periodo = '1';
        }
        else if (perio.equals("Periodo 2")) {
            periodo = '2';
        }
        else if (perio.equals("Periodo 3")) {
            periodo = '3';
        }
        else{
            periodo = '4';
        }
    }

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostar;
    private javax.swing.JComboBox<Grado> cbxGrado;
    private javax.swing.JComboBox<Materia> cbxMateria;
    private javax.swing.JComboBox cbxPeriodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombeP;
    private javax.swing.JTable tableNotas;
    // End of variables declaration//GEN-END:variables
}
