/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.entity;

import java.util.List;

/**
 *
 * @author Xiao Luo
 */
public class Item extends EntityTemplate implements Entity{
    private String name;
    private String id;
    private Integer temp;
    private String vendor;
    private String type;
    private int[] locations;

    public Item(String name, String id, int required_tmp, String vendor, String type) {
        this.name = name;
        this.id = id;
        this.temp = required_tmp;
        this.vendor = vendor;
        this.type = type;
    }
    
    public Item(){
        this(null, null, 9999, null, null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getID() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public String[] getColNames() {
        String[] names = {"name", "item_id","temp", "producer"};
        return names;
    }

    @Override
    public String[] getValues() {
        String[] values = new String[4];
        values[0] = this.getName();
        values[1] = this.getID();
        values[2] = Integer.toString(this.getTemp());
        values[3] = this.getVendor();
        return values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
