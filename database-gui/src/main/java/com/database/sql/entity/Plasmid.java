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
    private String creator_id;
    private String creator_name;

    public Plasmid(String name, String item_id, String creator_id, String creator_name, String feature) {
        super(name, item_id, 0, creator_id);
        this.feature = feature;
        // TODO refer to object
        this.creator_id = ;
        this.creator_name = ;
    }
    
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    // TODO
    @Override
    public String[] getColNames() {
        String[] names = {"name", "item_id", "creator_id", "creator_name", "feature"};
       return names;
    }

    // TODO
    @Override
    public String[] getValues() {
        String[] values = new String[5];
        values[0] = this.getName();
        values[1] = this.getID();
        values[2] = this.
        values[3] = this.
        values[4] = this.getFeature();
        return values;
    }

}
