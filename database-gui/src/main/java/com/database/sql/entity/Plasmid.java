/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql.entity;

/**
 *
 * @author Xiao Luo
 */
public class Plasmid extends Item implements Entity {
    private String feature;
    private String creator;

    public Plasmid(String name, String item_id, String creator_id, String creator_name, String feature) {
        super(name, item_id, 0, creator_id);
        this.feature = feature;
        // TODO refer to object
        this.creator = creator_id;
    }
    
    // TODO
    @Override
    public String[] getColNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO
    @Override
    public String[] getValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
