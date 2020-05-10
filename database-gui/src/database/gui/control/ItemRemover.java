/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.control;

import database.gui.Connector;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;

/**
 *
 * @author Xiao Luo
 */
public class ItemRemover {
    private String id;
    private RowSet itemRowSet;
    private String target;
    
    public ItemRemover(String id, String target){
        this.id = id;
        this.target = target;
        int idNo = Integer.parseInt(id);
        String sql = "SELECT * FROM item_view WHERE id IN (SELECT item_id FROM "
            + this.target + "_Item WHERE " + this.target.toLowerCase() + "_id=" + idNo + ")";
        itemRowSet = Connector.executeRowSet(sql);
    }
    
    public RowSet getRowSet(){
        return itemRowSet;
    }

    public void removeSelected(int[] selection){
        String[] items = new String[selection.length];
        try {
            for(int i = 0; i < selection.length; i++){
                itemRowSet.absolute(selection[i] + 1);
                items[i] = itemRowSet.getString("id") + "'";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Connection conn = Connector.getConnection();
        try {
            String prefix = "DELETE FROM " + target + "_Item WHERE item_id ='";
            String sql = "";
            
            Statement stm = conn.createStatement();
            for (int i= 0; i < items.length; i++) {
                stm.addBatch(prefix + items[i]);
            }
            stm.executeBatch();
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ItemAdder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RowSet getItemRowSet() {
        return itemRowSet;
    }

    public void setItemRowSet(RowSet itemRowSet) {
        this.itemRowSet = itemRowSet;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
}
