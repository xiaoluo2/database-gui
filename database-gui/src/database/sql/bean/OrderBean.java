/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Order;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;
/**
 *
 * @author Xiao Luo
 */
public class OrderBean {
    
    private JdbcRowSet rs;
    public OrderBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM Order");
        rs.execute();
    }
    
    public Order create(){
        throw new UnsupportedOperationException();
    }
    
    public Order update(){
        throw new UnsupportedOperationException();
    }
    
    public Order delete(){
        throw new UnsupportedOperationException();
    }
    
    public Order getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}