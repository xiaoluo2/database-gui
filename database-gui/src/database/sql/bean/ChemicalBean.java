/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.bean;

import database.sql.entity.Chemical;
import database.sql.Connector;
import java.sql.SQLException;
import javax.sql.rowset.*;
/**
 *
 * @author Xiao Luo
 */
public class ChemicalBean {
    
    private JdbcRowSet rs;
    public ChemicalBean() throws SQLException{
        this.rs = RowSetProvider.newFactory().createJdbcRowSet();
        rs.setUrl(Connector.DB_URL);
        rs.setUsername(Connector.USER);
        rs.setPassword(Connector.PASS);
        rs.setCommand("SELECT * FROM chemical_item_view");
        rs.execute();
    }
    
    public Chemical create(){
        throw new UnsupportedOperationException();
    }
    
    public Chemical update(){
        throw new UnsupportedOperationException();
    }
    
    public Chemical delete(){
        throw new UnsupportedOperationException();
    }
    
    public Chemical getCurrent(){
        throw new UnsupportedOperationException();
    }
    
}
