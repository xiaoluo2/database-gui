/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.control.Bean;
import database.gui.control.ItemAdder;
import database.gui.control.OrderBean;
import database.gui.entity.Order;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Xiao Luo
 */
public class OrderForm extends JPanel{

    /**
	 * 
	 */
    private static final long serialVersionUID = -2908720779469827862L;
    private JTextField idField = new JTextField(30);
    private JTextField statusField = new JTextField(30);
    private JTextField dateField = new JTextField(30);
    private JTextField requesterField = new JTextField(30);

    private JButton createButton = new JButton("Save");
    private JButton clearButton = new JButton("Clear");
    private JButton updateButton = new JButton("Update");
    private JButton deleteButton = new JButton("Delete");
 
    private Bean bean;
    public List<String> item_ids;
 
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
   
   public void selectItems(){
    // on save give order id to item adder
    ItemAdder itemAdder = new ItemAdder(idField.getText(), "Order");
    JFrame f = new AddRemoveUI(itemAdder);
    f.setVisible(true);
    
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
             JFrame f = (JFrame) OrderForm.this.getRootPane().getParent();
             switch(e.getActionCommand()) {
                 case "Save":
                     if (isEmptyFieldData()) {
                         JOptionPane.showMessageDialog(null, "Cannot save an empty record.");
                         return;
                     }
                     if(bean.create(a) != null) {
                         JOptionPane.showMessageDialog(null, "Saved sucessfully.");
                         selectItems();
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
                         f.dispose();
                     } else {
                         JOptionPane.showMessageDialog(null, "Failed to update.");
                     };
                     break;
                 case "Delete":
                     int reply = JOptionPane.showConfirmDialog(null, "Confirm deletion", "Delete" + a.getID(), JOptionPane.YES_NO_OPTION);
                     if (reply == JOptionPane.YES_OPTION) {
                         bean.delete(a);
                         JOptionPane.showMessageDialog(null, "Deleted.");
                         f.dispose();
                     } else {
                         ; //Do nothing
                     }
                     break;
                 case "Clear":
                     idField.setText("");
                     statusField.setText("");
                     dateField.setText("");
                     requesterField.setText("");
             }
             
         }
        
    }
    
    private JPanel initButtons() {
         JPanel panel = new JPanel();
         panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 3, 3));
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
         panel.add(new JLabel("Requester ID"), "align label");
         panel.add(requesterField, "wrap");
         return panel;
    }
 
    private Order getFieldData() {
        Order p = new Order(
           idField.getText(),
           statusField.getText(),
           dateField.getText(),
           Integer.parseInt(requesterField.getText())
         );
         return p;
    }
   
    private void setFieldData(Order p) {
         idField.setText(p.getID());
         statusField.setText(p.getStatus());
         dateField.setText(p.getDate());
         requesterField.setText(Integer.toString(p.getRequester()));
    }
   
    private boolean isEmptyFieldData() {
         return (idField.getText().trim().isEmpty()
                 && statusField.getText().isBlank()
                 && dateField.getText().isBlank()
                 && requesterField.getText().isBlank());
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