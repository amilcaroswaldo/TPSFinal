/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import Acceso_Datos.Materia;
import Acceso_Datos.Usuario;
import Acceso_Datos.UsuarioMate;
import Logica_Negocios.UsuarioJpaController;
import Logica_Negocios.MateriaJpaController;
import Logica_Negocios.UsuarioMateJpaController;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class FRAsignarMateria extends javax.swing.JInternalFrame {

    UsuarioJpaController controlUsuario = new UsuarioJpaController();
    MateriaJpaController controlMateria = new MateriaJpaController();
    UsuarioMateJpaController controAddMta = new UsuarioMateJpaController();
    Short idMateria, idUsuario;
    String materia, profe;
    DefaultTableModel model = new DefaultTableModel();
    Boolean unaVez = false;

    public FRAsignarMateria() {
        initComponents();
        controlMateria.comboMateria(ComboMaetrias);
        controlUsuario.comboUsuario(ComboMaetsro);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ComboMaetrias = new javax.swing.JComboBox<Materia>();
        btnAsignar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableMateProfe = new javax.swing.JTable();
        btnEliminiarMateProfe = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        ComboMaetsro = new javax.swing.JComboBox<Usuario>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setToolTipText("");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("Maestro");

        jLabel9.setText("Materia");

        ComboMaetrias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMaetriasActionPerformed(evt);
            }
        });

        btnAsignar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add_materia.png"))); // NOI18N
        btnAsignar.setText("Asignar");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        TableMateProfe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TableMateProfe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TableMateProfe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMateProfeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TableMateProfe);

        btnEliminiarMateProfe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminiarMateProfe.setText("Eliminar");
        btnEliminiarMateProfe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminiarMateProfeActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        ComboMaetsro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMaetsroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(ComboMaetsro, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(ComboMaetrias, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminiarMateProfe)
                            .addComponent(btnGuardar)
                            .addComponent(btnAsignar))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboMaetsro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboMaetrias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAsignar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminiarMateProfe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(67, 67, 67))
        );

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("ASIGNAR MATERIA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(196, 196, 196))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("Asigar materia");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboMaetriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMaetriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboMaetriasActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        // TODO add your handling code here:

        if (!unaVez) {
            unaVez = true;
            model.addColumn("ID profesor");
            model.addColumn("Profesor");
            model.addColumn("ID materia");
            model.addColumn("Materia");
        }
        materia = ComboMaetrias.getItemAt(ComboMaetrias.getSelectedIndex()).getMateria() + "";
        profe = ComboMaetsro.getItemAt(ComboMaetsro.getSelectedIndex()).getNombre() + "";
        idMateria = ComboMaetrias.getItemAt(ComboMaetrias.getSelectedIndex()).getIdMateria();
        idUsuario = ComboMaetsro.getItemAt(ComboMaetsro.getSelectedIndex()).getIdUsuario();
        model.addRow(new Object[]{idUsuario, profe, idMateria, materia});
        TableMateProfe.setModel(model);

    }//GEN-LAST:event_btnAsignarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        try {
             if(this.TableMateProfe.getRowCount()==0){
                JOptionPane.showMessageDialog(this, "Debe agregar primero usuarios a la tabla");
             }else{
                  for (int i = 0; i <= TableMateProfe.getRowCount(); i++) {
                Usuario classUsuario = new Usuario();
                Materia classMateria = new Materia();
                UsuarioMate classUsMat = new UsuarioMate();
                Short idUsuarioC = Short.parseShort(TableMateProfe.getValueAt(i, 0) + "");
                Short idMateriaC = Short.parseShort(TableMateProfe.getValueAt(i, 2) + "");
                classUsuario.setIdUsuario(idUsuarioC);
                classMateria.setIdMateria(idMateriaC);
                classUsMat.setIdMateria(classMateria);
                classUsMat.setIdUsuario(classUsuario);
                controAddMta.create(classUsMat);
                JOptionPane.showMessageDialog(this, "Almacenados con éxito"); 
            }
                        
            } 
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminiarMateProfeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminiarMateProfeActionPerformed
        // TODO add your handling code here:
        if(this.TableMateProfe.getSelectedRow()==-1){
              JOptionPane.showMessageDialog(this, "Debe seleccionar primero la matería que eliminaras.");
        }else if(JOptionPane.showConfirmDialog(rootPane, "Se eliminará la materia seleccionada. ¿Desea continuar?",
        "Cancelar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
               DefaultTableModel modelo = (DefaultTableModel) TableMateProfe.getModel();
        modelo.removeRow(TableMateProfe.getSelectedRow());
        }
      
    }//GEN-LAST:event_btnEliminiarMateProfeActionPerformed

    private void ComboMaetsroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMaetsroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboMaetsroActionPerformed

    private void TableMateProfeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMateProfeMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_TableMateProfeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Materia> ComboMaetrias;
    private javax.swing.JComboBox<Usuario> ComboMaetsro;
    private javax.swing.JTable TableMateProfe;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnEliminiarMateProfe;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
