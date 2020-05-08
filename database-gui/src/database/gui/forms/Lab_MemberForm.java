/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.control.Bean;
import database.gui.control.Lab_MemberBean;
import database.gui.entity.Lab_Member;
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
public class Lab_MemberForm extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4884364048201942920L;
	private JTextField idField = new JTextField(30);
    private JTextField nameField = new JTextField(30);
    private JTextField titleField = new JTextField(30);
 
    private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Clear");
   private JButton updateButton = new JButton("Update");
   private JButton deleteButton = new JButton("Delete");
 
    private Bean bean;
 
    public Lab_MemberForm() {
      initComponents();
      setInsert(true);
      setVisible(false);
      bean = new Lab_MemberBean();
      bean.setRs(null);
    }
    
    public Lab_MemberForm(Lab_MemberBean b){
        initComponents();
       this.bean = b;
       setInsert(false);
       setFieldData(b.getCurrent());
    }
    
   private void initComponents(){
      setBorder(new TitledBorder
      (new EtchedBorder(),"Lab Member"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.WEST);
        add(initButtons(), BorderLayout.PAGE_END);
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
                     break;
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
                     break;
                 case "Delete":
                     int reply = JOptionPane.showConfirmDialog(null, "Confirm deletion", "Delete" + a.getID(), JOptionPane.YES_NO_OPTION);
                     if (reply == JOptionPane.YES_OPTION) {
                         bean.delete(a);
                         JOptionPane.showMessageDialog(null, "Deleted.");
                         idField.setText("");
                         nameField.setText("");
                         titleField.setText("");
                     } else {
                         ; //Do nothing
                     }
                     break;
                 case "Clear":
                     idField.setText("");
                     nameField.setText("");
                     titleField.setText("");
                     break;
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
         panel.setLayout(new GridLayout(3,2));
         panel.add(new JLabel("Lab_ID"), "align label");
         panel.add(idField, "wrap");
         panel.add(new JLabel("Name"), "align label");
         panel.add(nameField, "wrap");
         panel.add(new JLabel("Title"), "align label");
         panel.add(titleField, "wrap");
         return panel;
    }
 
    private Lab_Member getFieldData() {
        Lab_Member p = new Lab_Member(
           Integer.parseInt(idField.getText().equals("") ? "0": idField.getText()),
           nameField.getText(),
           titleField.getText()
         );
         return p;
    }
   
    private void setFieldData(Lab_Member p) {
         idField.setText(p.getID());
         nameField.setText(p.getName());
         titleField.setText(p.getTitle());
    }
   
    private boolean isEmptyFieldData() {
         return (idField.getText().trim().isEmpty()
                 && nameField.getText().isBlank()
                 && titleField.getText().isBlank());
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
