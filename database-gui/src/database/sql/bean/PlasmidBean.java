/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Plasmid;
import database.sql.Connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.*;

/**
 *
 * @author Xiao Luo
 */
public class PlasmidBean {
    
    private JdbcRowSet rs;
    private Connection connection;
    public PlasmidBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM plasmid_item_view");
        rs.execute();
        
        this.connection = Connector.getConnection();
    }
    
    public Plasmid create(Plasmid a){
        String sql = "INSERT INTO Item(name, id, temp, producer, plasmid) VALUES(?,?,?,?,1)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,a.getName());
            stm.setString(2,a.getID());
            stm.setInt(3,a.getTemp());
            stm.setString(4,a.getVendor());
            stm.execute();
            sql = "INSERT INTO Plasmid(item_id, lab_id, feature) VALUES(" + a.getID() + "," + a.getCreator_id() + a.getFeature() + ")";
            stm.executeUpdate(sql);
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Plasmid update(Plasmid a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setInt(2, a.getTemp());
            stm.setString(3, a.getVendor());
            stm.setString(4, id);
            sql = "UPDATE Plasmid SET feature=? WHERE item_id=?";
            stm = connection.prepareStatement(sql);
            stm.setString(2, a.getFeature());
            stm.setString(3, id);
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Plasmid a){
        String sql = "DELETE FROM Plasmid WHERE item_id=" + a.getID();
        String sql2 = "DELETE FROM Item WHERE id=" + a.getID();
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(PlasmidBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Plasmid getCurrent(){
        Plasmid a = new Plasmid();
        try {
            rs.moveToCurrentRow();
            a.setId(rs.getString("id"));
            a.setName(rs.getString("name"));
            a.setCreator_id(rs.getString("lab_id"));
            a.setCreator_name(rs.getString("lab_name"));
            a.setTemp(rs.getInt("temp"));
            a.setVendor(rs.getString("producer"));
            a.setFeature(rs.getString("feature"));
        } catch (SQLException ex) {
            Logger.getLogger(PlasmidBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
}