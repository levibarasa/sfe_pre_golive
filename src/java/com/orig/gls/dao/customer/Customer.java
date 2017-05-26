package com.orig.gls.dao.customer;

import com.orig.gls.conn.AdminDb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Customer {

    public static ArrayList getUnMappedAccounts() {
        String sql = "select DISTINCT cust_id, acct_name, sol_id from general_acct_mast_table where sub_group_code is null and mapped_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "N", 3);
    }

    public static ArrayList getAccounts() {
        String sql = "select DISTINCT cust_id, acct_name  from general_acct_mast_table where sub_group_code is  null and mapped_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "N", 2);
    }

    public static int getNumberOfSbGrpMembers(int groupId) {
        String s = "select no_of_members from sub_grp_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static int getMaxSbGrpMembers(int groupId) {
        String s = "select no_of_members from sub_grp_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
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

    public static int getNumberOfGrpMembers(int groupId) {
        String s = "select no_of_members from groups_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static int getMaxNumberOfGrpMembers(int groupId) {
        String s = "select MAX_ALLOWED_MEMBERS from groups_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static void addGroupMember(int groupId, String username) {
        int k = getNumberOfGrpMembers(groupId);
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
        String sql = "select cust_id, foracid, acct_name, acct_opn_date, sol_id,schm_type,schm_code ,sub_group_code, last_oper  from general_acct_mast_table_mod  where LAST_OPER not like 'V' and  LAST_OPER not like 'D' and LAST_OPER not like 'E' AND rcre_user_id <>?";
        return AdminDb.execArrayLists(sql, 1, uname, 9);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllUnMappedExitAccountMod(String uname) {
        String sql = "select DISTINCT cust_id, foracid, acct_name, acct_opn_date, sol_id,schm_type,schm_code ,sub_group_code, last_oper  from general_acct_mast_table_mod  where LAST_OPER like 'V' OR   LAST_OPER like 'D' OR LAST_OPER like 'E' AND rcre_user_id <>?";
        return AdminDb.execArrayLists(sql, 1, uname, 9);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllVerfiedAccounts() {
        String sql = "SELECT DISTINCT cust_id, acct_name, sol_id, sub_group_code from general_acct_mast_table where mapped_flg = ? and member_status=? and sub_group_code is not null";
        String in = "Y,A";
        return AdminDb.execArrayLists(sql, 2, in, 4);
    }

    public static String getCustIdByName(String name) {
        String sql = " select cust_id from GENERAL_ACCT_MAST_TABLE where ACCT_NAME=?";
        return AdminDb.getValue(sql, 1, 1, name);
    }

    public static String getMemberStatus(String accnt) {
        String sql = "select member_status from  general_acct_mast_table where CUST_ID= ?";
        return AdminDb.getValue(sql, 1, 1, accnt);
    }
//   public static void main(String[] args) {
//        System.out.println(getAccountDetails("000037026", "WES2010", "A","1", "MARK"));
//    }

    // get data to insert into mod table
    public static int getAccountDetails(String forr, String subGroupId, String lastOper, String creditRate, String uname) {
        String sql = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OWNERSHIP,"
                + "BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,"
                + "LIEN_AMT,RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,RCRE_TIME,"
                + "ACCT_OPN_DATE from general_acct_mast_table where cust_id = ?";
        String str = AdminDb.getValue(sql, 20, 1, forr);
        String[] args = str.split("\\s*,\\s*");
        String s = "insert into general_acct_mast_table_mod (ACID,ACCT_CRNCY_CODE,"
                + "ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,"
                + "CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,LIEN_AMT,"
                + "RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,RCRE_TIME,"
                + "ACCT_OPN_DATE,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID, SUB_GROUP_CODE,"
                + "LAST_OPER,CREDIT_RATING) values"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111) ,"
                + "try_convert(date, ?, 111),try_convert(date, getDate(), 111) ,"
                + "try_convert(date, getDate(), 111),?,?,?,?)";
        String in = "";

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        for (int w = 0; w < 20; w++) {
            in = in + args[w] + ",";
        }

        String adds = uname + "," + subGroupId + "," + lastOper + "," + creditRate;
        in = in + adds;
        int n = AdminDb.dbWork(s, 24, in);
        if (n > 0) {
            markAccountAsMappedinGam(forr, lastOper);
        }
        return n;
    }

    public static int getSolId(String bankId) {
        String sql = "select sol_id from  service_outlet_table where bank_id= ?";
        String r = AdminDb.getValue(sql, 1, 1, bankId);
        int k = Integer.parseInt(r);
        return k;
    }

    public static int addAuditCustomerTrail(String acid) {
        String data = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,"
                + "ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER,"
                + " ENTITY_CRE_FLG, FORACID, LCHG_USER_ID, LIEN_AMT, RCRE_USER_ID,"
                + "SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID ,"
                + "SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, "
                + "CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,"
                + "ACCT_OPN_DATE from GENERAL_ACCT_MAST_TABLE where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(data, 1, acid, 27);
        //System.out.println(""+ar);
        int h = 0;
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String in = one.get(0) + "," + one.get(1) + "," + one.get(2) + ","
                    + one.get(3) + "," + one.get(4) + "," + one.get(5) + ","
                    + one.get(6) + "," + one.get(7) + "," + one.get(8) + ","
                    + one.get(9) + "," + one.get(10) + "," + one.get(11) + ","
                    + one.get(12) + "," + one.get(13) + "," + one.get(14) + ","
                    + one.get(15) + "," + one.get(16) + "," + one.get(17) + ","
                    + one.get(18) + "," + one.get(19) + "," + one.get(20)
                    + "," + one.get(21) + "," + one.get(22) + ","
                    + one.get(23) + "," + one.get(24)
                    + "," + one.get(25) + "," + one.get(26);

            String sql = "insert into GENERAL_ACCT_MAST_AUDIT_TABLE("
                    + "ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,"
                    + "ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER,"
                    + " ENTITY_CRE_FLG, FORACID, LCHG_USER_ID,"
                    + " LIEN_AMT, RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID "
                    + ",SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, "
                    + "CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,"
                    + "ACCT_OPN_DATE) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "try_convert(date, ?, 111),try_convert(date, ?, 111) ,try_convert(date, ?, 111) "
                    + ",try_convert(date, ?, 111))";
            h = AdminDb.dbWork(sql, 27, in);
        }
        return h;
    }

    public static String getLastGamModOper(String foracid) {
        String sql = "select last_oper from general_acct_mast_table_mod where foracid = ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
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

//--
    // Get getlinkedAccounts
    public static String getlinkedAccounts(String subGroupId, String custId) {
        String sql = "select foracid from general_acct_mast_table where schm_code like ? and cust_id = ?";
        String in = subGroupId + "," + custId;
        return AdminDb.getValue(sql, 1, 2, in);
    }

    // Verify and delete from mod table
    public static void verifyAccountDetails(String foracid, String uname) {
        String sql = "select sub_group_code, credit_rating from general_acct_mast_table_mod where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 2);
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String sub = (String) one.get(0);
            String act = (String) one.get(1);
            completeVerification(foracid, sub, act, uname);
            deleteAfterVerification(foracid);
        }
    }

//    public static void main(String[] args) {
//
//        String custId = "";
//        String acctName, savingsAcnt, oprAcnt, loanAcnt, solId, groupCode, groupName, subgroupCode, subgroupName, creditRating, member_status;
//        acctName = savingsAcnt = loanAcnt = oprAcnt = solId = groupCode = groupName = subgroupCode = subgroupName = creditRating = member_status = "";
//        ArrayList all = getAccountDetails("000037022");
//        int size = all.size();
//
//        for (int i = 0; i < size; i++) {
//            ArrayList one = (ArrayList) all.get(i);
//            custId = (String) one.get(0);
//            acctName = (String) one.get(1);
//            savingsAcnt = (String) one.get(2);
//            loanAcnt = (String) one.get(3);
//            oprAcnt = (String) one.get(4);
//            solId = (String) one.get(5);
//            groupCode = (String) one.get(6);
//            subgroupCode = (String) one.get(7);
//            groupName = (String) one.get(8);
//            subgroupName = (String) one.get(9);
//            creditRating = (String) one.get(10);
//            member_status = (String) one.get(11);
//
//            System.out.println("CustId: " + custId + "\n Acc Name: " + acctName + "\n Sav Acc: " + savingsAcnt
//                    + "\n Loan Acc: " + loanAcnt + "\n Loan Acc: " + oprAcnt + "\n Sol Id: " + solId + "\n Group Code: " + groupCode + "\n SubGroup Code: "
//                    + subgroupCode + "\n Group Name: " + groupName + "\n SubGroup Name: " + subgroupName
//                    + "\n CreditRating Name: " + creditRating + "\n Member Status: " + member_status);
//        }
//    }

    // Current Account Details
    public static ArrayList getAccountDetails(String foracid) {
        String sql = "select acct_name, sol_id, sub_group_code, credit_rating,member_status from general_acct_mast_table where  cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 5);

        ArrayList arr = new ArrayList();
        String operAcc = "001";
        String loanAcc = "001";
        String saveAcc = "001";
        String groupName = " ";
        String groupCode = " ";
        String subGroupName = " ";
        String member_status = "";
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            //loan acc ... GLSGR,GLSIN
            loanAcc = getlinkedAccounts("GLS%", foracid);
            operAcc = getlinkedAccounts("SBGCO", foracid);
            saveAcc = getlinkedAccounts("SBGLS", foracid);

            String accName = (String) hs.get(0);
            String solId = (String) hs.get(1);
            String subGroupCode = (String) hs.get(2);
            String creditRating = (String) hs.get(3);
            member_status = (String) hs.get(4);
            groupName = getGroupName(subGroupCode);
            groupCode = getGroupCode(subGroupCode);
            subGroupName = getSubGroupName(subGroupCode);

            ArrayList one = new ArrayList();
            if (operAcc.isEmpty()) {
                operAcc = "001";
            }
            if (loanAcc.isEmpty()) {
                loanAcc = "001";
            }
            if (saveAcc.isEmpty()) {
                saveAcc = "001";
            }
            one.add(foracid);
            one.add(accName);
            one.add(loanAcc);
            one.add(saveAcc);
            one.add(operAcc);
            one.add(solId);
            one.add(groupCode);
            one.add(subGroupCode);
            one.add(groupName);
            one.add(subGroupName);//5
            one.add(creditRating);
            one.add(member_status);
            arr.add(one);
        }
        return arr;
    }
    public static ArrayList getVoluntaryExitAccountDetails(String foracid) {
        String sql = "select acct_name, sol_id, sub_group_code, credit_rating,member_status from general_acct_mast_table where member_status='V' and  cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 5);

        ArrayList arr = new ArrayList();
        String operAcc = "001";
        String loanAcc = "001";
        String saveAcc = "001";
        String groupName = " ";
        String groupCode = " ";
        String subGroupName = " ";
        String member_status = "";
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            //loan acc ... GLSGR,GLSIN
            loanAcc = getlinkedAccounts("GLS%", foracid);
            operAcc = getlinkedAccounts("SBGCO", foracid);
            saveAcc = getlinkedAccounts("SBGLS", foracid);

            String accName = (String) hs.get(0);
            String solId = (String) hs.get(1);
            String subGroupCode = (String) hs.get(2);
            String creditRating = (String) hs.get(3);
            member_status = (String) hs.get(4);
            groupName = getGroupName(subGroupCode);
            groupCode = getGroupCode(subGroupCode);
            subGroupName = getSubGroupName(subGroupCode);

            ArrayList one = new ArrayList();
            if (operAcc.isEmpty()) {
                operAcc = "001";
            }
            if (loanAcc.isEmpty()) {
                loanAcc = "001";
            }
            if (saveAcc.isEmpty()) {
                saveAcc = "001";
            }
            one.add(foracid);
            one.add(accName);
            one.add(loanAcc);
            one.add(saveAcc);
            one.add(operAcc);
            one.add(solId);
            one.add(groupCode);
            one.add(subGroupCode);
            one.add(groupName);
            one.add(subGroupName);//5
            one.add(creditRating);
            one.add(member_status);
            arr.add(one);
        }
        return arr;
    }

    public static int getGroupId(String subGrCode) {
        String sql = "select group_id from sub_grp_table where sub_group_code= ?";
        String r = AdminDb.getValue(sql, 1, 1, subGrCode);
        int k = Integer.parseInt(r);
        return k;
    }

    public static String getGroupName(String subGrCode) {
        String grpId = String.valueOf(getGroupId(subGrCode));
        String sql = "select group_name from groups_table where group_id= ?";
        return AdminDb.getValue(sql, 1, 1, grpId);
    }

    public static String getGroupCode(String subGrCode) {
        String grpId = String.valueOf(getGroupId(subGrCode));
        String sql = "select group_code from groups_table where group_id= ?";
        return AdminDb.getValue(sql, 1, 1, grpId);
    }

    // Update GAM with verified details
    public static void completeVerification(String foracid, String subGroupId, String actType, String uname) {
        String sql = "update general_acct_mast_table set  credit_rating = ?, sub_group_code = ?, "
                + "lchg_user_id = ?, lchg_time = try_convert(date, getDate(), 111), "
                + "last_modified_date = try_convert(date, getDate(), 111) where cust_id = ?";
        String in = actType + "," + subGroupId + "," + uname + "," + foracid;
        AdminDb.dbWork(sql, 4, in);
    }

    // delete mod record
    public static void deleteAfterVerification(String foracid) {
        String sql = "delete from general_acct_mast_table_mod where cust_id = ?";
        AdminDb.dbWork(sql, 1, foracid);
    }

    private static String lastOperFlg(int custId) {
        String sql = "select last_oper from general_acct_mast_table_mod where cust_id = ?";
        return AdminDb.getValue(sql, 1, 1, String.valueOf(custId));
    }

}
