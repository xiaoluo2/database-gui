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
public class Plasmid extends Item implements Entity {
    private String dna;
    private String antibioticRes;
    private String strain;

    public Plasmid(String dna, String antibioticRes, String strain, String name, long item_id, Integer required_tmp, String source, String description, double price) {
        super(name, item_id, required_tmp, source, description, price);
        this.dna = dna;
        this.antibioticRes = antibioticRes;
        this.strain = strain;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public String getAntibioticRes() {
        return antibioticRes;
    }

    public void setAntibioticRes(String antibioticRes) {
        this.antibioticRes = antibioticRes;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }
    
    // TODO
    @Override
    public String[] getFieldNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // TODO
    @Override
    public String[] getValues() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
