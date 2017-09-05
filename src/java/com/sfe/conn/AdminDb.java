package com.sfe.conn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

public class AdminDb {

    private static final Log log = LogFactory.getLog("origlogger");

    public static int dbWork(String sql, int params, String args) {
        DBConnection dbconn = new DBConnection();
        try {
            Connection conn = dbconn.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params > 0) {
                System.out.println("Input values " + args);
                String[] vals = args.split("\\s*,\\s*");
                int l = vals.length;
                System.out.println("No of elements in array " + l);
                int g = 0;
                while (g < params) {
                    ps.setString(g + 1, vals[g]);
                    g++;
                }
            }
            int n = ps.executeUpdate();
            if (n > 0) {
                conn.setAutoCommit(false);
                conn.commit();
                conn.setAutoCommit(true);
                return 1;
            }
            DBConnection.closeConn(conn);
        } catch (SQLException ex) {
            log.debug(ex);
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean findDuplicate(String sql, int params, String args) {
        DBConnection dbconn = new DBConnection();
        try {
            Connection conn = dbconn.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params > 0) {
                String[] vals = args.split("\\s*,\\s*");
                int g = 0;
                while (g < params) {
                    ps.setString(g + 1, vals[g]);
                    g++;
                }
            }
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                return true;
            }
            DBConnection.closeConn(conn);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            log.debug(ex);
            return false;
        }
        return false;
    }

    public static String getValue(String sql, int w, int params, String args) {
        String k = "";
        DBConnection dbconn = new DBConnection();
        try {
            Connection conn = dbconn.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params > 0) {
                String[] vals = args.split("\\s*,\\s*");
                int g = 0;
                while (g < params) {
                    ps.setString(g + 1, vals[g]);
                    g++;
                }
            }
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                if (w == 1) {
                    k = r.getString(1);
                } else {
                    String b = r.getString(1);
                    for (int q = 2; q <= w;) {
                        b = b + "," + r.getString(q);
                        q++;
                    }
                    k = b;
                }
            }
            DBConnection.closeConn(conn);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            log.debug(ex);
        }
        return k;
    }

    public static ArrayList execArrayLists(String sql, int params, String args, int outParams) {
        DBConnection dbconn = new DBConnection();
        ArrayList ar = new ArrayList();
        try {
            Connection conn = dbconn.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params > 0) {
                String[] vals = args.split("\\s*,\\s*");
                int g = 0;
                while (g < params) {
                    ps.setString(g + 1, vals[g]);
                    g++;
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList arr = new ArrayList();
                for (int w = 1; w <= outParams; w++) {
                    arr.add(rs.getString(w));
                }
                ar.add(arr);
            }
            DBConnection.closeConn(conn);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            log.debug(ex);
        }
        return ar;
    }

    public static List execAllList(String sql, int params, String args, int outParams) {
        DBConnection dbconn = new DBConnection();
        List ar = new ArrayList();
        try {
            Connection conn = dbconn.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            if (params > 0) {
                String[] vals = args.split("\\s*,\\s*");
                int g = 0;
                while (g < params) {
                    ps.setString(g + 1, vals[g]);
                    g++;
                }
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for (int w = 1; w <= outParams; w++) {
                    ar.add(rs.getString(w));
                }
            }
            DBConnection.closeConn(conn);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            log.debug(ex);
        }
        return ar;
    }

    private static String correctedDate(String date) {
        DateTime dateTime = ISODateTimeFormat.dateTimeParser().parseDateTime(date);
        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String da = sdf1.format(dateTime.toDate());
        return da;
    }
}
