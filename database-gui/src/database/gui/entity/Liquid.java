/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.entity;

/**
 *
 * @author Xiao Luo
 */
public class Liquid extends Item {

    public Liquid(String name, String id, int required_tmp, String vendor) {
        super(name, id, required_tmp, vendor, "liquid");
    }
    
    @Override
    public String getTableName(){
        return "Item";
    }
    
}
