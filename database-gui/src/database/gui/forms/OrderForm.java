/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.Bean;
import database.gui.bean.OrderBean;
import database.gui.entity.Order;
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
public class OrderForm extends JPanel{

    private JTextField idField = new JTextField(30);
    private JTextField statusField = new JTextField(30);
    private JTextField dateField = new JTextField(30);
    private JTextField vendorField = new JTextField(30);
 
    private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Clear");
   private JButton updateButton = new JButton("Update");
   private JButton deleteButton = new JButton("Delete");
 
    private Bean bean;
 
    public OrderForm() {
        initComponents();
      setInsert(true);
      setVisible(false);
      bean = new OrderBean();
      bean.setRs(null);
    }
    
   public OrderForm(OrderBean b){
       initComponents();
       this.bean = b;
       setInsert(false);
       setFieldData(b.getCurrent());
   }
    
   private void initComponents(){
      setBorder(new TitledBorder
      (new EtchedBorder(),"Order"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.WEST);
        add(initButtons(), BorderLayout.PAGE_END);
   }
 
    private class ButtonHandler implements ActionListener {
 
         @Override
         public void actionPerformed(ActionEvent e) {
             Order a = getFieldData();
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
                         statusField.setText("");
                         dateField.setText("");
                         vendorField.setText("");
                     } else {
                         ; //Do nothing
                     }
                 case "Clear":
                     idField.setText("");
                     statusField.setText("");
                     dateField.setText("");
                     vendorField.setText("");
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
         panel.setLayout(new GridLayout(4,2));
         panel.add(new JLabel("Order ID"), "align label");
         panel.add(idField, "wrap");
         panel.add(new JLabel("Status"), "align label");
         panel.add(statusField, "wrap");
         panel.add(new JLabel("Date"), "align label");
         panel.add(dateField, "wrap");
         panel.add(new JLabel("Vendor"), "align label");
         panel.add(vendorField, "wrap");
         return panel;
    }
 
    private Order getFieldData() {
        Order p = new Order(
           idField.getText(),
           statusField.getText(),
           dateField.getText(),
           vendorField.getText()
         );
         return p;
    }
   
    private void setFieldData(Order p) {
         idField.setText(p.getID());
         statusField.setText(p.getStatus());
         dateField.setText(p.getDate());
         vendorField.setText(p.getVendor());
    }
   
    private boolean isEmptyFieldData() {
         return (idField.getText().trim().isEmpty()
                 && statusField.getText().isBlank()
                 && dateField.getText().isBlank()
                 && vendorField.getText().isBlank());
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