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

    // Format of search is table_name:search
    // for example to search for John in table Lab Member
    // lab member:John
    // Just putting in the table name gives the whole table/view
    
    // sql: Make a select query
    public List<Entity> getSearchResult(String searchText){
        String[] args = searchText.trim().toLowerCase().split(":");
        String search;
        if (args.length > 2 || args.length < 1) {
            throw new Error();
        }
        if (args.length == 2) {
            search = args[1].trim();
        } else {
            search = null;
        }
        switch(args[1]){
            case "sql":
                // perform sql query
            case "antibody":
                if(search != null){
                    return getAntibodyView(search);
                } else {
                    return getAntibodyView();
                }
            case "chemical":
                if(search != null){
                    return getChemicalView(search);
                } else {
                    return getChemicalView();
                }
            case "lab member":
                if(search != null){
                    return getLab_MemberData(search);
                } else {
                    return getLab_MemberData();
                }
            case "location":
                if(search != null){
                    return getLocationData(search);
                } else {
                    return getLocationData();
                }
            case "plasmid":
                if(search != null){
                    return getPlasmidView(search);
                } else {
                    return getPlasmidView();
                }
            case "strain":
                if(search != null){
                    return getStrainView(search);
                } else {
                    return getStrainView();
                }


        }
        return getAntibodyView(); // placeholder
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

    public List<Entity> getAntibodyView(){
        String sql = "SELECT * FROM Antibody_view";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Antibody mapRow(ResultSet rs, int rowNum)
            throws SQLException {                  
                return new Antibody(rs.getString("name"), rs.getString("id"), rs.getInt("temp"), rs.getString("producer"), rs.getString("host"));
        }});
        
        return list;
    }
    
    public List<Entity> getChemicalView(){
        String sql = "SELECT * FROM Chemical_view";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Chemical mapRow(ResultSet rs, int rowNum)
            throws SQLException {                   
                return new Chemical(rs.getString("name"), rs.getString("id"), rs.getInt("tmp"), rs.getString("producer"), rs.getString("amount"));
        }});
        
        return list;
    }
    
    public List<Entity> getItemData(){
        String sql = "SELECT * FROM Item LEFT JOIN Antibody ON Item.id = Antibody.item_id LEFT JOIN Chemical ON Item.id = Chemical.item_id LEFT JOIN Plasmid ON Item.id = Plasmid.item_id LEFT JOIN Strain ON Item.id = Strain.item_id";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Item mapRow(ResultSet rs, int rowNum)
            throws SQLException {
                // TODO get entire column
                return new Item(rs.getString("name"), rs.getString("id"), rs.getInt("temp"), rs.getString("vendor"));
        }});
        
        return list;
    }
    
    public List<Entity> getLab_MemberData(){
        String sql = "SELECT * FROM Lab_Member";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Lab_Member mapRow(ResultSet rs, int rowNum)
            throws SQLException {                
                return new Lab_Member(rs.getString("member_id"), rs.getString("name"), rs.getString("title"));
        }});
        
        return list;
    }
    
    public List<Entity> getLocationData(){
        String sql = "SELECT * FROM Location";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Location mapRow(ResultSet rs, int rowNum)
            throws SQLException {                  
                return new Location(rs.getInt("location_id"), rs.getInt("room_no"), rs.getString("description"), rs.getInt("temp"));
        }});
        
        return list;
    }
    
    public List<Entity> getOrderData(){
        String sql = "SELECT * FROM Order";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Order mapRow(ResultSet rs, int rowNum)
            throws SQLException {                
                return new Order(rs.getString("order_id"), rs.getString("order_status"), rs.getString("order_date"), rs.getString("vendor"));
        }});
        
        return list;
    }
    
    public List<Entity> getPlasmidView(){
        String sql = "SELECT * FROM Plasmid_view";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Plasmid mapRow(ResultSet rs, int rowNum)
            throws SQLException {                   
                return new Plasmid(rs.getString("name"), rs.getString("id"), rs.getString("lab_id"), rs.getString("lab_name"), rs.getString("feature"));
        }});
        
        return list;
        
    }
    
    public List<Entity> getStrainView(){
        String sql = "SELECT * FROM Strain_view";
        List<Entity> list = template.query(sql, new RowMapper(){
 
        public Strain mapRow(ResultSet rs, int rowNum)
            throws SQLException {                   
                return new Strain(rs.getString("name"), rs.getString("id"), rs.getString("anti_res"), rs.getString("features"));
        }});
        
        return list;
    }

    private List<Entity> getAntibodyView(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Entity> getStrainView(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Entity> getPlasmidView(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Entity> getLocationData(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Entity> getLab_MemberData(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<Entity> getChemicalView(String search) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}