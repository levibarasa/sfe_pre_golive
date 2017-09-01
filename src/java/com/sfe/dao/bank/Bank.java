package com.orig.gls.dao.bank;

import com.orig.gls.conn.AdminDb;

public class Bank {

    public static boolean bankEnityExists() {
        String sql = "select count(*)cnt from sol_group_control_table";
        String str = AdminDb.getValue(sql, 1, 0, "");
        return Integer.parseInt(str) > 0;
    }

    public static String getBankId() {
        String sql = "select bank_id from sol_group_control_table";
        return AdminDb.getValue(sql, 1, 0, "");
    }
    public static String getContryCode(String bankId) {
        String sql = "select home_cntry_code from sol_group_control_table where BANK_ID = ?";
        return AdminDb.getValue(sql, 1, 1, bankId);
    }
}
