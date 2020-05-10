/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.control;

import database.gui.entity.Entity;
import database.gui.entity.Order;
import database.gui.forms.OrderForm;
import database.gui.Connector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class OrderBean implements Bean {
    
    private JdbcRowSet rs;
    
    public OrderBean() {
        try {
            this.rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM `Order`");
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public RowSet getRowSet(){
        return rs;
    }
    

    
    @Override
    public JPanel getForm(){
        OrderForm form = new OrderForm(this);
        form.setInsert(false);
        return form;
    }
    
    public Order create(Order a){
        String sql = "INSERT INTO `Order`(order_id, order_status, order_date, lab_id) VALUES(?,?,?,?)";
        try{
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1,Integer.parseInt(a.getID()));
                stm.setString(2,a.getStatus());
                stm.setString(3,a.getDate());
                stm.setInt(4,a.getRequester());
//                System.out.println(stm);
                stm.executeUpdate();
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return a;
    }
    
    public Order update(Order a){
        String sql = "UPDATE `Order` SET order_status=?, order_date=?, lab_id=? WHERE order_id=?";
        String id = a.getID();
        try {Connection connection = Connector.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getStatus());
            stm.setString(2, a.getDate());
            stm.setInt(3, a.getRequester());
            stm.setInt(4, Integer.parseInt(id));
            stm.executeUpdate();
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Order a){
    	String sql1 = "DELETE FROM Order_Item WHERE order_id=" + a.getID() + "";
        String sql2 = "DELETE FROM `Order` WHERE order_id=" + a.getID() + "";
        try {
            try (Connection connection = Connector.getConnection()) {
            	
                Statement stm = connection.createStatement();
                stm.addBatch(sql1);
                stm.addBatch(sql2);
                stm.executeBatch();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Order getCurrent(){
        Order a = new Order();
        try {
            if (rs.getRow() != 0){
                a.setOrder_id(Integer.toString((rs.getInt("order_id"))));
                a.setStatus(rs.getString("order_status"));
                a.setDate(rs.getString("order_date"));
                a.setRequester(rs.getInt("lab_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    @Override
    public Entity create(Entity e) {
        return create((Order)e);
    }

    @Override
    public Entity update(Entity e) {
        return update((Order)e);
    }

    @Override
    public void delete(Entity e) {
        delete((Order)e);
    }    

    @Override
    public void setRs(JdbcRowSet rs) {
        this.rs = rs;
    }
}
