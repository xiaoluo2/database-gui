/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql.entities;

import java.sql.Date;

/**
 *
 * @author Xiao Luo
 */
public class Order {
    private String order_id;
    private String status;
    private Date date;
    private String vendor;

    public Order(String order_id, String status, Date date, String vendor) {
        this.order_id = order_id;
        this.status = status;
        this.date = date;
        this.vendor = vendor;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
    
}
