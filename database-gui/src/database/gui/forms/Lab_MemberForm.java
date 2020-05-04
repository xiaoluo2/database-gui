/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.Lab_MemberBean;
import database.gui.entity.Lab_Member;
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
public class Lab_MemberForm extends JPanel{

    private JTextField lab_idField = new JTextField(30);
    private JTextField nameField = new JTextField(30);
    private JTextField titleField = new JTextField(30);
 
    private JButton createButton = new JButton("Save");
    private JButton clearButton = new JButton("Update");
    private JButton updateButton = new JButton("Delete");
    private JButton deleteButton = new JButton("Clear");
 
    private Lab_MemberBean bean;
 
    public Lab_MemberForm() {
        setBorder(new TitledBorder
        (new EtchedBorder(),"Lab_Member"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.NORTH);
        add(initButtons(), BorderLayout.CENTER);
    }
 
    private class ButtonHandler implements ActionListener {
 
         @Override
         public void actionPerformed(ActionEvent e) {
             Lab_Member a = getFieldData();
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
                         lab_idField.setText("");
                         nameField.setText("");
                         titleField.setText("");
                     } else {
                         ; //Do nothing
                     }
                 case "Clear":
                     lab_idField.setText("");
                     nameField.setText("");
                     titleField.setText("");
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
         panel.add(new JLabel("Lab_ID"), "align label");
         panel.add(lab_idField, "wrap");
         panel.add(new JLabel("Name"), "align label");
         panel.add(nameField, "wrap");
         panel.add(new JLabel("Title"), "align label");
         panel.add(titleField, "wrap");
         return panel;
    }
 
    private Lab_Member getFieldData() {
        Lab_Member p = new Lab_Member(
           Integer.parseInt(nameField.getText()),
           lab_idField.getText(),
           titleField.getText()
         );
         return p;
    }
   
    private void setFieldData(Lab_Member p) {
         lab_idField.setText(p.getID());
         nameField.setText(p.getName());
         titleField.setText(p.getTitle());
    }
   
    private boolean isEmptyFieldData() {
         return (lab_idField.getText().trim().isEmpty()
                 && nameField.getText().isBlank()
                 && titleField.getText().isBlank());
    }
    
    public void setInsert(boolean insert){
       if(insert){
           updateButton.setEnabled(false);
           deleteButton.setEnabled(false);
       } else {
           createButton.setEnabled(false);
           lab_idField.setEditable(false);
       }
   }
}
