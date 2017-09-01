
package com.orig.gls.conn;

import com.orig.gls.prop.GlsProp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

    Connection conn;
   GlsProp pr;

    public DBConnection() {
        pr = new GlsProp();
    }
// con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
    
       public Connection getDbConnection() {
        try {
            Class.forName(pr.getDBProperty().getProperty("database.driver")); 
   String url =pr.getDBProperty().getProperty("database.url"); 
   String userName =pr.getDBProperty().getProperty("database.user");
   String pass = pr.getDBProperty().getProperty("database.pass");
   conn = DriverManager.getConnection(url,userName,pass);
   
   //connection to oracle db
//            conn = DriverManager.getConnection(pr.getDBProperty().getProperty("database.url") + ":@" + pr.getDBProperty().getProperty("database.host")
//                    + ":" + pr.getDBProperty().getProperty("database.port") + ":" + pr.getDBProperty().getProperty("database.sid"),
//                    pr.getDBProperty().getProperty("database.user"), pr.getDBProperty().getProperty("database.pass"));

        } catch (ClassNotFoundException | SQLException asd) {
            System.err.println(asd.getMessage());
        }
        return conn;
    }
       public static void closeConn(Connection con)
       {
           if (con!=null)
           {
                
           }
       }
  //     public static void main(String[] args) {
//           DBConnection   bConnection = new DBConnection();
//           if (bConnection.getDbConnection()!=null)
//           {
//            System.out.println("Connected");    
//           }
//           else
//           {
//
//           System.out.println("Fuck you"); 
//           }
           
  //  }
}
