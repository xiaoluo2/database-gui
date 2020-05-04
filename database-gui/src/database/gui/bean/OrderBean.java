/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Order;
import database.sql.Connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.*;

/**
 *
 * @author Xiao Luo
 */
public class OrderBean {
    
    private JdbcRowSet rs;
    private Connection connection;
    public OrderBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM Order");
        rs.execute();
        
        this.connection = Connector.getConnection();
    }
    
    public Order create(Order a){
        String sql = "INSERT INTO Order(order_id, vendor, order_status, order_date. lab_id) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,Integer.parseInt(a.getID()));
            stm.setString(2,a.getVendor());
            stm.setString(3,a.getStatus());
            stm.setString(4,a.getDate());
            stm.setInt(5,a.getRequester());
            stm.execute();
            stm.executeUpdate(sql);
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Order update(Order a){
        String sql = "UPDATE Order SET vendor=?, order_status=?, order_date=?, lab_id=? WHERE order_id=?";
        String id = a.getID();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getVendor());
            stm.setString(2, a.getStatus());
            stm.setString(3, a.getDate());
            stm.setInt(4, a.getRequester());
            stm.setInt(5, Integer.parseInt(id));
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Order a){
        String sql = "DELETE FROM Order WHERE order_id=" + a.getID();
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Order getCurrent(){
        Order a = new Order();
        try {
            rs.moveToCurrentRow();
            a.setOrder_id(Integer.toString((rs.getInt("order_id"))));
            a.setVendor(rs.getString("vendor"));
            a.setStatus(rs.getString("order_status"));
            a.setDate(rs.getString("order_date"));
            a.setRequester(rs.getInt("lab_id"));
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
}
