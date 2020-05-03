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
public class Item extends EntityTemplate implements Entity{
    private String name;
    private String id;
    private Integer temp;
    private String vendor;
    private List<Location> locations;

    public Item(String name, String id, int required_tmp, String vendor) {
        this.name = name;
        this.id = id;
        this.temp = required_tmp;
        this.vendor = vendor;
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
        String[] names = {"name", "temp", "producer"};
        return names;
    }

    @Override
    public String[] getValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
