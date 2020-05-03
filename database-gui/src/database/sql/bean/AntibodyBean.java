/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Antibody;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;

/**
 *
 * @author Xiao Luo
 */
public class AntibodyBean {
    
    private JdbcRowSet rs;
    public AntibodyBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM antibody_item_view");
        rs.execute();
    }
    
    public Antibody create(){
        throw new UnsupportedOperationException();
    }
    
    public Antibody update(){
        throw new UnsupportedOperationException();
    }
    
    public Antibody delete(){
        throw new UnsupportedOperationException();
    }
    
    public Antibody getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}
