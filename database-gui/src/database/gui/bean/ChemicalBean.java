/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Chemical;
import database.sql.Connector;
import javax.sql.rowset.*;
import java.sql.*;
import javax.sql.RowSet;
/**
 *
 * @author Xiao Luo
 */
public class ChemicalBean {
    
    private JdbcRowSet rs;
    private Connection connection;
    public ChemicalBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM chemical_item_view");
        rs.execute();
        
        this.connection = Connector.getConnection();
    }
    
    public RowSet getRowSet(){
        return rs;
    }
    
    public Chemical create(Chemical a){
        String sql = "INSERT INTO Item(name, id, temp, producer, chemical) VALUES(?,?,?,?,?,1)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,a.getName());
            stm.setString(2,a.getID());
            stm.setInt(3,a.getTemp());
            stm.setString(4,a.getVendor());
            stm.execute();
            sql = "INSERT INTO Chemical(item_id, amount) VALUES(" + a.getID() + "," + a.getAmount() + ")";
            stm.executeUpdate(sql);
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Chemical update(Chemical a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setInt(2, a.getTemp());
            stm.setString(3, a.getVendor());
            stm.setString(4, id);
            sql = "UPDATE Chemical SET amount=? WHERE item_id=?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, a.getAmount());
            stm.setString(2, id);
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Chemical a){
        String sql = "DELETE FROM Chemical WHERE item_id=" + a.getID();
        String sql2 = "DELETE FROM Item WHERE id=" + a.getID();
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql2);
        } catch (SQLException ex) {

        }
        
    }
    
    public Chemical getCurrent(){
        Chemical a = new Chemical();
        try {
            rs.moveToCurrentRow();
            a.setId(rs.getString("id"));
            a.setName(rs.getString("name"));
            a.setAmount(rs.getString("amount"));
            a.setTemp(rs.getInt("temp"));
            a.setVendor(rs.getString("producer"));
        } catch (SQLException ex) {
            
        }
        return a;
    }
    
}