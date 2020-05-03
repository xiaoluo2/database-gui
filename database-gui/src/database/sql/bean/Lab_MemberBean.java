/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Lab_Member;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;
/**
 *
 * @author Xiao Luo
 */
public class Lab_MemberBean {
    
    private JdbcRowSet rs;
    public Lab_MemberBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM Lab_Member");
        rs.execute();
    }
    
    public Lab_Member create(){
        throw new UnsupportedOperationException();
    }
    
    public Lab_Member update(){
        throw new UnsupportedOperationException();
    }
    
    public Lab_Member delete(){
        throw new UnsupportedOperationException();
    }
    
    public Lab_Member getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}