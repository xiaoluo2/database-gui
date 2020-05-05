/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Entity;
import database.gui.entity.Strain;
import database.gui.forms.StrainForm;
import database.sql.Connector;
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
public class StrainBean extends Bean {
    
    private JdbcRowSet rs;
    private Connection connection;
    public StrainBean() {
        try {
            this.rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM strain_item_view");
            rs.execute();
            
            this.connection = Connector.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(StrainBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public RowSet getRowSet(){
        return rs;
    }
    
    public JPanel getForm(){
        StrainForm form = new StrainForm(this);
        form.setInsert(false);
        return form;
    }
    
    public JPanel getEmptyForm(){
        StrainForm form = new StrainForm(this);
        form.setInsert(true);
        form.setVisible(false);
        return form;
    }
    
    public Strain create(Strain a){
        String sql = "INSERT INTO Item(name, id, temp, producer, strain) VALUES(?,?,?,?,1)";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,a.getName());
            stm.setString(2,a.getID());
            stm.setInt(3,a.getTemp());
            stm.setString(4,a.getVendor());
            stm.execute();
            sql = "INSERT INTO Strain(item_id, anti_res, features) VALUES(" + a.getID() + "," + a.getAnti_res() + "," + a.getFeatures() + ")";
            stm.executeUpdate(sql);
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Strain update(Strain a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setInt(2, a.getTemp());
            stm.setString(3, a.getVendor());
            stm.setString(4, id);
            stm.execute();
            sql = "UPDATE Strain SET anti_res=?, features=? WHERE item_id=?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, a.getAnti_res());
            stm.setString(2, a.getFeatures());
            stm.setString(3, id);
            stm.execute();
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Strain a){
        String sql = "DELETE FROM Strain WHERE item_id=" + a.getID();
        String sql2 = "DELETE FROM Item WHERE id=" + a.getID();
        try {
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql2);
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
                a.setVendor(rs.getString("producer"));
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
}
