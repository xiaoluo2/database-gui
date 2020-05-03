/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Location;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;
/**
 *
 * @author Xiao Luo
 */
public class LocationBean {
    
    private JdbcRowSet rs;
    public LocationBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM Location");
        rs.execute();
    }
    
    public Location create(){
        throw new UnsupportedOperationException();
    }
    
    public Location update(){
        throw new UnsupportedOperationException();
    }
    
    public Location delete(){
        throw new UnsupportedOperationException();
    }
    
    public Location getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}