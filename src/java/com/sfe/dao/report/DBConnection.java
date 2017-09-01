package com.orig.gls.dao.report;

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

    public Connection getDbConnection() {
        try {
            Class.forName(pr.getDBProperty().getProperty("database.driver"));
            String url = pr.getDBProperty().getProperty("database.url");
            String userName = pr.getDBProperty().getProperty("database.user");
            String pass = pr.getDBProperty().getProperty("database.pass");
            conn = DriverManager.getConnection(url, userName, pass);

//            Class.forName(pr.getDBProperty().getProperty("database.driver"));
//            conn = DriverManager.getConnection(pr.getDBProperty().getProperty("database.url") + ":@" + pr.getDBProperty().getProperty("database.host")
//                    + ":" + pr.getDBProperty().getProperty("database.port") + ":" + pr.getDBProperty().getProperty("database.sid"),
//                    pr.getDBProperty().getProperty("database.user"), pr.getDBProperty().getProperty("database.pass"));
        } catch (ClassNotFoundException asd) {
            System.err.println(asd.getMessage());
        } catch (SQLException asd) {
            System.err.println(asd.getMessage());
        }
        return conn;
    }

    public static void closeConn(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (Exception d) {
            System.out.println(d.getMessage());
        }
    }
}
