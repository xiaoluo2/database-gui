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
public class Chemical extends Item implements Entity{
    private String amount;

    public Chemical(String name, long item_id, Integer required_tmp, String source, String description, double price, String amount) {
        super(name, item_id, required_tmp, source, description, price);
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    // TODO
    @Override
    public String[] getFieldNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO
    @Override
    public String[] getValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
