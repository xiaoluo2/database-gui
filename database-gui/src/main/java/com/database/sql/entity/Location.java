/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql.entity;

import java.util.List;

/**
 *
 * @author Xiao Luo
 */
public class Location extends EntityTemplate implements Entity {
    private Integer loc_id;
    private Integer room_num;
    private String type;
    private String description;
    private Integer temperature;
    private List<Item> items;

    public Location(int loc_id, int room_num, String type, String description, int temperature) {
        this.loc_id = loc_id;
        this.room_num = room_num;
        this.type = type;
        this.description = description;
        this.temperature = temperature;
    }

    @Override
    public String getID() {
        return Integer.toString(loc_id);
    }

    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    public Integer getRoom_num() {
        return room_num;
    }

    public void setRoom_num(Integer room_num) {
        this.room_num = room_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
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
