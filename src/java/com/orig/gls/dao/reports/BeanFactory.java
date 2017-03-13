package com.orig.gls.dao.reports;

import com.orig.gls.conn.AdminDb;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

public class BeanFactory {

    // Populate re-instatement, addition and exit of members.
    public static Collection getCustomerReport(String subGroupCode, Date fromDate, Date toDate, String Status) {
        String sql = "select acct_name, clr_bal_amt, cust_id, foracid, schm_code, sol_id from general_acct_mast_audit_table where sub_group_code = ? "
                + "and last_modified_date between TRY_CONVERT(?, 'dd/MM/yyyy', 102)  and TRY_CONVERT(?, 'dd/MM/yyyy', 102)  and member_status = ?";
        String in = subGroupCode + "," + fromDate + "," + toDate + "," + Status;
        ArrayList col = AdminDb.execArrayLists(sql, 4, in, 6);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Registration((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), (String) one.get(5), subGroupCode));
        }
        return collection;
    }

    // Populate repayment report.
    public static Collection getRepaymentReport(String subGroupCode, Date fromDate, Date toDate) {
        String sql = "select tran_amt, tran_date, acid, rcre_user_id, lchg_user_id from history_transactions_table where sub_group_code = ? and tran_date between TRY_CONVERT(?, 'dd/MM/yyyy', 102)  and TRY_CONVERT(?, 'dd/MM/yyyy', 102) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 5);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Repayment((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), subGroupCode));
        }
        return collection;
    }

    // Populate disbursement report.
    public static Collection getDisbursementReport(String subGroupCode, Date fromDate, Date toDate) {
        String sql = "select foracid, acct_name, dis_amt, dis_shdl_date from loan_disbursement_report where sub_group_code = ? and dis_shdl_date between TRY_CONVERT(?, 'dd/MM/yyyy', 102)  and TRY_CONVERT(?, 'dd/MM/yyyy', 102) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 4);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Disbursement((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), subGroupCode));
        }
        return collection;
    }

    // Populate disbursement report.
    public static Collection getDemandsReport(String subGroupCode, Date fromDate, Date toDate) {
        String sql = "select foracid, acct_name, dmd_amt, dmd_date from loan_demands_report where sub_group_code = ? and dmd_date between TRY_CONVERT(?, 'dd/MM/yyyy', 102)  and TRY_CONVERT(?, 'dd/MM/yyyy', 102) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 4);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Demands((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), subGroupCode));
        }
        return collection;
    }
}
