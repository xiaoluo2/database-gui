/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.Bean;
import database.gui.entity.Item;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author Xiao Luo
 */
public class ItemForm extends JPanel{
    private JTextField idField = new JTextField(30);
    private JTextField nameField = new JTextField(30);
    private JTextField tempField = new JTextField(30);
    private JTextField sourceField = new JTextField(30);
    private JTextField typeField = new JTextField(30);
 
    private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Clear");
   private JButton updateButton = new JButton("Update");
   private JButton deleteButton = new JButton("Delete");
 
    private Bean bean;
 
    public ItemForm() {
        setBorder(new TitledBorder
        (new EtchedBorder(),"Item"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
    }
    
   public ItemForm(Bean b){
       this();
       this.bean = b;
   }
    
   public ItemForm(boolean insert){
       this();
       this.setInsert(insert);
   }
   
   public ItemForm(Item a){
       this(false);
       this.setFieldData(a);
   }
 
    private class ButtonHandler implements ActionListener {
 
         @Override
         public void actionPerformed(ActionEvent e) {
             Item a = getFieldData();
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
                         typeField.setText("");
                     } else {
                         ; //Do nothing
                     }
                 case "Clear":
                     idField.setText("");
                     nameField.setText("");
                     tempField.setText("");
                     sourceField.setText("");
                     typeField.setText("");
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
         panel.add(new JLabel("ID"), "align label");
         panel.add(idField, "wrap");
         
         panel.add(new JLabel("Name"), "align label");
         panel.add(nameField, "wrap");
         panel.add(new JLabel("Temperature"), "align label");
         panel.add(tempField, "wrap");
         panel.add(new JLabel("Source"), "align label");
         panel.add(sourceField, "wrap");
         panel.add(new JLabel("Type"), "align label");
         panel.add(typeField, "wrap");
         return panel;
    }
 
    private Item getFieldData() {
         Item p = new Item(
           nameField.getText(),
           idField.getText(),
           Integer.parseInt(tempField.getText().equals("")?"0":tempField.getText()),
           sourceField.getText(),
           typeField.getText()
         );
         return p;
    }
   
    private void setFieldData(Item p) {
         idField.setText(p.getID());
         nameField.setText(p.getName());
         tempField.setText(Integer.toString(p.getTemp()));
         sourceField.setText(p.getVendor());
         typeField.setText(p.getType());
    }
   
    private boolean isEmptyFieldData() {
         return (idField.getText().trim().isEmpty()
                 && nameField.getText().isBlank()
                 && tempField.getText().isBlank()
                 && sourceField.getText().isBlank()
                 && typeField.getText().isBlank());
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