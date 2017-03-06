package com.orig.gls.dao.tran;

import com.orig.gls.conn.AdminDb;
import com.orig.gls.prop.GlsProp;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Transact {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

    public static ArrayList getAllAccountsMappedtoSubGroup(String subgroupCode) {
        String sql = "select foracid, acct_name, from general_acct_mast_table where sub_group_code = ? and schm_code <>?";
        GlsProp pr = new GlsProp();
        String schm = pr.getDBProperty().getProperty("wholesale.schmecode");
        String in = subgroupCode + "," + schm;
        return AdminDb.execArrayLists(sql, 2, in, 2);
    }

    public static int addTranDetails(BigDecimal tranAmt, Date tranDate, String acid, String tranParticulars, String rcreUserId, Date rcreTime, String lchgUserId, Date lchgTime, String delFlg, String pstdFlg, String tranType, String bankTranId, String subGroupCode) {
        String sql = " insert into DAILY_TRANSACTIONS_TABLE(TRAN_AMT,TRAN_DATE, FORACID ,TRAN_ID,TRAN_PARTICULARS, RCRE_USER_ID, RCRE_TIME,LCHG_USER_ID, LCHG_TIME, DEL_FLG,PSTD_FLG,TRAN_TYPE , BANK_TRAN_ID, SUB_GROUP_CODE) values (?,to_date(?,'dd/MM/yyyy'),?,?,?,?,?,?,?,?,?,?,?)";
        String in = tranAmt + "," + tranDate + "," + acid + ",GLS_SEQ.nextval," + tranParticulars + "," + rcreUserId + "," + rcreTime + "," + lchgUserId + "," + lchgTime + "," + delFlg + "," + pstdFlg + "," + tranType + "," + bankTranId + "," + subGroupCode;
        return AdminDb.dbWork(sql, 13, in);
    }

    public static void completeTran(String foracid) {
        String sql = "delete from loan_demands_table where acid = ?";
        AdminDb.dbWork(sql, 1, foracid);
    }

    public static void completeTranPosting(String foracid) {
        String sql = "delete from daily_trasnctions_table where bank_tran_id = ?";
        AdminDb.dbWork(sql, 1, foracid);
    }

    public static int findTranDetails(String subgroupCode) {
        String sql = "select TRAN_AMT,TRAN_DATE, FORACID ,TRAN_ID,TRAN_PARTICULARS, RCRE_USER_ID, RCRE_TIME,LCHG_USER_ID, LCHG_TIME, DEL_FLG,PSTD_FLG,TRAN_TYPE , BANK_TRAN_ID, SUB_GROUP_CODE from daily_transaction_table "
                + "where acid = ? and tran_type = ?";
        String in = subgroupCode + ",C";
        String val = AdminDb.getValue(sql, 14, 2, in);
        String[] vals = val.split("\\s*,\\s*");
        String ins = "";
        for (int w = 0; w < 13; w++) {
            ins = ins + vals[w] + ",";
        }
        ins = ins + vals[13];
        String sq = " insert into HISTORY_TRANSACTIONS_TABLE(TRAN_AMT,TRAN_DATE, FORACID ,TRAN_ID,TRAN_PARTICULARS, RCRE_USER_ID, RCRE_TIME,LCHG_USER_ID, LCHG_TIME, DEL_FLG,PSTD_FLG,TRAN_TYPE , BANK_TRAN_ID, SUB_GROUP_CODE) values (?,to_date(?,'dd/MM/yyyy'),?,?,?,?,?,?,?,?,?,?,?,?)";
        return AdminDb.dbWork(sq, 14, ins);
    }

    public static String getAcid(String foracid) {
        String sql = "select acid from general_acct_mast_table where foracid ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
    }

    public static int getLastInsertId() {
        String sql = "select max(tran_id) from daily_transactions_table";
        String r = AdminDb.getValue(sql, 1, 0, "");
        return Integer.parseInt(r) + 1;
    }

    public static void deleteAfterVerification(String foracid) {
        completeTran(foracid);
    }

    public static ArrayList verifyTransacctions(String subgroupCode) {
        String sql = "select bank_tran_id, tran_amt, tran_type, acid, sub_group_code from daily_transactions_table where sub_group_code = ? and tran_type = ?";
        String in = subgroupCode + ",C";
        return AdminDb.execArrayLists(sql, 2, in, 5);
    }

    public static void postTransacctions(String subgroupCode) {
        String sql = "select acid, tran_amt, tran_particulars, bank_tran_id from daily_transactions_table where sub_group_code = ? and tran_type = ?";
        String in = subgroupCode + ",C";
        String val = AdminDb.getValue(sql, 3, 2, in);
        String[] vals = val.split("\\s*,\\s*");
        String fromAccount;
        String toAccount;
        fromAccount = getDebitAccountNumber(subgroupCode);
        toAccount = vals[0];
        System.out.println("Debit Account " + fromAccount);
        System.out.println("Credit Account " + toAccount);
        BigDecimal amts = new BigDecimal(vals[1]);
        BigDecimal bg = amts.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
        int dbl = bg.intValue();
        String amt = String.valueOf(dbl);
        ProcessTran pst = new ProcessTran();
        String resp = pst.postTran(fromAccount, toAccount, amt, vals[2]);
        System.out.println("Response from Finacle " + resp);
        if (resp.equalsIgnoreCase("000")) {
            if (findTranDetails(toAccount) > 0) {
                completeTranPosting(vals[3]);
            }
        }
    }

    public static String getDebitAccountNumber(String foracid) {
        String sql = "select sub_grp_acnt_no from sub_grp_table where sub_group_code = ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
    }

    public static String getlinkedOperAcnt(String foracid) {
        String sql = "select foracid from general_acct_mast_table where foracid = ? and schm_code = ?";
        String in = foracid + ",SBGLS";
        return AdminDb.getValue(sql, 1, 2, in);
    }
}
