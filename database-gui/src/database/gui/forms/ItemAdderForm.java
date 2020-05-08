/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.control.ItemAdder;
import database.gui.models.RowSetModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTable;
/**
 *
 * @author Xiao Luo
 */
public class ItemAdderForm extends JPanel {
    private ItemAdder itemAdder;
    private JTable selectionTable;
    private String targetTable;
    
    public ItemAdderForm(ItemAdder itemAdder, String targetTable){
        this.itemAdder = itemAdder;
        this.targetTable = targetTable;
        this.selectionTable = new JTable();
        selectionTable.setModel(new RowSetModel(itemAdder.getRowSet()));
    }
    
    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selected = selectionTable.getSelectedRows();
            if(targetTable.equals("Location")) {
                itemAdder.addSelectedtoLocation(selected);
            } else if (targetTable.equals("Order")){
                itemAdder.addSelectedtoOrder(selected);
            }
        }
    
    }
}
