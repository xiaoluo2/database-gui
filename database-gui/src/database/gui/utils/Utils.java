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
        String sql;
        String[] s = searchText.split(":");
        if(s == null || s.length < 3) {
            return null;
        }
        String target = s[0].toLowerCase().trim();
        target = target.substring(0, 1).toUpperCase() + target.substring(1);
        String itemType = "";
        String tablename = "Item";
        switch(target){
            case "Antibody":
		itemType = "AND antibody=1";
                break;
            case "Chemical":
		itemType = "AND chemcial=1";
                break;
            case "Enzyme":
                itemType = " AND enyzme=1";
			break;
            case "Item":
                tablename = "item_location_view";
                break;
            case "Liquid":
                itemType = " AND liquid=1";
		break;
            case "Mole_bio":
                itemType = " AND mole_bio=1";
			break;
            case "Plasmid":
		itemType = "AND plasmid=1";
                break;
            case "React_probes":
                itemType = " AND react_probes=1";
			break;
            case "Strain":
		itemType = "AND strain=1";
                break;
            default:
                tablename = target;
                break;
        }
        String column = s[1].trim();
        String info = s[2].trim();
        sql = "SELECT * FROM `"+tablename+"` WHERE `"+column+"` = '"+info + "'" + itemType;
        return sql;
    }
    
}
