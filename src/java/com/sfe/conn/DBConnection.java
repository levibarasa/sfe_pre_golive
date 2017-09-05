package com.sfe.conn;

import com.sfe.prop.SFEProp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    Connection conn;
    SFEProp pr;

    public DBConnection() {
        pr = new SFEProp();
    }

    public Connection getDbConnection() {
        try {
            Class.forName(pr.getDBProperty().getProperty("database.driver"));
            String url = pr.getDBProperty().getProperty("database.url");
            String userName = pr.getDBProperty().getProperty("database.user");
            String pass = pr.getDBProperty().getProperty("database.pass");
            conn = DriverManager.getConnection(url, userName, pass);
        } catch (ClassNotFoundException | SQLException asd) {
            System.err.println(asd.getMessage());
        }
        return conn;
    }

    public static void closeConn(Connection con) {
        if (con != null) {

        }
    }
//      public static void main(String[] args) {
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
//           
//    }
}
