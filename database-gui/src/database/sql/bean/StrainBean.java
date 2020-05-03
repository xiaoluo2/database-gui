/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Strain;
import database.sql.Connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.*;

/**
 *
 * @author Xiao Luo
 */
public class StrainBean {
    
    private JdbcRowSet rs;
    private Connection connection;
    public StrainBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM antibody_item_view");
        rs.execute();
        
        this.connection = Connector.getConnection();
    }
    
    public Strain create(Strain a){
        String sql = "INSERT INTO Item(name, id, temp, producer, antibody) VALUES(?,?,?,?,1)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,a.getName());
            stm.setString(2,a.getID());
            stm.setInt(3,a.getTemp());
            stm.setString(4,a.getVendor());
            stm.execute();
            sql = "INSERT INTO Strain(host) VALUES(" + a.getHost() + ")";
            stm.executeUpdate(sql);
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Strain update(Strain a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setInt(2, a.getTemp());
            stm.setString(3, a.getVendor());
            stm.setString(4, id);
            sql = "UPDATE Strain SET host=? WHERE item_id=?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, a.getHost());
            stm.setString(2, id);
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Strain a){
        String sql = "DELETE FROM Strain WHERE item_id=" + a.getID();
        String sql2 = "DELETE FROM Item WHERE id=" + a.getID();
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql2);
        } catch (SQLException ex) {
            Logger.getLogger(StrainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Strain getCurrent(){
        Strain a = new Strain();
        try {
            rs.moveToCurrentRow();
            a.setId(rs.getString("id"));
            a.setName(rs.getString("name"));
            a.setHost(rs.getString("host"));
            a.setTemp(rs.getInt("temp"));
            a.setVendor(rs.getString("producer"));
        } catch (SQLException ex) {
            Logger.getLogger(StrainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
}
