/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.control;

import database.gui.Connector;
import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Array;
/**
 *
 * @author Xiao Luo
 */
public class ItemAdder {
    private RowSet itemRowSet;
    private String attachId;
    
    public ItemAdder(String id){
        // TODO create item view
        itemRowSet = Connector.executeRowSet("SELECT * FROM item_view");
    }
    
    public RowSet getRowSet(){
        return itemRowSet;
    }
    
    public void addSelectedtoOrder(int[] selection){
        String[] items = new String[selection.length];
        try {
            for(int i = 0; i < selection.length; i++){
                itemRowSet.absolute(selection[i] + 1);
                items[i] = "ROW(" + attachId + "," + itemRowSet.getString("id") + ")";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        String rows = String.join(" ", items);
        String sql = "INSERT INTO Order_Item(order_id, item_id) VALUES " + rows;
        
        Connection conn = Connector.getConnection();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removeSelectedfromOrder(int[] selection){
        String[] items = new String[selection.length];
        try {
            for(int i = 0; i < selection.length; i++){
                itemRowSet.absolute(selection[i] + 1);
                items[i] = itemRowSet.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "DELETE FROM Order_Item WHERE item_id IN (?)";
        
        Connection conn = Connector.getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            Array array = conn.createArrayOf("varchar", items);
            stm.setArray(1, array);
            stm.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void addSelectedtoLocation(int[] selection){
        String[] items = new String[selection.length];
        try {
            for(int i = 0; i < selection.length; i++){
                itemRowSet.absolute(selection[i] + 1);
                items[i] = "ROW(" + attachId + "," + itemRowSet.getString("id") + ")";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        String rows = String.join(" ", items);
        String sql = "INSERT INTO Loc_Item(loc_id, item_id) VALUES " + rows;
        
        Connection conn = Connector.getConnection();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        public void removeSelectedfromLocation(int[] selection){
        String[] items = new String[selection.length];
        try {
            for(int i = 0; i < selection.length; i++){
                itemRowSet.absolute(selection[i] + 1);
                items[i] = itemRowSet.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "DELETE FROM Loc_Item WHERE item_id IN (?)";
        
        Connection conn = Connector.getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            Array array = conn.createArrayOf("varchar", items);
            stm.setArray(1, array);
            stm.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
