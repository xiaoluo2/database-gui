/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.sql.entity;

/**
 *
 * @author Xiao Luo
 */
public class Strain extends Item implements Entity{

    private String anti_res;
    private String features;

    public Strain(String name, String id, String anti_res, String features) {
        super(name, id, 0, null);
        this.anti_res = anti_res;
        this.features = features;
    }
    
    public Strain(){
        this(null, null, null, null);
    }

    @Override
    public String[] getColNames() {
        String[] names = {"name", "id", "anti_res", "features"};
        return names;
    }

    @Override
    public String[] getValues() {
        String[] values = new String[4];
        values[0] = this.getName();
        values[1] = this.getID();
        values[2] = this.getAnti_res();
        values[3] = this.getFeatures();
        return values;
    }

    public String getAnti_res() {
        return anti_res;
    }

    public void setAnti_res(String anti_res) {
        this.anti_res = anti_res;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
    
}
