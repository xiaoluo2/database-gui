/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.entity;

import java.util.List;

/**
 *
 * @author Xiao Luo
 */
public class Location extends EntityTemplate implements Entity {
    private Integer loc_id;
    private Integer room_num;
    private String loc_type;
    private Integer temperature;
    private List<String> items;

    public Location(int loc_id, int room_num, String type, int temperature) {
        this.loc_id = loc_id;
        this.room_num = room_num;
        this.loc_type = type;
        this.temperature = temperature;
    }

    public Location() {
        this(0, 0, null, 9999);
        
    }
    
    public void addItem(String itemID){
        items.add(itemID);
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
        return loc_type;
    }

    public void setType(String description) {
        this.loc_type = description;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }
    
}
