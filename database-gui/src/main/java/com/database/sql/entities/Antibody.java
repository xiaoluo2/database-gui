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
public class Antibody extends Item {
    private String host;
    private String catalog_no;
    
    public Antibody(String name, long item_id, Integer required_tmp, String source, String description, double price, String host, String catalog_no) {
        super(name, item_id, required_tmp, source, description, price);
        this.host = host;
        this.catalog_no = catalog_no;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCatalog_no() {
        return catalog_no;
    }

    public void setCatalog_no(String catalog_no) {
        this.catalog_no = catalog_no;
    }
}
