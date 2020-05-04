/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.LocationBean;
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

    private JTextField loc_idField = new JTextField(30);
    private JTextField room_noField = new JTextField(30);
    private JTextField loc_typeField = new JTextField(30);
    private JTextField tempField = new JTextField(30);
 
    private JButton createButton = new JButton("Save");
    private JButton clearButton = new JButton("Update");
    private JButton updateButton = new JButton("Delete");
    private JButton deleteButton = new JButton("Clear");
 
    private LocationBean bean;
 
    public LocationForm() {
        setBorder(new TitledBorder
        (new EtchedBorder(),"Location"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
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
                         loc_idField.setText("");
                         room_noField.setText("");
                         loc_typeField.setText("");
                         tempField.setText("");
                     } else {
                         ; //Do nothing
                     }
                 case "Clear":
                     loc_idField.setText("");
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
         createButton.addActionListener(new ButtonHandler());
         return panel;
    }
 
    private JPanel initFields() {
         JPanel panel = new JPanel();
         panel.add(new JLabel("Location ID"), "align label");
         panel.add(loc_idField, "wrap");
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
           Integer.parseInt(loc_idField.getText()),
           Integer.parseInt(room_noField.getText()),
           loc_typeField.getText(),
           Integer.parseInt(tempField.getText())
         );
         return p;
    }
   
    private void setFieldData(Location p) {
         loc_idField.setText(p.getID());
         room_noField.setText(Integer.toString(p.getRoom_num()));
         loc_typeField.setText(p.getType());
         tempField.setText(Integer.toBinaryString(p.getTemperature()));
    }
   
    private boolean isEmptyFieldData() {
         return (loc_idField.getText().trim().isEmpty()
                 && room_noField.getText().isBlank()
                 && loc_typeField.getText().isBlank()
                 && tempField.getText().isBlank());
    }
    
    public void setInsert(boolean insert){
       if(insert){
           updateButton.setEnabled(false);
           deleteButton.setEnabled(false);
       } else {
           createButton.setEnabled(false);
           loc_idField.setEditable(false);
       }
   }
}