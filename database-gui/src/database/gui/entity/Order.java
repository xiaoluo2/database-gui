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
public class Order extends EntityTemplate implements Entity {
    private String order_id;
    private String status;
    private String date;
    private int requester;
//    private List<String> items;

    public Order(String order_id, String status, String date, int requestor) {
        this.order_id = order_id;
        this.status = status;
        this.date = date;
        this.requester = requestor;
    }
    
    public Order(){
        this(null, null, null, 0);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public int getRequester() {
        return requester;
    }

    public void setRequester(int requester) {
        this.requester = requester;
    }
    
}
