package com.orig.gls.dao.customer;
 
import com.orig.gls.conn.AdminDb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Customer {

    public static ArrayList getUnMappedAccounts() {
        String sql = "select cust_id, acct_name, sol_id from general_acct_mast_table where sub_group_code is null and mapped_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "N", 3);
    }

    // Get unmapped account details using cust no
    public static ArrayList getUnMappedAccountDetails(String foracid) {
        String sql = "select cust_id, acct_name, sol_id from general_acct_mast_table where sub_group_code is null and mapped_flg = ? and schm_code <>? and cust_id = ?";
         String in = "N,SBGRP," + foracid;
        return AdminDb.execArrayLists(sql, 3, in, 3);
    }

    // Repayment Account Number Mapping
    public static ArrayList getUnMappedRepayAcnt() {

        String sql = "select cust_id, acct_name, sol_id from general_acct_mast_table where mapped_flg = ? and schm_code = ?";
        String in = "N,SBGRP";
        return AdminDb.execArrayLists(sql, 2, in, 3);
    }

    public static void addGroupMember(int groupId, String username) {
        String s = "select no_of_members from groups_table where group_id=?";
        int k = 0;
        String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
        try {
            k = Integer.valueOf(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "update groups_table set no_of_members = ?, lchg_user_id =? where group_id=?";
        String in = k + 1 + "," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void removeGroupMember(int groupId, String username) {
        String s = "select no_of_members from groups_table where group_id=?";
        String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
        int k = 0;
        try {
            k = Integer.valueOf(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "update groups_table set no_of_members = ?, lchg_user_id =? where group_id=?";
        String in = k - 1 + "," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getUnMappedAccountDetailsMod(String foracid) {
        String sql = "select k.cust_id, k.acct_name, k.sol_id, k.sub_group_code,w.sub_group_name  from general_acct_mast_table_mod k, sub_grp_table w where cust_id = ? and w.sub_group_code=k.sub_group_code";
        return AdminDb.execArrayLists(sql, 1, foracid, 5);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllUnMappedAccountMod(String uname) {
        String sql = "select cust_id, foracid, acct_name, acct_opn_date, sol_id,schm_type,schm_code ,sub_group_code, last_oper  from general_acct_mast_table_mod  where rcre_user_id <>?";
        return AdminDb.execArrayLists(sql, 1, uname, 9);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllVerfiedAccounts() {
        String sql = "select cust_id, acct_name, sol_id, sub_group_code from general_acct_mast_table where mapped_flg = ? and member_status=? and sub_group_code is not null";
        String in = "Y,A";
        return AdminDb.execArrayLists(sql, 2, in, 4);
    }

    // get data to insert into mod table
    public static boolean getAccountDetails(String forr, String subGroupId, String lastOper, String creditRate, String uname) {
        String sql = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,LIEN_AMT,RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,RCRE_TIME,ACCT_OPN_DATE from general_acct_mast_table where cust_id = ?";
        String str = AdminDb.getValue(sql, 20, 1, forr);
        String[] args = str.split("\\s*,\\s*");
         String s = "insert into general_acct_mast_table_mod (ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,LIEN_AMT,RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,RCRE_TIME,ACCT_OPN_DATE,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID, SUB_GROUP_CODE,LAST_OPER,CREDIT_RATING) values"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111) ,try_convert(date, ?, 111),try_convert(date, getDate(), 111) ,try_convert(date, getDate(), 111),?,?,?,?)";
        String in = "";
        
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
         for (int w = 0; w <20 ; w++) {
            in = in + args[w] + ",";
        }
        
        String adds =  uname + "," + subGroupId + "," + lastOper + "," + creditRate;
        in = in + adds;
        int n = AdminDb.dbWork(s, 24, in);
        if (n > 0) {
            markAccountAsMappedinGam(forr, lastOper);
        }
        return n > 0;
    }
    public static void main(String[] args) {
        getAccountDetails("37024", "6", "A","1", "MIKE");
    }
    
    public static int getSolId(String bankId) {
        String sql = "select sol_id from  service_outlet_table where bank_id= ?";
        String r = AdminDb.getValue(sql, 1, 1, bankId);
        int k = Integer.parseInt(r);
        return k;
    }

   
    public static int addAuditCustomerTrail(String acid) {
        String data = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER, ENTITY_CRE_FLG, FORACID, LCHG_USER_ID, LIEN_AMT, RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID ,SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,ACCT_OPN_DATE from GENERAL_ACCT_MAST_TABLE where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(data, 1, acid, 27);
        int h = 0;
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String in = one.get(0) + "," + one.get(1) + "," + one.get(2) + "," + one.get(3) + "," + one.get(4) + "," + one.get(5) + "," + one.get(6) + "," + one.get(7) + "," + one.get(8) + "," + one.get(9) + "," + one.get(10) + "," + one.get(11) + "," + one.get(12) + "," + one.get(13) + "," + one.get(14) + "," + one.get(15) + "," + one.get(16) + "," + one.get(17) + "," + one.get(18) + "," + one.get(9) + "," + one.get(20)
                    + "," + one.get(21) + "," + one.get(22) + "," + ((String) one.get(23)).substring(0, 10) + "," + ((String) one.get(24)).substring(0, 10) + "," + ((String) one.get(25)).substring(0, 10) + "," + ((String) one.get(26)).substring(0, 10);
            String sql = "insert into GENERAL_ACCT_MAST_AUDIT_TABLE(ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER, ENTITY_CRE_FLG, FORACID, LCHG_USER_ID, LIEN_AMT, RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID ,SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,ACCT_OPN_DATE,SNO) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111),try_convert(date, ?, 111) ,try_convert(date, ?, 111) ,try_convert(date, ?, 111) , GLS_SEQ.NEXTVAL)";
            h = AdminDb.dbWork(sql, 27, in);
        }
        return h;
    }

    public static String getLastGamModOper(String foracid) {
        String sql = "select last_oper from general_acct_mast_table_mod where foracid = ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
    }

    public static int getGroupId(String foracid) {
        String sql = "select group_id from sub_grp_table where sub_group_code= ?";
        String r = AdminDb.getValue(sql, 1, 1, foracid);
        int k = Integer.parseInt(r);
        return k;
    }

    // Update GAM  Mapped flag AND WAIT FOR APPROVAL
    public static void markAccountAsMappedinGam(String foracid, String memberStatus) {
        String sql = "update general_acct_mast_table set mapped_flg = ?, member_status = ? where cust_id = ?";
        String in = "Y," + memberStatus + "," + foracid;
        AdminDb.dbWork(sql, 3, in);
    }

    // Get subgroup name
    public static String getSubGroupName(String subGroupId) {
        String sql = "select sub_group_name from sub_grp_table where sub_group_code = ?";
        return AdminDb.getValue(sql, 1, 1, subGroupId);
    }

    // Get getlinkedAccounts
    public static String getlinkedAccounts(String subGroupId, int custId) {
        String sql = "select foracid from general_acct_mast_table where schm_code like ? and cust_id = ?";
        String in = subGroupId + "," + custId;
        return AdminDb.getValue(sql, 1, 2, in);
    }

    // Verify and delete from mod table
    public static void verifyAccountDetails(String foracid, String uname) {
        String sql = "select sub_group_code, credit_rating from general_acct_mast_table_mod where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 2);
        int k = Integer.parseInt(foracid);
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String sub = (String) one.get(0);
            String act = (String) one.get(1);
            completeVerification(k, sub, act, uname);
            deleteAfterVerification(k);
        }
    }

    // Current Account Details
    public static ArrayList getAccountDetails(String foracid) {
        String sql = "select acct_name, sol_id, sub_group_code, credit_rating from general_acct_mast_table where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 4);
        int k = Integer.parseInt(foracid);
        ArrayList arr = new ArrayList();
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            ArrayList one = new ArrayList();
            one.add(foracid);
            one.add((String) hs.get(0));
            one.add(getlinkedAccounts("GLS%", k));
            one.add(getlinkedAccounts("SBGCO", k));
            one.add(getlinkedAccounts("SBGLS", k));
            one.add((String) hs.get(1));
            one.add((String) hs.get(2));
            one.add(getSubGroupName((String) hs.get(2)));//5
            one.add((String) hs.get(3));
            arr.add(one);
        }
        return arr;
    }

    // Update GAM with verified details
    public static void completeVerification(int foracid, String subGroupId, String actType, String uname) {
        String mStatus;
        switch (lastOperFlg(foracid)) {
            case "A":
            case "M":
                mStatus = "A";
                break;
            default:
                mStatus = "D";
                break;
        }
        String sql = "update general_acct_mast_table set member_status = ?, credit_rating = ?, sub_group_code = ?, lchg_user_id = ?, lchg_time = GETDATE(), last_modified_time = GETDATE() where cust_id = ?";
        String in = mStatus + "," + actType + "," + subGroupId + "," + uname + "," + foracid;
        AdminDb.dbWork(sql, 4, in);
    }

    // delete mod record
    public static void deleteAfterVerification(int foracid) {
        String sql = "delete from general_acct_mast_table_mod where cust_id = ?";
        AdminDb.dbWork(sql, 1, String.valueOf(foracid));
    }

    private static String lastOperFlg(int custId) {
        String sql = "select last_oper from general_acct_mast_table_mod where cust_id = ?";
        return AdminDb.getValue(sql, 1, 1, String.valueOf(custId));
    }
    
    
    
//        public static void main(String[] args) {
//            //cust_id, acct_name, sol_id
//        ArrayList roles = getUnMappedAccounts() ;  
//     System.out.println("--Desc --  time ---userId -- bankId--");
//        for(int w = 1; w <= roles.size(); w++){
//           String cust_id =  roles.get(0).toString();
//            String acct_name = roles.get(1).toString();
//             String sol_id =  roles.get(2).toString(); 
//             System.out.println(cust_id+" --"+acct_name+" --"+sol_id); 
//           System.out.println("--Desc --  time ---userId -- bankId--");      
//        }
//    }
}
