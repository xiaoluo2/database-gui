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
public class Chemical extends Item implements Entity{
    private String amount;

    public Chemical(String name, String item_id, int required_tmp, String vendor, String amount) {
        super(name, item_id, required_tmp, vendor);
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

    @Override
    public String[] getColNames() {
        String[] names = {"name", "item_id", "required_tmp", "vendor", "amount"};
        return names;
    }

    @Override
    public String[] getValues() {
        String[] values = new String[5];
        values[0] = this.getName();
        values[1] = this.getID();
        values[2] = Integer.toString(this.getTemp());
        values[3] = this.getVendor();
        values[4] = this.getAmount();
        return values;
    }
    
}
