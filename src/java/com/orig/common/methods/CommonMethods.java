package com.orig.common.methods;

import com.orig.gls.conn.AdminDb;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class CommonMethods {

    private static final Log log = LogFactory.getLog("origlogger");

    public static ArrayList getMemberDetails(String acid) {
        String sql = "select acct_name, acid, bank_id, cust_id, acct_crncy_code, foracid, acct_ownership, del_flg, schm_code from general_acct_mast_table where acid = ?";
        return AdminDb.execArrayLists(sql, 1, acid, 9);
    }

    public static boolean getMemberLoans(String acid) {
        String sql = "select sum(clrBalAmt)AMT from general_acct_mast_table where cust_id = ?";
        String amt = AdminDb.getValue(sql, 1, 1, acid);
        double custId = Double.parseDouble(amt);
        return custId > 0.0;
    }

    public static boolean getsubGroupId(String groupCode, String groupName) {
        String sql = "select sub_group_id from sub_grp_table where sub_group_code = ? and group_id = ?";
        String in = groupCode + "," + getGroupCode(groupName);
        String str = AdminDb.getValue(sql, 1, 2, in);
        int groupid = Integer.parseInt(str);
        return groupid != 0;
    }

    private static int getGroupCode(String groupCode) {
        String sql = "select group_id from groups_table where group_code = ?";
        String str = AdminDb.getValue(sql, 1, 1, groupCode);
        return Integer.parseInt(str);
    }

    public static int numDaysOut(Date startDate, Date endDate) {
        DateTime dateTime = new DateTime(startDate.getTime());
        DateTime endTime = new DateTime(endDate.getTime());
        Days d = Days.daysBetween(dateTime, endTime);
        int days = d.getDays();
        return days;
    }
}
