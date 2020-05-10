/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.control;

import database.gui.entity.Entity;
import database.gui.entity.Item;
import database.gui.forms.ItemForm;
import database.gui.Connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.*;
import javax.swing.JPanel;

/**
 *
 * @author Xiao Luo
 */
public class ItemBean implements Bean {
    
    private RowSet rs;
    
    public ItemBean() {
        try {
            this.rs = RowSetProvider.newFactory().createCachedRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM item_view");
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ItemBean(String type) {
        try {
            this.rs = RowSetProvider.newFactory().createCachedRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM item_view WHERE type='" + type + "'");
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public RowSet getRowSet(){
        return rs;
    }
    
    
    @Override
    public JPanel getForm(){
        ItemForm form = new ItemForm(this);
        return form;
    }
    
    public Item create(Item a){
        String sql = "INSERT INTO Item(name, id, temp, producer," + a.getType() + ") VALUES(?,?,?,?, 1)";
        
        try{
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1,a.getName());
                stm.setString(2,a.getID());
                stm.setInt(3,a.getTemp());
                stm.setString(4,a.getVendor());
                stm.executeUpdate();
            }
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Item update(Item a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, a.getName());
                stm.setInt(2, a.getTemp());
                stm.setString(3, a.getVendor());
                stm.setString(4, id);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Item a){
    	String sql1 = "DELETE FROM Order_Item WHERE item_id='" + a.getID() + "'";
        String sql2 = "DELETE FROM Item WHERE id='" + a.getID() + "'";
        try {
            try (Connection connection = Connector.getConnection()) {
                Statement stm = connection.createStatement();
                stm.addBatch(sql1);
                stm.addBatch(sql2);
                stm.executeBatch();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Item getCurrent(){
        Item a = new Item();
        try {
            if (rs.getRow() != 0){
                a.setId(rs.getString("id"));
                a.setName(rs.getString("name"));
                a.setTemp(rs.getInt("temp"));
                a.setVendor(rs.getString("producer"));
                a.setType(rs.getString("type"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    @Override
    public Item create(Entity e) {
        return create((Item)e);
    }

    @Override
    public Item update(Entity e) {
        return update((Item)e);
    }

    @Override
    public void delete(Entity e) {
        delete((Item)e);
    }

    @Override
    public void setRs(JdbcRowSet rs) {
        this.rs = rs;
    }
}
