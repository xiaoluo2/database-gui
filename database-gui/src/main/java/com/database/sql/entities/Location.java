/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql.entities;

/**
 *
 * @author Xiao Luo
 */
public class Location {
    private long loc_id;
    private Integer room_num;
    private String type;
    private String description;
    private Integer temperature;

    public Location(long loc_id, Integer room_num, String type, String description, Integer temperature) {
        this.loc_id = loc_id;
        this.room_num = room_num;
        this.type = type;
        this.description = description;
        this.temperature = temperature;
    }

    public long getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(long loc_id) {
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
    
    
}
