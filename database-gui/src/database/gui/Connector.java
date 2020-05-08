package database.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.*;

public class Connector {
   // database URL
   public static final String DB_URL = "jdbc:mysql://triton.towson.edu:3360/shomic2db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
   //  Database credentials
   public static final String USER = "shomic2";
   public static final String PASS = "Cosc*w2k3";
   
   public Connection getConnection(String username, String password){
       Connection conn = null;
       try{
         conn = DriverManager.getConnection(DB_URL,username,password);
       } catch(SQLException se){
         se.printStackTrace();
      }
      return conn;
   }
   
   public static Connection getConnection(){
       Connection conn = null;
       try{
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
       } catch(SQLException se){
         se.printStackTrace();
      }
      return conn;
   }
   
   public static RowSet executeRowSet(String sql){
       RowSet rs = null;
       try {
           rs = RowSetProvider.newFactory().createCachedRowSet();
           rs.setUrl(Connector.DB_URL);
           rs.setUsername(Connector.USER);
           rs.setPassword(Connector.PASS);
           rs.setCommand(sql);
           rs.execute();
           
       } catch (SQLException ex) {
           Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
       }
       return rs;
   }
   
}