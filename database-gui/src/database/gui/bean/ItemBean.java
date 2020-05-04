/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Item;
import database.sql.Connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.*;

/**
 *
 * @author Xiao Luo
 */
public class ItemBean {
    
    private JdbcRowSet rs;
    private Connection connection;
    public ItemBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM Item");
        rs.execute();
        
        this.connection = Connector.getConnection();
    }
    
    public RowSet getRowSet(){
        return rs;
    }
    
    public Item create(Item a){
        String sql = "INSERT INTO Item(name, id, temp, producer," + a.getType() + ") VALUES(?,?,?,?, 1)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,a.getName());
            stm.setString(2,a.getID());
            stm.setInt(3,a.getTemp());
            stm.setString(4,a.getVendor());
            stm.execute();
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Item update(Item a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setInt(2, a.getTemp());
            stm.setString(3, a.getVendor());
            stm.setString(4, id);
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Item a){
        String sql2 = "DELETE FROM Item WHERE id=" + a.getID();
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Item getCurrent(){
        Item a = new Item();
        String[] types = {"antibody", "enzyme", "plasmid", "chemical", "strain", "react_probes", "mole_bio", "liquid"};
        int res = 0;
        try {
            rs.moveToCurrentRow();
            a.setId(rs.getString("id"));
            a.setName(rs.getString("name"));
            a.setTemp(rs.getInt("temp"));
            a.setVendor(rs.getString("producer"));
            int i = 0;
            while(res != 1){
                res = rs.getInt(types[i]);
                i++;
            }
            a.setType((res == 0)? null: types[i]);
        } catch (SQLException ex) {
            Logger.getLogger(ItemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
}
