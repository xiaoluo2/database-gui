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
public class Plasmid extends Item implements Entity {
    private String feature;
    private String creator_id;
    private String creator_name;

    public Plasmid(String name, String item_id, String creator_id, String creator_name, String feature) {
        super(name, item_id, 0, creator_id, "plasmid");
        this.feature = feature;
        this.creator_id = creator_id;
        this.creator_name = creator_name;
    }
    
    public Plasmid(){
        this(null, null, null, null, null);
    }
    
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

}
