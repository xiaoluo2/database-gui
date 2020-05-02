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
public class Item {
    private String name;
    private long item_id;
    private Integer required_tmp;
    private String source;
    private String description;
    private double price;

    public Item(String name, long item_id, Integer required_tmp, String source, String description, double price) {
        this.name = name;
        this.item_id = item_id;
        this.required_tmp = required_tmp;
        this.source = source;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public Integer getRequired_tmp() {
        return required_tmp;
    }

    public void setRequired_tmp(Integer required_tmp) {
        this.required_tmp = required_tmp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
