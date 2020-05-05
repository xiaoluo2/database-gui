/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.bean;

import database.gui.entity.Entity;
import database.gui.entity.Lab_Member;
import database.gui.forms.Lab_MemberForm;
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
public class Lab_MemberBean implements Bean {
    
    private JdbcRowSet rs;
    
    public Lab_MemberBean() {
        try {
            this.rs = RowSetProvider.newFactory().createJdbcRowSet();
            rs.setUrl(Connector.DB_URL);
            rs.setUsername(Connector.USER);
            rs.setPassword(Connector.PASS);
            rs.setCommand("SELECT * FROM Lab_Member");
            rs.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(Lab_MemberBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public RowSet getRowSet(){
        return rs;
    }
    
    
    @Override
    public JPanel getForm(){
        Lab_MemberForm form = new Lab_MemberForm(this);
        return form;
    }
    
    public Lab_Member create(Lab_Member a){
        String sql = "INSERT INTO Lab_Member(Lab_id, name, title) VALUES(?,?,?)";
        try{
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1,Integer.parseInt(a.getID()));
                stm.setString(2,a.getName());
                stm.setString(3,a.getTitle());
                stm.execute();
                stm.executeUpdate(sql);
            }
        } catch(SQLException e){
            return null;
        }
        return a;
    }
    
    public Lab_Member update(Lab_Member a){
        String sql = "UPDATE Lab_Member SET name=?, title=? WHERE Lab_id=?";
        int id = Integer.parseInt(a.getID());
        try {
            try (Connection connection = Connector.getConnection()) {
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, a.getName());
                stm.setString(2, a.getTitle());
                stm.setInt(3, id);
            }
        } catch (SQLException ex) {
            return null;
        }
        return a;
    }
    
    public void delete(Lab_Member a){
        String sql = "DELETE FROM Lab_Member WHERE Lab_id=" + a.getID();
        try {
            try (Connection connection = Connector.getConnection()) {
                Statement stm = connection.createStatement();
                stm.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab_MemberBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public Lab_Member getCurrent(){
        Lab_Member a = new Lab_Member();
        try {
            if (rs.getRow() != 0){
                a.setMember_id(rs.getInt("Lab_id"));
                a.setName(rs.getString("name"));
                a.setTitle(rs.getString("title"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Lab_MemberBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    @Override
    public Lab_Member create(Entity e) {
        return create((Lab_Member)e);
    }

    @Override
    public Lab_Member update(Entity e) {
        return update((Lab_Member)e);
    }

    @Override
    public void delete(Entity e) {
        delete((Lab_Member)e);
    }

    @Override
    public void setRs(JdbcRowSet rs) {
        this.rs = rs;
    }
    
}