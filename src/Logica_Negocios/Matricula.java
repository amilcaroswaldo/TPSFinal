/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_Negocios;

import Acceso_Datos.Parentesco;
import static Acceso_Datos.Parentesco_.parentesco;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Amilcar
 */
public class Matricula {
    ParentescoJpaController controlParen= new ParentescoJpaController();
    
    public void comboParentesco(JComboBox<Parentesco> combo){
        try {
            List<Parentesco> lista = controlParen.findParentescoEntities();
            for (Parentesco item : lista) {
                combo.addItem(new Parentesco(
                        item.getIdParentesco()
                )
                );
            }
        } catch (Exception e) {
        }
    }

    @Override
    public String toString() {
        return parentesco.toString();
    }
}
