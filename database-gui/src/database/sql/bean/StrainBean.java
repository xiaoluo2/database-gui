/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Strain;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;
/**
 *
 * @author Xiao Luo
 */
public class StrainBean {
    
    private JdbcRowSet rs;
    public StrainBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM strain_item_view");
        rs.execute();
    }
    
    public Strain create(){
        throw new UnsupportedOperationException();
    }
    
    public Strain update(){
        throw new UnsupportedOperationException();
    }
    
    public Strain delete(){
        throw new UnsupportedOperationException();
    }
    
    public Strain getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}
