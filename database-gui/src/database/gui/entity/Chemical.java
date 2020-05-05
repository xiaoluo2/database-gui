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
public class Chemical extends Item implements Entity{
    private String amount;

    public Chemical(String name, String item_id, int required_tmp, String vendor, String amount) {
        super(name, item_id, required_tmp, vendor, "chemical");
        this.amount = amount;
    }
    
    public Chemical(){
        this(null, null, 9999, null, null);
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    
}
