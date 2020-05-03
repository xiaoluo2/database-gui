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
public class Antibody extends Item implements Entity{
    private String host;
    
    public Antibody(String name, String item_id, int required_tmp, String source, String host) {
        super(name, item_id, required_tmp, source);
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    // TODO
    @Override
    public String[] getColNames() {
       String[] names = {"name", "item_id", "required_tmp", "source", "host"};
       return names;
    }

    // TODO
    @Override
    public String[] getValues() {
        String[] values = new String[5];
        values[0] = this.getName();
        values[1] = this.getID();
        values[2] = Integer.toString(this.getTemp());
        values[3] = this.getVendor();
        values[4] = this.getHost();
        return values;
    }

}
