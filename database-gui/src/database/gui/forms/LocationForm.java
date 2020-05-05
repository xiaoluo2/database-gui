/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.Bean;
import database.gui.entity.Location;
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
public class LocationForm extends JPanel{

    private JTextField idField = new JTextField(30);
    private JTextField room_noField = new JTextField(30);
    private JTextField loc_typeField = new JTextField(30);
    private JTextField tempField = new JTextField(30);
 
    private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Clear");
   private JButton updateButton = new JButton("Update");
   private JButton deleteButton = new JButton("Delete");
 
    private Bean bean;
 
    public LocationForm() {
        setBorder(new TitledBorder
        (new EtchedBorder(),"Location"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
    }
    
    public LocationForm(Bean b){
        this();
        this.bean = b;
    }
   public LocationForm(boolean insert){
       this();
       this.setInsert(insert);
   }
   
   public LocationForm(Location a){
       this(false);
       this.setFieldData(a);
   }
 
    private class ButtonHandler implements ActionListener {
 
         @Override
         public void actionPerformed(ActionEvent e) {
             Location a = getFieldData();
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
                         room_noField.setText("");
                         loc_typeField.setText("");
                         tempField.setText("");
                     } else {
                         ; //Do nothing
                     }
                 case "Clear":
                     idField.setText("");
                     room_noField.setText("");
                     loc_typeField.setText("");
                     tempField.setText("");
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
         panel.add(new JLabel("Location ID"), "align label");
         panel.add(idField, "wrap");
         panel.add(new JLabel("Type"), "align label");
         panel.add(loc_typeField, "wrap");
         panel.add(new JLabel("Room Number"), "align label");
         panel.add(room_noField, "wrap");
         panel.add(new JLabel("Temperature"), "align label");
         panel.add(tempField, "wrap");
         return panel;
    }
 
    private Location getFieldData() {
        Location p = new Location(
           Integer.parseInt(idField.getText().equals("") ? "0": idField.getText()),
           Integer.parseInt(room_noField.getText().equals("") ? "0": room_noField.getText()),
           loc_typeField.getText(),
           Integer.parseInt(tempField.getText().equals("") ? "0" : tempField.getText())
         );
         return p;
    }
   
    private void setFieldData(Location p) {
         idField.setText(p.getID());
         room_noField.setText(Integer.toString(p.getRoom_num()));
         loc_typeField.setText(p.getType());
         tempField.setText(Integer.toBinaryString(p.getTemperature()));
    }
   
    private boolean isEmptyFieldData() {
         return (idField.getText().trim().isEmpty()
                 && room_noField.getText().isBlank()
                 && loc_typeField.getText().isBlank()
                 && tempField.getText().isBlank());
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