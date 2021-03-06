/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui;

import database.gui.control.Lab_MemberBean;
import database.gui.control.PlasmidBean;
import database.gui.control.Bean;
import database.gui.control.StrainBean;
import database.gui.control.AntibodyBean;
import database.gui.control.OrderBean;
import database.gui.control.ChemicalBean;
import database.gui.control.ItemAdder;
import database.gui.control.ItemBean;
import database.gui.control.ItemRemover;
import database.gui.control.LocationBean;
import database.gui.forms.*;
import database.gui.models.*;
import database.gui.utils.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sql.RowSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author Xiao Luo
 */
public class GuiJFrame extends javax.swing.JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bean bean;
    /**
     * Creates new form NewJFrame
     */
    public GuiJFrame() {
        initComponents();
        initForms();
    }
    
    private void initForms(){
        JPanel a = new AntibodyForm();
        JPanel b = new ChemicalForm();
        JPanel c = new PlasmidForm();
        JPanel d = new StrainForm();
        JPanel e = new ItemForm();
        JPanel f = new Lab_MemberForm();
        JPanel g = new LocationForm();
        JPanel h = new OrderForm();
        GridBagConstraints cn = new GridBagConstraints();
        cn.fill = GridBagConstraints.BOTH;
        formPanel.add(a, cn);
        formPanel.setLayer(a, 7);
        formPanel.add(b, cn);
        formPanel.setLayer(b, 6);
        formPanel.add(c, cn);
        formPanel.setLayer(c, 5);
        formPanel.add(d, cn);
        formPanel.setLayer(d, 4);
        formPanel.add(e, cn);
        formPanel.setLayer(e, 3);
        formPanel.add(f, cn);
        formPanel.setLayer(f, 2);
        formPanel.add(g, cn);
        formPanel.setLayer(g, 1);
        formPanel.add(h, cn);
        formPanel.setLayer(h, 0);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        searchBar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        searchTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        typeBox = new javax.swing.JComboBox<>();
        formPanel = new javax.swing.JLayeredPane();
        jPanel4 = new javax.swing.JPanel();
        ViewPane = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        selectionTable = new javax.swing.JTable();
        removeItemButton = new javax.swing.JButton();
        addItemButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        searchTable.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() != 0){
                    JFrame f= new JFrame();
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    try {
                        int row = searchTable.getSelectedRow();
                        bean.getRowSet().absolute(row + 1);
                    } catch (SQLException ex) {

                    }
                    JPanel p = bean.getForm();
                    f.setTitle("Edit");
                    f.getContentPane().setLayout(new GridBagLayout());
                    f.getContentPane().add(p);
                    f.setSize(600, 280);
                    f.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {

            }

            @Override
            public void mouseReleased(MouseEvent arg0) {

            }

            @Override
            public void mouseEntered(MouseEvent arg0) {

            }

            @Override
            public void mouseExited(MouseEvent arg0) {

            }

        });
        jScrollPane4.setViewportView(searchTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton)
                .addGap(25, 269, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Find", jPanel2);

        typeBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[]{ "Antibody", "Chemical", "Plasmid", "Strain", "Other Item", "Lab Member", "Location", "Order"}));
        typeBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                int type = cb.getSelectedIndex();
                Component[] cp = formPanel.getComponents();
                for(int i=0;i<cp.length;i++){
                    cp[i].setVisible(false);
                }
                cp[type].setVisible(true);
            }
        });

        formPanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(599, Short.MAX_VALUE))
            .addComponent(formPanel)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(typeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanel))
        );

        jTabbedPane3.addTab("New Record", jPanel3);

        RowSet item_loc = Connector.executeRowSet("SELECT * FROM item_location_view");
        jTable1.setModel(new RowSetModel(item_loc));
        jScrollPane2.setViewportView(jTable1);

        ViewPane.addTab("Item Locations", jScrollPane2);

        RowSet order_items = Connector.executeRowSet("SELECT * FROM order_item_view");
        jTable2.setModel(new RowSetModel(order_items));
        jScrollPane3.setViewportView(jTable2);

        ViewPane.addTab("Order Items", jScrollPane3);

        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ViewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ViewPane, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("View", jPanel4);

        jLabel1.setText("Choose Location");

        selectionTable.setModel(new RowSetModel(Connector.executeRowSet("SELECT * FROM Location")));
        jScrollPane1.setViewportView(selectionTable);

        removeItemButton.setText("Remove");
        removeItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemButtonActionPerformed(evt);
            }
        });

        addItemButton.setText("Add");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Pick Action:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(addItemButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeItemButton)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeItemButton)
                    .addComponent(addItemButton)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane3.addTab("Add/Remove Items", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        RowSet rs = null;
        String query = null;
        String searchText = searchBar.getText().trim();
        if (searchText.startsWith("query:")){
            query = searchText.substring(6);
        } else {
            query = Utils.searchToQuery(searchText);
        }
        
        if(query != null){
            rs = Connector.executeRowSet(query);
            searchTable.setModel(new RowSetModel(rs));
        } else {
            // TODO make all editable
            searchText = searchText.toLowerCase();
            switch(searchText){
                case "antibody":
                    bean = new AntibodyBean();
                break;
                case "chemical":
                    bean = new ChemicalBean();
                break;
                case "enzyme":
                    bean = new ItemBean("Enzyme");
                break;
                case "lab member":
                    bean = new Lab_MemberBean();
                break;
                case "liquid":
                    bean = new ItemBean("Liquid");
                break;
                case "location":
                    bean = new LocationBean();
                break;
                case "molecular":
                    bean = new ItemBean("Molecular Bio");
                break;
                case "order":
                    bean = new OrderBean();
                break;
                case "plasmid":
                    bean = new PlasmidBean();
                break;
                case "react probe":
                    bean = new ItemBean("React Probes");
                break;
                case "strain":
                    bean = new StrainBean();
                break;
                case "":
                    ;
                break;
                default:
                    ;
                break;
            }
            searchTable.setModel(new RowSetModel(bean.getRowSet()));
        }

    }//GEN-LAST:event_searchButtonActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        int idx = selectionTable.getSelectedRow();
        if (idx != -1) {
            String id = Integer.toString((int) selectionTable.getModel().getValueAt(idx, 0));
            JFrame f = new AddRemoveUI(new ItemAdder(id, "Loc"));
            f.setSize(this.getWidth() + 200, this.getHeight());
            f.setVisible(true);
        }
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void removeItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemButtonActionPerformed
        int idx = selectionTable.getSelectedRow();
        if (idx != -1) {
            String id = Integer.toString((int) selectionTable.getModel().getValueAt(idx, 0));
            JFrame f = new AddRemoveUI(new ItemRemover(id, "Loc"));
            f.setSize(this.getWidth() + 200, this.getHeight());
            f.setVisible(true);
        }
    }//GEN-LAST:event_removeItemButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        if (evt.getActionCommand().equals("Refresh")) {
            RowSet order_items = Connector.executeRowSet("SELECT * FROM order_item_view");
            jTable2.setModel(new RowSetModel(order_items));
            
            RowSet item_loc = Connector.executeRowSet("SELECT * FROM item_location_view");
            jTable1.setModel(new RowSetModel(item_loc));
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ViewPane;
    private javax.swing.JButton addItemButton;
    private javax.swing.JLayeredPane formPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeItemButton;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JTable searchTable;
    private javax.swing.JTable selectionTable;
    private javax.swing.JComboBox<String> typeBox;
    // End of variables declaration//GEN-END:variables

    
}
