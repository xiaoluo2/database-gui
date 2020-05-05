/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.gui.utils;

/**
 *
 * @author Xiao Luo
 */
public class Utils {
    
    public static String searchToQuery(String searchText){
        String sql, type = "";
        String[] s = searchText.split(":");
        if(s == null || s.length < 3) {
            return null;
        }
        String tablename = s[0].toLowerCase().trim();
        tablename = tablename.substring(0, 1).toUpperCase() + tablename.substring(1);
        switch(tablename){
            case "Antibody":
                tablename = "antibody_item_view";
                break;
            case "Chemical":
                tablename = "chemical_item_view";
                break;
            case "Enzyme":
                tablename = "Item";
                type = " AND enzyme=1";
                break;
            case "Item":
                tablename = "Item_Location_View";
                break;
            case "Liquid":
                tablename = "Item";
                type = " AND liquid=1";
                break;
            case "Mole_bio":
                tablename = "Item";
                type = " AND mole_bio=1";
                break;
            case "Plasmid":
                tablename = "plasmid_item_view";
                break;
            case "React_probes":
                tablename = "Item";
                type = " AND react_probes=1";
                break;
            case "Strain":
                tablename = "strain_item_view";
                break;
        }
        String column = s[1].trim();
        String info = s[2].trim();
        sql = "SELECT * FROM `"+tablename+"` WHERE `"+column+"` = '"+info + "'";
        return sql;
    }
    
}
