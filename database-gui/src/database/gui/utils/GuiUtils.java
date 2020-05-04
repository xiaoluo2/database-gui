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
public class GuiUtils {
    
    public static String searchToQuery(String tablename, String column, String info){
        String a = "SELECT * FROM "+tablename+" WHERE "+column+" = "+info;
        return a;
    }
    
}
