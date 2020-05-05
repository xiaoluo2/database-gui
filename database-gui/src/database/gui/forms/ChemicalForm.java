/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.Bean;
import database.gui.bean.ChemicalBean;
import database.gui.entity.Chemical;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Xiao Luo
 */
public class ChemicalForm extends JPanel{
    private JTextField idField = new JTextField(30);
   private JTextField nameField = new JTextField(30);
   private JTextField tempField = new JTextField(30);
   private JTextField sourceField = new JTextField(30);
   private JTextField amountField = new JTextField(30);

   private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Clear");
   private JButton updateButton = new JButton("Update");
   private JButton deleteButton = new JButton("Delete");

   private Bean bean;

   public ChemicalForm() {
      initComponents();
      setInsert(true);
      setVisible(false);
      bean = new ChemicalBean();
      bean.setRs(null);
    }
   
   public ChemicalForm(ChemicalBean b){
       initComponents();
       this.bean = b;
       setInsert(false);
       setFieldData(b.getCurrent());
   }
   
      private void initComponents(){
      setBorder(new TitledBorder
      (new EtchedBorder(),"Chemical"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.WEST);
        add(initButtons(), BorderLayout.PAGE_END);
   }

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Chemical a = getFieldData();
            switch(e.getActionCommand()) {
                case "Save":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null, "Cannot save an empty record.");
                        return;
                    }
                    if(bean.create(a) != null) {
                        JOptionPane.showMessageDialog(null, "Saved sucessfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to save.");
                    };
                case "Update":
                    if (isEmptyFieldData()) {
                        JOptionPane.showMessageDialog(null, "Cannot update an empty record.");
                        return;
                    }
                    if(bean.update(a) != null) {
                        JOptionPane.showMessageDialog(null, "Updated sucessfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update.");
                    };
                case "Delete":
                    int reply = JOptionPane.showConfirmDialog(null, "Confirm deletion", "Delete" + a.getID(), JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        bean.delete(a);
                        JOptionPane.showMessageDialog(null, "Deleted.");
                        idField.setText("");
                        nameField.setText("");
                        tempField.setText("");
                        sourceField.setText("");
                        amountField.setText("");
                    } else {
                        ; //Do nothing
                    }
                case "Clear":
                    idField.setText("");
                    nameField.setText("");
                    tempField.setText("");
                    sourceField.setText("");
                    amountField.setText("");
            }
            
        }
       
    }

    private JPanel initButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        panel.add(createButton);
        panel.add(clearButton);
         panel.add(updateButton);
         panel.add(deleteButton);
        createButton.addActionListener(new ButtonHandler());
        clearButton.addActionListener(new ButtonHandler());
      updateButton.addActionListener(new ButtonHandler());
      deleteButton.addActionListener(new ButtonHandler());
        return panel;
    }

    private JPanel initFields() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2));
        panel.add(new JLabel("ID"), "align label");
        panel.add(idField, "wrap");
        panel.add(new JLabel("Name"), "align label");
        panel.add(nameField, "wrap");
        panel.add(new JLabel("Temperature"), "align label");
        panel.add(tempField, "wrap");
        panel.add(new JLabel("Source"), "align label");
        panel.add(sourceField, "wrap");
        panel.add(new JLabel("Amount"), "align label");
        panel.add(amountField, "wrap");
        return panel;
    }

    private Chemical getFieldData() {
        Chemical p = new Chemical(
          nameField.getText(),
          idField.getText(),
          Integer.parseInt(tempField.getText().equals("")?"0":tempField.getText()),
          sourceField.getText(),
          amountField.getText()
        );
        return p;
     }
  
     private void setFieldData(Chemical p) {
        idField.setText(p.getID());
        nameField.setText(p.getName());
        tempField.setText(Integer.toString(p.getTemp()));
        sourceField.setText(p.getVendor());
        amountField.setText(p.getAmount());
     }
  
     private boolean isEmptyFieldData() {
        return (idField.getText().trim().isEmpty()
                && nameField.getText().isBlank()
                && tempField.getText().isBlank()
                && sourceField.getText().isBlank()
                && amountField.getText().isBlank());
     }    

   public void setInsert(boolean insert){
       if(insert){
           updateButton.setVisible(false);
           deleteButton.setVisible(false);
           createButton.setVisible(true);
           idField.setEditable(true);
       } else {
           createButton.setVisible(false);
           idField.setEditable(false);
           updateButton.setVisible(true);
           deleteButton.setVisible(true);
       }
   }
    
}