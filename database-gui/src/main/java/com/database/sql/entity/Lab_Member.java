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
public class Lab_Member extends EntityTemplate implements Entity{
    private String member_id;
    private String name;
    private String title;

    public Lab_Member(String member_id, String name, String title) {
        this.member_id = member_id;
        this.name = name;
        this.title = title;
    }

    public String getID() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // TODO
    @Override
    public String[] getColNames() {
        String[] names = {"lab_id", "name", "title"};
        return names;
    }

    // TODO
    @Override
    public String[] getValues() {
        String[] values = new String[3];
        values[0] = this.getID();
        values[1] = this.getName();
        values[2] = this.getTitle();
        return values;
    }
    
}
