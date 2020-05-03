/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database.sql.entity;

import java.sql.Date;

/**
 *
 * @author Xiao Luo
 */
public class Order extends EntityTemplate implements Entity {
    private String order_id;
    private String status;
    private Date date;
    private String vendor;
    private Lab_Member requester;

    public Order(String order_id, String status, Date date, String vendor) {
        this.order_id = order_id;
        this.status = status;
        this.date = date;
        this.vendor = vendor;
    }

    @Override
    public String getID() {
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
    
    public Lab_Member getRequester() {
        return requester;
    }

    public void setRequester(Lab_Member requester) {
        this.requester = requester;
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
