package com.sfe.dao.menu;

import com.sfe.conn.AdminDb;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Menu {

    private static final Log log = LogFactory.getLog("origlogger");

    public static boolean menuExists(String mopId) {
        String sql = "select count(*)CNT from resources where mop_id = ?";
        String r = AdminDb.getValue(sql, 1, 1, mopId);
        return Integer.parseInt(r) > 0;
    }

    public static boolean roleMenuIsUnverified(String roleName, String mopId) {
        String sql = "select count(*)cnt from res_mapping_mod where role_name = ? and mop_id = ?";
        String in = roleName + "," + mopId;
        String r = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(r) > 0;
    }

    public static boolean roleHasMenu(String roleName, String mopId) {
        String sql = "select count(*)cnt from res_mapping where role_name = ? and mop_id = ?";
        String in = roleName + "," + mopId;
        String r = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(r) > 0;
    }

    public static String mopUrl(String mopId) {
        String sql = "select mop_url from res_mapping where mop_id = ?";
        return AdminDb.getValue(sql, 1, 1, mopId);
    }
}
