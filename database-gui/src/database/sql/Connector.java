package database.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
   // JDBC driver name and database URL
   public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   public static final String DB_URL = "jdbc:mysql://triton.towson.edu/shomic2db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

   //  Database credentials
   public static final String USER = "shomic2";
   public static final String PASS = "Cosc*w2k3";
   
   public static Connection getConnection(){
       Connection conn = null;
       try{
         conn = DriverManager.getConnection(DB_URL,USER,PASS);
       } catch(SQLException se){
         se.printStackTrace();
      }
      return conn;
   }
   
}