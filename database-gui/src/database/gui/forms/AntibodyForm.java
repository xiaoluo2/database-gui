/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.control.AntibodyBean;
import database.gui.control.Bean;
import database.gui.entity.Antibody;
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
public class AntibodyForm extends JPanel {
   /**
	 * 
	 */
	private static final long serialVersionUID = 670329382520087673L;
/**
	 * 
	 */
private JTextField idField = new JTextField(30);
   private JTextField nameField = new JTextField(30);
   private JTextField tempField = new JTextField(30);
   private JTextField sourceField = new JTextField(30);
   private JTextField hostField = new JTextField(30);

   private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Clear");
   private JButton updateButton = new JButton("Update");
   private JButton deleteButton = new JButton("Delete");

   private Bean bean;

   public AntibodyForm() {
      initComponents();
      setInsert(true);
      setVisible(false);
      bean = new AntibodyBean();
      bean.setRs(null);
   }
   
   public AntibodyForm(AntibodyBean b){
       initComponents();
       this.bean = b;
       setInsert(false);
       setFieldData(b.getCurrent());
   }
   
   private void initComponents(){
      setBorder(new TitledBorder
      (new EtchedBorder(),"Antibody"));
        setLayout(new BorderLayout(5, 5));
        add(initFields(), BorderLayout.WEST);
        add(initButtons(), BorderLayout.PAGE_END);
   }
   
   private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Antibody a = getFieldData();
            JFrame f = (JFrame) AntibodyForm.this.getRootPane().getParent();
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
                    nameField.setText("");
                    tempField.setText("");
                    sourceField.setText("");
                    hostField.setText("");
                    
                    break;
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
      panel.setLayout(new GridLayout(5,2));
      panel.add(new JLabel("ID"), "align label");
      panel.add(idField);
      panel.add(new JLabel("Name"), "align label");
      panel.add(nameField);
      panel.add(new JLabel("Temperature"), "align label");
      panel.add(tempField);
      panel.add(new JLabel("Source"), "align label");
      panel.add(sourceField);
      panel.add(new JLabel("Host"), "align label");
      panel.add(hostField);
      return panel;
   }

   private Antibody getFieldData() {
      Antibody p = new Antibody(
        nameField.getText(),
        idField.getText(),
        Integer.parseInt(tempField.getText().equals("")?"0":tempField.getText()),
        sourceField.getText(),
        hostField.getText()
      );
      return p;
   }

   private void setFieldData(Antibody p) {
      idField.setText(p.getID());
      nameField.setText(p.getName());
      tempField.setText(Integer.toString(p.getTemp()));
      sourceField.setText(p.getVendor());
      hostField.setText(p.getHost());
   }

   private boolean isEmptyFieldData() {
      return (idField.getText().trim().isEmpty()
              && nameField.getText().isBlank()
              && tempField.getText().isBlank()
              && sourceField.getText().isBlank()
              && hostField.getText().isBlank());
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
