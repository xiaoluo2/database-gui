/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.entity;

/**
 *
 * @author Xiao Luo
 */
public class Enzyme extends Item {

    public Enzyme(String name, String id, int required_tmp, String vendor) {
        super(name, id, required_tmp, vendor);
    }
    
    @Override
    public String getTableName(){
        return "Item";
    }
    
}