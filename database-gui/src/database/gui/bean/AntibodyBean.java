/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Antibody;
import database.gui.entity.Entity;
import database.gui.forms.AntibodyForm;
import database.sql.Connector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.*;
import javax.sql.RowSet;
import javax.swing.JPanel;

/**
 *
 * @author Xiao Luo
 */
public class AntibodyBean implements Bean {
    
    private JdbcRowSet rs;
    public AntibodyBean() {
        try {
            this.rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            String sql = "SELECT * FROM antibody_item_view";
            rs.setCommand(sql);
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(AntibodyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public RowSet getRowSet(){
        return rs;
    }
    
    @Override
    public JPanel getForm(){
        AntibodyForm form = new AntibodyForm(this);
        return form;
    }
    
    public Antibody create(Antibody a){
        String sql = "INSERT INTO Item(name, id, temp, producer, antibody) VALUES(?,?,?,?,1)";
        try{
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1,a.getName());
                stm.setString(2,a.getID());
                stm.setInt(3,a.getTemp());
                stm.setString(4,a.getVendor());
                System.out.println(stm);
                stm.execute();
                sql = "INSERT INTO Antibody(item_id, host) VALUES(?,?)";
                stm = connection.prepareStatement(sql);
                stm.setString(1, a.getID());
                stm.setString(2, a.getHost());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return a;
    }
    
    public Antibody update(Antibody a){
        String sql = "UPDATE Item SET name=?, temp=?, producer=? WHERE id=?";
        String id = a.getID();
        try {
            Connection connection = Connector.getConnection();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getName());
            stm.setInt(2, a.getTemp());
            stm.setString(3, a.getVendor());
            stm.setString(4, id);
            stm.execute();
            sql = "UPDATE Antibody SET host=? WHERE item_id=?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, a.getHost());
            stm.setString(2, id);
            System.out.println(stm);
            stm.execute();
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Antibody a){
        String sql = "DELETE FROM Antibody WHERE item_id=" + a.getID();
        String sql2 = "DELETE FROM Item WHERE id=" + a.getID();
        try {
            Connection connection = Connector.getConnection();
            Statement stm = connection.createStatement();
            stm.executeUpdate(sql);
            stm.executeUpdate(sql2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    @Override
    public Antibody getCurrent(){
        Antibody a = new Antibody();
        try {
            if (rs.getRow() != 0){
//            rs.moveToCurrentRow();
            a.setId(rs.getString("id"));
            a.setName(rs.getString("name"));
            a.setHost(rs.getString("host"));
            a.setTemp(rs.getInt("temp"));
            a.setVendor(rs.getString("producer"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AntibodyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    @Override
    public Antibody create(Entity e) {
        return create((Antibody)e);
    }

    @Override
    public Antibody update(Entity e) {
        return update((Antibody)e);
    }

    @Override
    public void delete(Entity e) {
        delete((Antibody)e);
    }

    @Override
    public void setRs(JdbcRowSet rs) {
        this.rs = rs;
    }
}
