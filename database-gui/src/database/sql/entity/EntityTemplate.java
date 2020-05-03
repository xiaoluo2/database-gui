/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.entity;

import java.util.regex.Pattern;  
import java.util.regex.Matcher;   
/**
 *
 * @author Xiao Luo
 */
public abstract class EntityTemplate {

    // By default class name and table name will match
    public String getTableName() {
        String className  = this.getClass().toString();
        String tableName;
        String pattern = "\\.\\w*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(className);
        if (m.find()){
            tableName = m.group(0).substring(1);
        } else {
            tableName = "";
        }
        return tableName;
    }
    
}
