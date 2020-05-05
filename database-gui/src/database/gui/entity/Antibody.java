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
public class Antibody extends Item implements Entity{
    private String host;
    
    public Antibody(String name, String item_id, int required_tmp, String source, String host) {
        super(name, item_id, required_tmp, source, "antibody");
        this.host = host;
    }
    
    public Antibody(){
        this(null, null, 9999, null, null);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

}
