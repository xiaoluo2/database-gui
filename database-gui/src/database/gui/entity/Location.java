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
public class Location extends EntityTemplate implements Entity {
    private Integer loc_id;
    private Integer room_num;
    private String description;
    private Integer temperature;

    public Location(int loc_id, int room_num, String description, int temperature) {
        this.loc_id = loc_id;
        this.room_num = room_num;
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
        String[] names = {"location_id", "room_no", "loc_type", "temperature"};
        return names;
    }

    // TODO
    @Override
    public String[] getValues() {
        String[] values = new String[4];
        values[0] = this.getID();
        values[1] = Integer.toString(this.getRoom_num());
        values[2] = this.getDescription();
        values[3] = Integer.toString(this.getTemperature());
        return values;
    }
    
}
