package com.sfe.dao.reports;

import com.sfe.common.methods.CommonMethods;
import com.sfe.conn.AdminDb;
import java.util.Collection;
import java.util.ArrayList;

public class BeanFactory {
    // Populate officer monitoring  report 

    public static Collection getOfficerMonitoringReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select distinct sol_id,ACCT_MGR_USER_ID,DIS_AMT,no_of_cases_disb from OFFICER_MONITORING_REPORT where sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 4);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new OfficerMonitoring((String) one.get(0), (String) one.get(1), "", "", "", "", "", "", (String) one.get(2), (String) one.get(3), fromDate, toDate));
        }
        return collection;
    }

    // Populate loan Saving  report 
    public static Collection getLoanSavingsReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select distinct sol_id,ACCT_MGR_USER_ID,DIS_AMT,CLR_BAL_AMT from LOAN_SAVING_PORTFOLIO_REPORT where sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 4);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new LoanSavingsPortfolio((String) one.get(0), (String) one.get(1), (String) one.get(2), "", (String) one.get(3), fromDate, toDate));
        }
        return collection;
    }

    // Populate   Retentionrate Report
    public static Collection getRetentionReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select distinct ACCT_MGR_USER_ID,active_members,rein,exits,reten_rate from RETENTION_RATE_REPORT where sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 5);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new RetentionRate((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), fromDate, toDate));
        }
        return collection;
    }

    // Populate re-instatement, addition and exit of members.
    public static Collection getCustomerReport(String subGroupCode, String fromDate, String toDate, String Status) {
        String sql = "select acct_name, clr_bal_amt, cust_id, foracid, schm_code, sol_id from general_acct_mast_audit_table where sub_group_code = ? "
                + "and last_modified_date between try_convert(date, ?, 111)   and try_convert(date, ?, 111)   and member_status = ?";
        String in = subGroupCode + "," + fromDate + "," + toDate + "," + Status;
        ArrayList col = AdminDb.execArrayLists(sql, 4, in, 6);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Registration((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), (String) one.get(5), subGroupCode, fromDate, toDate));
        }
        return collection;
    }

    // Populate written off report.
    public static Collection getWriteOffReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select distinct foracid,acct_name,schm_code,dis_amt,dis_shdl_date,chrge_off_principal,pending_interest,total,loan_due_days,chrge_off_date from LOAN_CHARGE_OFF_REPORT where sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 10);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new ChargeOff((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), (String) one.get(5), (String) one.get(6), (String) one.get(7), (String) one.get(8), (String) one.get(9), fromDate, toDate));
        }
        return collection;
    }

    // Populate repayment report.
    public static Collection getRepaymentReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select tran_amt, tran_date, foracid, rcre_user_id, lchg_user_id from history_transactions_table where sub_group_code = ? and tran_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 5);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Repayment((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), subGroupCode, fromDate, toDate));
        }
        return collection;
    }
    // Populate group loan status report.

    public static Collection getGroupLoanStatusReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select  FORACID,ACCT_NAME,sub_group_phone,DIS_AMT,DIS_SHDL_DATE,branch_name,SUB_GROUP_CODE,CUST_ID from GROUP_USER_REPORT where GROUP_CODE =? and DIS_SHDL_DATE  between try_convert(date, ?, 111)  and try_convert(date, ?, 111)";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 8);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            double saving = CommonMethods.getMemberSavings((String) one.get(7));
            if (saving < 0) {
                saving = (-1 * saving);
            }
            String savings = String.valueOf(saving);
            collection.add(new GroupLoanStatus((String) one.get(0), (String) one.get(1), (String) one.get(2),
                    (String) one.get(0), savings, (String) one.get(3),
                    "", "", (String) one.get(4), "", "", "", "", "", "", "", "", "", "", "", (String) one.get(5), (String) one.get(6), fromDate, toDate));
        }
        return collection;
    }

// Populate new member register report.
    public static Collection getNewMemberReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select DISTINCT ACCT_MGR_USER_ID,group_id,SCHM_CODE  from GROUP_USER_REPORT where sub_group_code =? and formation_date  between try_convert(date, ?, 111)  and try_convert(date, ?, 111)";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 3);
        ArrayList collection = new ArrayList();
        int no_Of_Members = col.size();
        String colSiz = String.valueOf(no_Of_Members);
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            CommonMethods.getAccMgrs((String) one.get(0));
            collection.add(new NewMember((String) one.get(0), (String) one.get(1), (String) one.get(2), colSiz, fromDate, toDate));
        }
        return collection;
    }
    // Populate Compulsary Saving Withdrawal report

    public static Collection getCompulsarySavingWithdrawalReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select  foracid,acct_name,credit_rating,DIS_AMT,cast(clr_bal_amt as decimal(15,2))  from LOAN_DISBURSEMENT_REPORT where schm_code like 'SBGCO' and sub_group_code =? and dis_shdl_date  between try_convert(date, ?, 111)  and try_convert(date, ?, 111)";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 5);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            double projSavWith = (0.8 * Double.parseDouble((String) one.get(4)));
            String prjSav = String.valueOf(projSavWith);
            collection.add(new CompulsarySavingWithdrawal((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), prjSav, fromDate, toDate));
        }
        return collection;
    }

    // Populate projection report
    public static Collection getProjectionReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select CUST_ID,ACCT_NAME,DIS_AMT,group_id,group_name,DIS_SHDL_DATE FROM GROUP_USER_REPORT where  sub_group_code = ? and DIS_SHDL_DATE  between try_convert(date, ?, 111)  and try_convert(date, ?, 111)";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 6);
        ArrayList collection = new ArrayList();

        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            double otherSaving = CommonMethods.getMemberOtherSavings((String) one.get(0));
            double loan = CommonMethods.getClearBalance((String) one.get(0));
            double saving = CommonMethods.getMemberSavings((String) one.get(0));
            if (saving < 0) {
                saving = (-1 * saving);
            }
            if (otherSaving < 0) {
                otherSaving = (-1 * otherSaving);
            }
            if (loan < 0) {
                loan = (-1 * loan);
            }
            double col_fnd = 1.2 * loan;
            String collateral_fund = String.valueOf(col_fnd);
            String savings = String.valueOf(saving);
            String otherSavings = String.valueOf(otherSaving);
            collection.add(new Projection((String) one.get(0), (String) one.get(1), collateral_fund, (String) one.get(2), savings,
                    (String) one.get(5), otherSavings, (String) one.get(3), (String) one.get(4), (String) one.get(5), fromDate, toDate));
        }
        return collection;
    }

    // Populate disbursement report.
    public static Collection getDisbursementReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select foracid,acct_name,acct_mgr_user_id, dis_amt ,dis_shdl_date from loan_disbursement_report where sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 5);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Disbursement((String) one.get(0), (String) one.get(1), "", "",
                    (String) one.get(3), (String) one.get(4), "", "", (String) one.get(2), "", (String) one.get(4), fromDate, toDate));
        }
        return collection;
    }

    // Populate Demands report.
    public static Collection getDemandsReport(String subGroupCode, String fromDate, String toDate) {
        String sql = "select foracid, acct_name, dmd_amt, dmd_date from loan_demands_report where sub_group_code = ? and dmd_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subGroupCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 4);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new Demands((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), subGroupCode, fromDate, toDate));
        }
        return collection;
    }
}
