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
public class Order extends EntityTemplate implements Entity {
    private String order_id;
    private String status;
    private String date;
    private String vendor;
    private Lab_Member requester;

    public Order(String order_id, String status, String date, String vendor) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
        String[] names = {"Order_id", "Status", "Date", "Vendor"};
        return names;
    }

    // TODO
    @Override
    public String[] getValues() {
        String[] values = new String[4];
        values[0] = this.getID();
        values[1] = this.getStatus();
        values[2] = this.getDate();
        values[3] = this.getVendor();
        return values;
    }
    
}
