/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql;
   
import java.util.List;    
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper;  

public class MySQLQueries {
    
    @Autowired
    JdbcTemplate template;    

    public void setTemplate(JdbcTemplate template) {    
        this.template = template;    
    }   

    // Search logic
    public Object[] getSearchResult(String searchText){
        return new Object[10];
    }

    public void insertNewRecord(String[] fields, String[] values){

    }

    public void getView(String viewName){

    }

}