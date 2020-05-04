/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.forms;

import database.gui.bean.AntibodyBean;
import database.gui.entity.Antibody;
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
public class AntibodyForm extends JPanel {
   private JTextField idField = new JTextField(30);
   private JTextField nameField = new JTextField(30);
   private JTextField tempField = new JTextField(30);
   private JTextField sourceField = new JTextField(30);
   private JTextField hostField = new JTextField(30);

   private JButton createButton = new JButton("Save");
   private JButton clearButton = new JButton("Update");
   private JButton updateButton = new JButton("Delete");
   private JButton deleteButton = new JButton("Clear");

   private AntibodyBean bean;

   public AntibodyForm() {
      setBorder(new TitledBorder
      (new EtchedBorder(),"Antibody"));
      setLayout(new BorderLayout(5, 5));
      add(initFields(), BorderLayout.NORTH);
      add(initButtons(), BorderLayout.CENTER);
   }
   
   private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Antibody a = getFieldData();
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
                        hostField.setText("");
                    } else {
                        ; //Do nothing
                    }
                case "Clear":
                    idField.setText("");
                    nameField.setText("");
                    tempField.setText("");
                    sourceField.setText("");
                    hostField.setText("");
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
      panel.add(new JLabel("ID"), "align label");
      panel.add(idField, "wrap");
      idField.setEnabled(false);
      panel.add(new JLabel("Name"), "align label");
      panel.add(nameField, "wrap");
      panel.add(new JLabel("Temperature"), "align label");
      panel.add(tempField, "wrap");
      panel.add(new JLabel("Source"), "align label");
      panel.add(sourceField, "wrap");
      panel.add(new JLabel("Host"), "align label");
      panel.add(hostField, "wrap");
      return panel;
   }

   private Antibody getFieldData() {
      Antibody p = new Antibody(
        nameField.getText(),
        idField.getText(),
        Integer.parseInt(tempField.getText()),
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
}
