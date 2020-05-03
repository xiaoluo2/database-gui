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
    private Integer member_id;
    private String name;
    private String title;

    public Lab_Member(int member_id, String name, String title) {
        this.member_id = member_id;
        this.name = name;
        this.title = title;
    }

    public String getID() {
        return Integer.toString(member_id);
    }

    public void setMember_id(int member_id) {
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
        String[] names = {"name", "member_id", "title"};
        return names;
    }

    // TODO
    @Override
    public String[] getValues() {
        String[] values = new String[3];
        values[0] = this.getName();
        values[1] = this.getID();
        values[2] = this.getTitle();
        return values;
    }
    
}
