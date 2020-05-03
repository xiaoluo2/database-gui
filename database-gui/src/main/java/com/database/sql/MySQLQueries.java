/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql;

import com.database.sql.entity.*;
   
import java.util.List;    
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;  
import com.database.sql.entity.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLQueries {
    
    @Autowired
    JdbcTemplate template;    

    public void setTemplate(JdbcTemplate template) {    
        this.template = template;    
    }   

    // Search TODO
    public Entity[] getSearchResult(String searchText){
        throw new UnsupportedOperationException();
    }

    public void saveChangesToSearchTable(){
        throw new UnsupportedOperationException();
    }

    // New Record TODO
    public void insertNewRecord(Entity e){
        String queryString;
        String tableName = e.getTableName();
        String colNames = String.join(",", e.getColNames());
        String values = String.join(",", e.getValues());
        queryString = String.format("INSERT INTO %s(%s) VALUES(%s)", tableName, colNames, values);
        template.update(queryString);
    }
    
    public void updateRow(String tableName, String id, String[] columnNames, String[] values){
        String queryString;
        String assign = "";
        for(int i=0; i < columnNames.length; i++){
            assign = assign.concat(columnNames[i] + "=" + values[i] + ", ");
        }
        queryString = String.format("UPDATE %s SET %s WHERE id=%s", tableName, assign, id);
        template.update(queryString);
    }

    public List<Antibody> getAntibodyView(){
        String sql = "SELECT * FROM Antibody_view";
        List<Antibody> list = template.query(sql, new RowMapper(){
 
        public Antibody mapRow(ResultSet rs, int rowNum)
            throws SQLException {                  
                return new Antibody(rs.getString("name"), rs.getString("id"), rs.getInt("temp"), rs.getString("producer"), rs.getString("host"));
        }});
        
        return list;
    }
    
    public List<Chemical> getChemicalView(){
        String sql = "SELECT * FROM Chemical_view";
        List<Chemical> list = template.query(sql, new RowMapper(){
 
        public Chemical mapRow(ResultSet rs, int rowNum)
            throws SQLException {                   
                return new Chemical(rs.getString("name"), rs.getString("id"), rs.getInt("tmp"), rs.getString("producer"), rs.getString("amount"));
        }});
        
        return list;
    }
    
    public List getItemData(){
        String sql = "SELECT * FROM Item LEFT JOIN Antibody ON Item.id = Antibody.item_id LEFT JOIN Chemical ON Item.id = Chemical.item_id LEFT JOIN Plasmid ON Item.id = Plasmid.item_id LEFT JOIN Strain ON Item.id = Strain.item_id";
        List<Item> list = template.query(sql, new RowMapper(){
 
        public Item mapRow(ResultSet rs, int rowNum)
            throws SQLException {
                // TODO get entire column
                return new Item(rs.getString("name"), rs.getString("id"), rs.getInt("temp"), rs.getString("vendor"));
        }});
        
        return list;
    }
    
    public List<Lab_Member> getLab_MemberData(){
        String sql = "SELECT * FROM Lab_Member";
        List<Lab_Member> list = template.query(sql, new RowMapper(){
 
        public Lab_Member mapRow(ResultSet rs, int rowNum)
            throws SQLException {                
                return new Lab_Member(rs.getString("member_id"), rs.getString("name"), rs.getString("title"));
        }});
        
        return list;
    }
    
    public List<Location> getLocationData(){
        String sql = "SELECT * FROM Location";
        List<Location> list = template.query(sql, new RowMapper(){
 
        public Location mapRow(ResultSet rs, int rowNum)
            throws SQLException {                  
                return new Location(rs.getInt("location_id"), rs.getInt("room_no"), rs.getString("description"), rs.getInt("temp"));
        }});
        
        return list;
    }
    
    public List<Order> getOrderData(){
        String sql = "SELECT * FROM Order";
        List<Order> list = template.query(sql, new RowMapper(){
 
        public Order mapRow(ResultSet rs, int rowNum)
            throws SQLException {                
                return new Order(rs.getString("order_id"), rs.getString("order_status"), rs.getString("order_date"), rs.getString("vendor"));
        }});
        
        return list;
    }
    
    public List<Plasmid> getPlasmidView(){
        String sql = "SELECT * FROM Plasmid_view";
        List<Plasmid> list = template.query(sql, new RowMapper(){
 
        public Plasmid mapRow(ResultSet rs, int rowNum)
            throws SQLException {                   
                return new Plasmid(rs.getString("name"), rs.getString("id"), rs.getString("lab_id"), rs.getString("lab_name"), rs.getString("feature"));
        }});
        
        return list;
        
    }
    
    public List<Strain> getStrainView(){
        String sql = "SELECT * FROM Strain_view";
        List<Strain> list = template.query(sql, new RowMapper(){
 
        public Strain mapRow(ResultSet rs, int rowNum)
            throws SQLException {                   
                return new Strain(rs.getString("name"), rs.getString("id"), rs.getString("anti_res"), rs.getString("features"));
        }});
        
        return list;
    }

}