/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql.entities;



/**
 * Interface to display data in tables
 * @author Xiao Luo
 */
public interface Entity {
    String[] getFieldNames();
    
    String[] getValues();
}
