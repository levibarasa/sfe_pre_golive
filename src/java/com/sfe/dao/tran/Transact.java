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
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static ArrayList getAllAccountsMappedtoSubGroup(String subgroupCode) {
        String sql = "select foracid, acct_name, from general_acct_mast_table where sub_group_code = ? and schm_code <>?";
        GlsProp pr = new GlsProp();
        String schm = pr.getDBProperty().getProperty("wholesale.schmecode");
        String in = subgroupCode + "," + schm;
        return AdminDb.execArrayLists(sql, 2, in, 2);
    }
//    public static void main(String[] args) {
//        System.out.println(addTranDetails(new BigDecimal(1),  new Date() , "001SIN0001000124", "GLS LOAN REPAYMENT", "MARK",  new Date() , "MARK",  new Date(), "N", "N", "D", "100100", "WES2010"));
//        
//    }
//    

    public static int addTranDetails(BigDecimal tranAmt, Date tranDate, String acid, String tranParticulars, String rcreUserId, Date rcreTime, String lchgUserId, Date lchgTime, String delFlg, String pstdFlg, String tranType, String bankTranId, String subGroupCode) {

        String sql = " insert into DAILY_TRANSACTIONS_TABLE"
                + "(FORACID,BANK_TRAN_ID,DEL_FLG,LCHG_TIME,LCHG_USER_ID,PSTD_FLG,RCRE_TIME,RCRE_USER_ID,SUB_GROUP_CODE,TRAN_AMT,TRAN_DATE ,TRAN_PARTICULARS,TRAN_TYPE) "
                + " values (?,?,?,try_convert(date, ?, 111),?,?,try_convert(date, ?, 111),?,?,?,try_convert(date, ?, 111),?,?)";
        String rcreTime_ = sdf.format(new Date());
        String tranDate_ = sdf.format(new Date());
        String lchgTime_ = sdf.format(new Date());
        String in = acid + "," + bankTranId + "," + delFlg + "," + lchgTime_ + "," + lchgUserId + "," + pstdFlg + ","
                + rcreTime_ + "," + rcreUserId + "," + subGroupCode + "," + new BigDecimal("" + tranAmt + "") + ","
                + tranDate_ + "," + tranParticulars + "," + tranType;
        return AdminDb.dbWork(sql, 13, in);
    }

    public static void completeTran(String foracid) {
        String sql = "delete from loan_demands_table where acid = ?";
        AdminDb.dbWork(sql, 1, foracid);
    }

    public static void completeTranPosting(String foracid) {
        String sql = "delete from DAILY_TRANSACTIONS_TABLE where bank_tran_id = ?";
        AdminDb.dbWork(sql, 1, foracid);
    }

    public static int findTranDetails(String subgroupCode) {
        String sql = "select TRAN_AMT,TRAN_DATE, FORACID ,TRAN_PARTICULARS, RCRE_USER_ID,"
                + " RCRE_TIME,LCHG_USER_ID, LCHG_TIME, DEL_FLG,PSTD_FLG,TRAN_TYPE , BANK_TRAN_ID,"
                + " SUB_GROUP_CODE from DAILY_TRANSACTIONS_TABLE "
                + "where foracid = ? and tran_type = ?";
        String in = subgroupCode + ",C";
        String val = AdminDb.getValue(sql, 13, 2, in);
        String[] vals = val.split("\\s*,\\s*");
        String ins = "";
        for (int w = 0; w < 12; w++) {
            ins = ins + vals[w] + ",";
        }
        ins = ins + vals[12];
        String sq = " insert into HISTORY_TRANSACTIONS_TABLE(TRAN_AMT,TRAN_DATE, FORACID ,"
                + "TRAN_PARTICULARS, RCRE_USER_ID, RCRE_TIME,LCHG_USER_ID, LCHG_TIME, DEL_FLG,"
                + "PSTD_FLG,TRAN_TYPE , BANK_TRAN_ID, SUB_GROUP_CODE) values (?,try_convert(date, ?, 111)"
                + "  ,?,?,?,try_convert(date, ?, 111),?,try_convert(date, ?, 111),?,?,?,?,?)";
        return AdminDb.dbWork(sq, 13, ins);
    }

    public static String getAcid(String foracid) {
        String sql = "select acid from general_acct_mast_table where foracid= ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
    }

    public static int getLastInsertId() {
        String sql = "select max(tran_id) from DAILY_TRANSACTIONS_TABLE";
        String r = AdminDb.getValue(sql, 1, 0, "");
        return Integer.parseInt(r) + 1;
    }
     
    public static void deleteAfterVerification(String foracid) {
        completeTran(foracid);
    }

    public static ArrayList verifyTransacctions(String subgroupCode) {
        String sql = "select bank_tran_id, tran_amt, tran_type, foracid, sub_group_code from DAILY_TRANSACTIONS_TABLE where sub_group_code = ? and tran_type = ?";
        String in = subgroupCode + ",C";
        return AdminDb.execArrayLists(sql, 2, in, 5);
    }

    public static ArrayList getPostDetails(String subgroupCode) {
        String sql = "select foracid, tran_amt, tran_particulars, bank_tran_id from DAILY_TRANSACTIONS_TABLE where sub_group_code = ? and tran_type = ?";
        String in = subgroupCode + ",C";
        return AdminDb.execArrayLists(sql, 2, in, 4);
    }

//    public static void main(String[] args) {
//        System.out.println();
//        ArrayList ar =getPostDetails("WES2010");
//        for (int i = 0; i < ar.size(); i++) {
//            ArrayList one = (ArrayList) ar.get(i);
//            String foracid = (String) one.get(0);
//            String tranAmnt = (String) one.get(1);
//            String tranParticular = (String) one.get(2);
//            String tranId = (String) one.get(3);
//            
//            System.out.println(foracid+" |"+tranAmnt+" | "+tranParticular+" |"+tranId);  
//
//        }
//    }
    public static boolean postTransacctions(String subgroupCode) {
        boolean response = false;
        String fromAccount, toAccount;
        fromAccount = getDebitAccountNumber(subgroupCode);
        ArrayList ar = getPostDetails(subgroupCode);
        for (int i = 0; i < ar.size(); i++) {
            ArrayList one = (ArrayList) ar.get(0);

            toAccount = (String) one.get(0);
            System.out.println("Debit Account " + fromAccount);
            System.out.println("Credit Account " + toAccount);
            BigDecimal amts = new BigDecimal("" + one.get(1) + "");
            BigDecimal bg = amts.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
            int dbl = bg.intValue();
            String amt = String.valueOf(dbl);

            ProcessTran pst = new ProcessTran();
            String resp = pst.postTran(fromAccount, toAccount, amt, (String) one.get(2));
            System.out.println("Response from Finacle " + resp);
            if (resp.equalsIgnoreCase("000")) {
                if (findTranDetails(toAccount) > 0) {
                    completeTranPosting((String) one.get(3));
                }
                response = true;
            }
        }
        return response;
    }

    //    public static void main(String[] args) {
//        ArrayList ar =verifyTransacctions("WES2010");
//        for (int i = 0; i < ar.size(); i++) {
//                        ArrayList one = (ArrayList) ar.get(i);
//                        System.out.println((String) one.get(0)+" "+ one.get(1)+" "+(String) one.get(2)+" "+(String) one.get(3)+" "+(String) one.get(4));
//        }
//    }
    public static String getDebitAccountNumber(String foracid) {
        String sql = "select sub_grp_acnt_no from sub_grp_table where sub_group_code = ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
    }

    public static String getlinkedOperAcnt(String foracid) {
        String sql = "select foracid from general_acct_mast_table where foracid = ? and schm_code = ?";
        String in = foracid + ",SBGLS";
        return AdminDb.getValue(sql, 1, 2, in);
    }
// log a transaction 
    public static void createTransactionHistory(BigDecimal tranAmt, String foracid, String tranParticulars, String rcreUserId, String lchgUserId,  String delFlg, String pstdFlg, String tranType, String bankTranId, String subGroupCode) {
        String sql = " insert into HISTORY_TRANSACTIONS_TABLE(TRAN_AMT,TRAN_DATE, FORACID ,"
                + "TRAN_PARTICULARS, RCRE_USER_ID, RCRE_TIME,LCHG_USER_ID, LCHG_TIME, DEL_FLG,"
                + "PSTD_FLG,TRAN_TYPE , BANK_TRAN_ID, SUB_GROUP_CODE) values (?,try_convert(date, ?, 111)"
                + "  ,?,?,?,try_convert(date, ?, 111),?,try_convert(date, ?, 111),?,?,?,?,?)";
        String rcreTime_ = sdf.format(new Date());
        String tranDate_ = sdf.format(new Date());
        String lchgTime = sdf.format(new Date());
        String ins = new BigDecimal("" + tranAmt + "") + "," + tranDate_ + "," + foracid + "," + tranParticulars + "," + rcreUserId + "," + rcreTime_ + "," + lchgUserId + "," + lchgTime + "," + delFlg + "," + pstdFlg + "," +  tranType +"," + bankTranId + "," + subGroupCode;
      AdminDb.dbWork(sql, 13, ins); 
    }
//    public static void main(String[] args) {
    
//        int t =createTransactionHistory(  new BigDecimal(10000), "001SIN0001000124",   "GLS LOAN REPAYMENT", "MARK","MARK",  "N", "N", "C", "PBUG1014", "WES2010");
//        System.out.println(t);
//    } 
}
