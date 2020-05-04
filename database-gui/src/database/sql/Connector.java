package database.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
   // database URL
   public static final String DB_URL = "jdbc:mysql://triton.towson.edu:3360/shomic2db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

   //  Database credentials
   public static final String USER = "shomic2";
   public static final String PASS = "Cosc*w2k3";
   
   private static Connection connection;
   
   public static void setConnection(){
       Connection conn = null;
       try{
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
       } catch(SQLException se){
         se.printStackTrace();
      }
      connection = conn;
   }
   
   public static Connection getConnection(){
       return connection;
   }
   
}