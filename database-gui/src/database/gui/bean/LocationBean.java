/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Entity;
import database.gui.entity.Location;
import database.gui.forms.LocationForm;
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
public class LocationBean implements Bean {
    
    private JdbcRowSet rs;
    
    public LocationBean() {
        try {
            this.rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM Location");
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public RowSet getRowSet(){
        return rs;
    }
    
    @Override
    public JPanel getForm(){
        LocationForm form = new LocationForm(this);
        return form;
    }
    
    public Location create(Location a){
        String sql = "INSERT INTO Location(location_id, room_no, loc_type, temperature) VALUES(?,?,?,?)";
        try{
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1,Integer.parseInt(a.getID()));
                stm.setInt(2,a.getRoom_num());
                stm.setString(3,a.getType());
                stm.setInt(4,a.getTemperature());
                stm.execute();
                stm.executeUpdate(sql);
            }
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Location update(Location a){
        String sql = "UPDATE Location SET room_no=?, loc_type=?, temperature=? WHERE location_id=?";
        int id = Integer.parseInt(a.getID());
        try {
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, a.getRoom_num());
                stm.setString(2, a.getType());
                stm.setInt(3, a.getTemperature());
                stm.setInt(4, id);
            }
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Location a){
        String sql = "DELETE FROM Location WHERE location_id=" + a.getID();
        try {
            try (Connection connection = Connector.getConnection()) {
                Statement stm = connection.createStatement();
                stm.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Location getCurrent(){
        Location a = new Location();
        try {
            if (rs.getRow() != 0){
                a.setLoc_id(rs.getInt("location_id"));
                a.setRoom_num(rs.getInt("room_no"));
                a.setType(rs.getString("loc_type"));
                a.setTemperature(rs.getInt("temperature"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    @Override
    public Location create(Entity e) {
        return create((Location)e);
    }

    @Override
    public Location update(Entity e) {
        return update((Location)e);
    }

    @Override
    public void delete(Entity e) {
        delete((Location)e);
    }    

    @Override
    public void setRs(JdbcRowSet rs) {
        this.rs = rs;
    }

}
