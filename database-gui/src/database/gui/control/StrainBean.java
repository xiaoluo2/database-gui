/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.control;

import database.gui.entity.Entity;
import database.gui.entity.Strain;
import database.gui.forms.StrainForm;
import database.gui.Connector;
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
public class StrainBean implements Bean {
    
    private JdbcRowSet rs;
    
    public StrainBean() {
        try {
            this.rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM strain_item_view");
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(StrainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public RowSet getRowSet(){
        return rs;
    }

    
    @Override
    public JPanel getForm(){
        StrainForm form = new StrainForm(this);
        form.setInsert(false);
        return form;
    }
    
    public Strain create(Strain a){
        String sql = "INSERT INTO Item(name, id, producer, strain) VALUES(?,?,?,1)";
        try{
            try(Connection connection = Connector.getConnection()){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1,a.getName());
                stm.setString(2,a.getID());
                stm.setString(3,a.getVendor());
                stm.executeUpdate();
                sql = "INSERT INTO Strain(item_id, anti_res, features) VALUES('" + a.getID() + "','" + a.getAnti_res() + "','" + a.getFeatures() + "')";
                stm = connection.prepareStatement(sql);
                stm.executeUpdate();
            }
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return a;
    }
    
    public Strain update(Strain a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            try(Connection connection = Connector.getConnection()){
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, a.getName());
                stm.setInt(2, a.getTemp());
                stm.setString(3, a.getVendor());
                stm.setString(4, id);
                stm.executeUpdate();
                sql = "UPDATE Strain SET anti_res=?, features=? WHERE item_id=?";
                stm = connection.prepareStatement(sql);
                stm.setString(1, a.getAnti_res());
                stm.setString(2, a.getFeatures());
                stm.setString(3, id);
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Strain a){
        String sql = "DELETE FROM Strain WHERE item_id='" + a.getID() + "'";
        String sql2 = "DELETE FROM Item WHERE id='" + a.getID() + "'";
        try {
            try(Connection connection = Connector.getConnection()){
                Statement stm = connection.createStatement();
                stm.executeUpdate(sql);
                stm.executeUpdate(sql2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StrainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Strain getCurrent(){
        Strain a = new Strain();
        try {
            if (rs.getRow() != 0){
                a.setId(rs.getString("id"));
                a.setName(rs.getString("name"));
                a.setAnti_res(rs.getString("anti_res"));
                a.setFeatures(rs.getString("features"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StrainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    @Override
    public Strain create(Entity e) {
        return create((Strain)e);
    }

    @Override
    public Strain update(Entity e) {
        return update((Strain)e);
    }

    @Override
    public void delete(Entity e) {
        delete((Strain)e);
    }    

    @Override
    public void setRs(JdbcRowSet rs) {
        this.rs = rs;
    }
}
