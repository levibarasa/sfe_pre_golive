package com.sfe.dao.report;

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
