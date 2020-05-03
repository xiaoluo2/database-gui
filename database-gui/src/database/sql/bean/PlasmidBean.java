/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Plasmid;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;
/**
 *
 * @author Xiao Luo
 */
public class PlasmidBean {
    
    private JdbcRowSet rs;
    public PlasmidBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM plasmid_item_view");
        rs.execute();
    }
    
    public Plasmid create(){
        throw new UnsupportedOperationException();
    }
    
    public Plasmid update(){
        throw new UnsupportedOperationException();
    }
    
    public Plasmid delete(){
        throw new UnsupportedOperationException();
    }
    
    public Plasmid getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}
