package com.orig.gls.dao.reports;

import com.orig.common.methods.CommonMethods;
import com.orig.gls.conn.AdminDb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;

public class BeanFactory {

    // Populate re-instatement, addition and exit of members.
    //select foracid,clr_bal_amt,  cust_id,ACCT_NAME,SUB_GROUP_CODE,LAST_MODIFIED_DATE,LCHG_USER_ID,MEMBER_STATUS from GENERAL_ACCT_MAST_AUDIT_TABLE
    public static Collection getCustomerReport(String subGroupCode, String fromDate, String toDate, String Status) {
        String sql = "select foracid,clr_bal_amt,cust_id,ACCT_NAME,SUB_GROUP_CODE,LAST_MODIFIED_DATE,LCHG_USER_ID,MEMBER_STATUS,"
                + " sol_id from general_acct_mast_audit_table where sub_group_code = ? "
                + "and last_modified_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111)  and member_status = ?";
        String in = subGroupCode + "," + fromDate + "," + toDate + "," + Status;
        ArrayList col = AdminDb.execArrayLists(sql, 4, in, 9);
        System.out.println(col);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            String exitReason = "";
            String memberStatus = (String) one.get(7);
            if (memberStatus.equalsIgnoreCase("V")) {
                exitReason = "Voluntary";
            } else if (memberStatus.equalsIgnoreCase("E")) {
                exitReason = "Expelled";
            } else if (memberStatus.equalsIgnoreCase("D")) {
                exitReason = "Deceased";
            }
            String mainGrp = CommonMethods.getGrpCode(subGroupCode);

            String clearBal = (String) one.get(1);
            String forfeitAmt = (String) one.get(1);
            String unclaimedAmt = (String) one.get(1);
            String loanBal = (String) one.get(1);
            String custId = (String) one.get(2);
            String loanAcc = CommonMethods.getLoanAcc(custId);

            collection.add(new Registration((String) one.get(2), (String) one.get(1),
                    (String) one.get(3), mainGrp, subGroupCode,
                    (String) one.get(5), exitReason, (String) one.get(7), loanAcc, loanBal, forfeitAmt, unclaimedAmt, clearBal));
        }
        
        return collection;
    }

//    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date f_Date = sdf.parse("2017-01-01");
//            Date t_Date = new Date();
//            String toDate = sdf.format(t_Date);
//            System.out.println(getCustomerReport("WES2010", "2017-01-01", toDate, "A"));
//        } catch (ParseException e) {
//        }
//    }

    // Populate repayment report.
    public static Collection getRepaymentReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select tran_amt, tran_date, foracid, rcre_user_id, lchg_user_id from history_transactions_table where sub_group_code = ? and tran_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
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
    public static Collection getDisbursementReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select acct_mgr_user_id, dis_amt ,dis_shdl_date from loan_disbursement_report where sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 3);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Disbursement((String) one.get(0), (String) one.get(1), (String) one.get(2), subGroupCode));
        }
        return collection;
    }

    // Populate disbursement report.
    public static Collection getDemandsReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select foracid, acct_name, dmd_amt, dmd_date from loan_demands_report where sub_group_code = ? and dmd_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
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
