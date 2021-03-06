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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Xiao Luo
 */
public class ItemAdder {
    private RowSet itemRowSet;
    private String id;
    private String target;
    
    public ItemAdder(String id, String target){
        this.id = id;
        this.target = target;
        int idNo = Integer.parseInt(id);
        String sql = "SELECT * FROM item_view WHERE id NOT IN (SELECT item_id FROM "
            + this.target + "_Item WHERE " + this.target.toLowerCase() + "_id=" + idNo + ")";
        itemRowSet = Connector.executeRowSet(sql);
    }
    
    public RowSet getRowSet(){
        return itemRowSet;
    }
    
    public void addSelected(int[] selection){
        String[] items = new String[selection.length];
        try {
            for(int i = 0; i < selection.length; i++){
                itemRowSet.absolute(selection[i] + 1);
                items[i] = "(" + id + ",'" + itemRowSet.getString("id") + "')";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        String rows = String.join(",", items);
        String sql = "INSERT INTO " + target + "_Item(" + target.toLowerCase() + "_id, item_id) VALUES " + rows;
//        System.out.println(sql);
        Connection conn = Connector.getConnection();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(sql);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public RowSet getItemRowSet() {
        return itemRowSet;
    }

    public void setItemRowSet(RowSet itemRowSet) {
        this.itemRowSet = itemRowSet;
    }

    public String getAttachedId() {
        return id;
    }

    public void setAttachedId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
