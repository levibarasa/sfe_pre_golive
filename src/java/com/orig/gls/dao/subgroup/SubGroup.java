package com.orig.gls.dao.subgroup;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.orig.gls.conn.AdminDb;
import com.orig.gls.dao.group.Group;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SubGroup {

    private static final Log log = LogFactory.getLog("origlogger");
    private static int subgroupId;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static boolean subgroupExists(String groupCode, String groupName) {
        String sql = "select count(*)cnt from sub_grp_table where sub_group_code = ? and sub_group_name = ?";
        String in = groupCode + "," + groupName;
        String r = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(r) > 0;
    }

    public static int getsubGroupId(String groupCode, String groupName) {
        String sql = "select sub_group_id from sub_grp_table where sub_group_code = ? and sub_group_name = ?";
        String in = groupCode + "," + groupName;
        String r = AdminDb.getValue(sql, 1, 2, in);
        return Integer.parseInt(r);
    }

    public static String getgroupName(String groupId) {
        String sql = "select GROUP_NAME  from GROUPS_TABLE where group_id = ?";
        String in = groupId;
        return AdminDb.getValue(sql, 1, 1, in);
    }

    public static String getgroupId(String groupCode) {
        String sql = "select group_id  from GROUPS_TABLE where GROUP_CODE = ?";
        String in = groupCode;
        return AdminDb.getValue(sql, 1, 1, in);
    }

    public static String getgroupCode(String groupId) {
        String sql = "select GROUP_CODE  from GROUPS_TABLE where group_id = ?";
        String in = groupId;
        return AdminDb.getValue(sql, 1, 1, in);
    }

    public static String getLastOper(String groupCode, String groupName) {
        String sql = "select last_oper from sub_grp_table_mod where sub_group_code = ? and sub_group_name = ?";
        String in = groupCode + "," + groupName;
        return AdminDb.getValue(sql, 1, 2, in);
    }
    public static String groupName(String groupId) {
        String sql = "select GROUP_NAME from groups_table where group_id = ?";
        return AdminDb.getValue(sql, 1, 1, groupId);
    }
    
 public static String groupCode(String groupId) {
        String sql = "select group_code from groups_table where group_id = ?";
        return AdminDb.getValue(sql, 1, 1, groupId);
    }
    
    public static String getgroupCodes(int groupCode) {
        String sql = "select group_code from groups_table where group_id = ?";
        return AdminDb.getValue(sql, 1, 1, String.valueOf(groupCode));
    }

    public static int getGroupId(String groupCode) {
        String sql = "select group_id from sub_grp_table where sub_group_code = ?";
        String r = AdminDb.getValue(sql, 1, 1, groupCode);
        return Integer.parseInt(r);
    }

//        public static void main(String[] args) {
//        addsubGroupDetails("001", "KE", "N", "BOX 128 0010900 NBI", 100000, "BODABODA CREW", "0776376823", "001981", "325632284", new Date(), "MIKE", 20, 9, 10, 45000, 50000, new Date(), "MIKE", "WESTLANDS", "SBG001", new Date(), "NAIROBI", "CHIROMO", new Date(), "10:00AM", "Office", "Charity", "Mark", "Justine", "N", "N", 3, 3, "001", "WESTLANDS", "W", "8328728", "MASHUJAA","000037022","000037023","000037024");
//    }
    
    public static String getNoOfMembers(int groupCode) {
        String sql = "select group_code from groups_table where group_id = ?";
        return AdminDb.getValue(sql, 1, 1, String.valueOf(groupCode));
    }
    
    public static int addsubGroupDetails(String bankId, String countryCode, String delFlg, String subGroupAddress, double subGroupLoans, String subGroupName, String subGroupPhone, String subGrpMgrId, String subGrpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int groupId, int noOfMembers, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String subGpRegion, String subgroupCode, String formationDate, String subGroupCenter, String subGroupVillage, String firstMeetDate, String meetTime, String meetPlace, String subGpChair, String subGpTreasurer, String subGpSecretary, String subGpStatus, String subGpStatusCode, int loanAccounts, int savingAccounts, String solId, String branchName, String meetFrequency, String accountNo, String accountName, String gpChairId, String gpTreasurerId, String gpSecretaryId) {
        Date first_MeetDate = new Date();
        String sql = "insert into sub_grp_table(bank_id, branch_name, country_code, del_flg, group_id, "
                + "lchg_user_id, loan_accounts, max_allowed_members, meet_frequency, meet_place, no_of_members, "
                + "outstanding_bal, rcre_user_id, saving_accounts, savings_amt, sol_id, sub_gp_region, sub_gp_status, "
                + "sub_gp_status_code, sub_group_address, sub_group_center, sub_group_code, sub_group_loans, sub_group_name,"
                + " sub_group_phone, sub_group_village, sub_grp_mgr_id, sub_grp_reg_no, sub_grp_acnt_no, sub_grp_acnt_name, "
                + "first_meet_date, formation_date,lchg_date,nxt_meet_date, rcre_time,GP_CHAIR_ID,"
                + "GP_SECRETARY_ID,GP_TREASURER_ID,SUB_GP_CHAIR,SUB_GP_TREASURER,SUB_GP_SECRETARY,MEET_TIME) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111) ,"//1-31
                + "try_convert(date, ?, 111) ,try_convert(date, ?, 111) ,try_convert(date, ?, 111)  "
                + ",try_convert(date, GETDATE(), 111),?,?,?,?,?,?,?)";
        if (bankId.isEmpty()) {
            bankId = "001";
        }

        if (countryCode.isEmpty()) {
            countryCode = "KE";
        }
        if (rcreTime == null) {
            parseDates(new Date());
        }
        
        //noOfMembers
        try {
            SimpleDateFormat in = new SimpleDateFormat("dd-MMM-yyyy");
            Date fm_date = in.parse(firstMeetDate);
            String fmd = sdf.format(fm_date);
            first_MeetDate = sdf.parse(fmd);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        branchName = Group.getbranchName(solId);
        String in = bankId + "," + branchName + "," + countryCode + ","
                + delFlg + "," + groupId + "," + lchgUserId + "," + loanAccounts
                + "," + maxAllowedMembers + "," + meetFrequency + "," + meetPlace
                + "," + noOfMembers + "," + outstandingBal + "," + rcreUserId + ","
                + savingAccounts
                + "," + savingsAmt + ","
                + solId + ","
                + subGpRegion
                + "," + subGpStatus + "," + subGpStatusCode + ","
                + subGroupAddress + "," + subGroupCenter + "," + subgroupCode
                + "," + subGroupLoans + "," + subGroupName + "," + subGroupPhone
                + "," + subGroupVillage + "," + subGrpMgrId + "," + subGrpRegNo + ","
                + accountNo + "," + accountName + "," + firstMeetDate + ","
                + formationDate + "," + parseDates(lchgDate) + ","
                + parseDates(getNextMettingDate(first_MeetDate, meetFrequency)) + ","
                + gpChairId + "," + gpTreasurerId + "," + gpSecretaryId + ","
                + subGpChair + "," + subGpTreasurer + "," + subGpSecretary+ "," + meetTime;
        int addmod = AdminDb.dbWork(sql, 41, in);  
        if (addmod > 0) {
            String s = "select sub_group_id from sub_grp_table where sub_group_code = ?";
            String r = AdminDb.getValue(s, 1, 1, subgroupCode);
            subgroupId = Integer.parseInt(r);
        }
        return subgroupId;
    }

    public static ArrayList getAllVerifiedSubGroups() {
        String sql = "select sub_group_code, sub_group_id, sub_group_name, rcre_time, rcre_user_id, sol_id,"//0-5
                + " branch_name, sub_grp_mgr_id, sub_grp_reg_no, formation_date, sub_gp_region,sub_group_center,"//6-11
                + " sub_group_village, sub_group_address, sub_group_phone,first_meet_date, nxt_meet_date,"//12-16
                + " meet_time, meet_place, max_allowed_members, sub_gp_chair, sub_gp_treasurer, sub_gp_secretary,"//17-22
                + "sub_gp_status, sub_gp_status_code, no_of_members, meet_frequency, saving_accounts, savings_amt,"//23-28
                + " loan_accounts,outstanding_bal, 'PROTECTED', 'PROTECTED',GP_CHAIR_ID,GP_SECRETARY_ID,"//29-34
                + "GP_TREASURER_ID, sub_grp_acnt_no, sub_grp_acnt_name,group_id,MEET_TIME "//35-39
                + "from sub_grp_table where sub_group_id not in (select sub_group_id from sub_grp_table_mod)";
        return AdminDb.execArrayLists(sql, 0, "", 40);
    }

    public static ArrayList getAllSubGroups() {
        String sql = "select sub_group_code, sub_group_id, sub_group_name, rcre_time, rcre_user_id,"//0-4
                + " sol_id, branch_name, sub_grp_mgr_id,sub_grp_reg_no, formation_date, sub_gp_region,"//5-10                                                                                                                                                                   
                + " sub_group_center, sub_group_village, sub_group_address, sub_group_phone,"//11-14
                + "first_meet_date, nxt_meet_date, meet_time, meet_place, max_allowed_members,"//15-19
                + " sub_gp_chair, sub_gp_treasurer, sub_gp_secretary,sub_gp_status, sub_gp_status_code,"//20-24
                + " no_of_members, meet_frequency, saving_accounts, savings_amt, loan_accounts, "//25-29
                + "outstanding_bal, 'PROTECTED', 'PROTECTED', sub_grp_acnt_no,GP_CHAIR_ID,"//30-34
                + "GP_SECRETARY_ID,GP_TREASURER_ID,sub_grp_acnt_name,group_id,MEET_TIME from sub_grp_table";//35-39
        return AdminDb.execArrayLists(sql, 0, "", 40);
    }
//    public static void main(String[] args) {
//        ArrayList all = getUnverifiedSubGroups("");
//        int size = all.size();
//        for (int i = 0; i < size; i++) {
//            ArrayList one = (ArrayList) all.get(i);
//            System.out.println(one.get(39));//+" "+one.get(35)+" "+one.get(36));
//        }
//    }

    public static ArrayList getUnverifiedSubGroups(String username) {
        String sql = "select sub_group_code, sub_group_id, sub_group_name, rcre_time, rcre_user_id, "//0-4
                + "sol_id, branch_name, sub_grp_mgr_id,sub_grp_reg_no, formation_date, sub_gp_region,"//5-10
                + " sub_group_center, sub_group_village, sub_group_address, sub_group_phone,"//11-14
                + "first_meet_date, nxt_meet_date, meet_time, meet_place, max_allowed_members,"//15-19
                + " sub_gp_chair, sub_gp_treasurer, sub_gp_secretary, sub_gp_status, sub_gp_status_code, "//20-24
                + "no_of_members, meet_frequency, saving_accounts, savings_amt, loan_accounts, "//25-29
                + "outstanding_bal, 'PROTECT', 'PROTECTED', sub_grp_acnt_no, GP_CHAIR_ID,"//30-34(31,32)
                + "GP_SECRETARY_ID,GP_TREASURER_ID,sub_grp_acnt_name,group_id,MEET_TIME from"//35-39(34,35,36)
                + " sub_grp_table_mod where rcre_user_id <> ?";
        return AdminDb.execArrayLists(sql, 1, username, 40);
    }

    public static ArrayList getUnverifiedSubGroups() {
        String sql = "select sub_group_code, sub_group_id, sub_group_name, rcre_time, rcre_user_id,"//0-4
                + " sol_id, branch_name, sub_grp_mgr_id,sub_grp_reg_no, formation_date, sub_gp_region, "//5-10
                + "sub_group_center, sub_group_village, sub_group_address, sub_group_phone," //11-14
                + "first_meet_date, nxt_meet_date, meet_time, meet_place, max_allowed_members,"//15-19
                + " sub_gp_chair, sub_gp_treasurer, sub_gp_secretary, sub_gp_status, sub_gp_status_code,"//20-24
                + " no_of_members, meet_frequency, saving_accounts, savings_amt, loan_accounts, "//25-29
                + "outstanding_bal, 'PROTECTED', 'PROTECTED', sub_grp_acnt_no, GP_CHAIR_ID,"//30-34
                + "GP_SECRETARY_ID,GP_TREASURER_ID,sub_grp_acnt_name,group_id,MEET_TIME from "//35-39
                + "sub_grp_table_mod";
        return AdminDb.execArrayLists(sql, 0, "", 40);
    }

    public static double getMemberSavings(String subgroup) {
        double custId = 0.0;
        String sql = "select SUM(cast(CLR_BAL_AMT as decimal(15,2))) from general_acct_mast_table where SUB_GROUP_CODE = ?  and SCHM_CODE not LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, subgroup);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

    public static boolean getSubGroupModDetails(int subGroupId, String lastOper) {
        boolean mod = false;
        String sql = "select  bank_id, branch_name, country_code, del_flg, group_id,lchg_user_id, loan_accounts, max_allowed_members,"
                + " meet_frequency, meet_place,MEET_TIME, no_of_members, outstanding_bal, rcre_user_id, saving_accounts, savings_amt, "
                + " sol_id, sub_gp_region, sub_gp_status, sub_gp_status_code, sub_group_address, sub_group_center, "
                + " sub_group_code, sub_group_loans, sub_group_name,sub_group_phone, sub_group_village, sub_grp_mgr_id, "
                + " sub_grp_reg_no, sub_grp_acnt_no, sub_grp_acnt_name,  "
                + "  first_meet_date, formation_date,lchg_date,nxt_meet_date, rcre_time,GP_CHAIR_ID, "
                + " GP_SECRETARY_ID,GP_TREASURER_ID,SUB_GP_CHAIR,SUB_GP_TREASURER,SUB_GP_SECRETARY,sub_group_id "
                + " from sub_grp_table where sub_group_id=?";
        String str = AdminDb.getValue(sql, 43, 1, String.valueOf(subGroupId));
        String[] args = str.split("\\s*,\\s*");
        String s = "insert into sub_grp_table_mod(bank_id, branch_name, country_code, del_flg, group_id,lchg_user_id, "//1-6
                + "loan_accounts, max_allowed_members,meet_frequency, meet_place,MEET_TIME, no_of_members, outstanding_bal, "//7-13
                + "rcre_user_id, saving_accounts, savings_amt,sol_id, sub_gp_region, sub_gp_status, sub_gp_status_code, "//13-19
                + "sub_group_address, sub_group_center,sub_group_code, sub_group_loans, sub_group_name,sub_group_phone,"//20-25
                + " sub_group_village, sub_grp_mgr_id,sub_grp_reg_no, sub_grp_acnt_no, sub_grp_acnt_name,first_meet_date, "//26-31
                + "formation_date,lchg_date,nxt_meet_date, rcre_time,GP_CHAIR_ID,"//32-36
                + "GP_SECRETARY_ID,GP_TREASURER_ID,SUB_GP_CHAIR,SUB_GP_TREASURER,SUB_GP_SECRETARY,sub_group_id,last_oper)"//37-43
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111),"//1-31
                + "try_convert(date, ?, 111) ,try_convert(date, ?, 111) ,try_convert(date, ?, 111),"//32-34
                + "try_convert(date, ?, 111),?,?,?,?,?,?,?,?)";//35-43
        String in = "";

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        for (int w = 0; w < 43; w++) {
            in = in + args[w] + ",";
        }
        //String adds = subGroupId + "," + lastOper;
        String adds = lastOper;
        in = in + adds;
        int n = AdminDb.dbWork(s, 44, in);
        if (n > 0) {
            mod = true;
        }
        return mod;
    }

    public static boolean previousAddSubGroupModDetails(int subGroupId, String bankId, String countryCode, String delFlg, String subGroupAddress, double subGroupLoans, String subGroupName, String subGroupPhone, String subGrpMgrId, String subGrpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int groupId, int noOfMembers, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String subGpRegion, String subgroupCode, Date formationDate, String subGroupCenter, String subGroupVillage, Date firstMeetDate, String meetTime, String meetPlace, String subGpChair, String subGpTreasurer, String subGpSecretary, String subGpStatus, String subGpStatusCode, int loanAccounts, int savingAccounts, String solId, String branchName, String meetFrequency, String lastOper, String accountNo, String accountName, String gpChairId, String gpTreasurerId, String gpSecretaryId) {

        String sql = "insert into sub_grp_table_mod( bank_id, branch_name, country_code, del_flg, group_id,"
                + " last_oper, lchg_user_id, loan_accounts, max_allowed_members, meet_frequency, meet_place,"
                + " no_of_members, outstanding_bal, rcre_user_id, saving_accounts, savings_amt, sol_id,"
                + " sub_gp_region, sub_gp_status, sub_gp_status_code, sub_group_address, sub_group_center, "
                + "sub_group_code, sub_group_id, sub_group_loans, sub_group_name, sub_group_phone, sub_group_village,"
                + " sub_grp_mgr_id, sub_grp_reg_no, sub_grp_acnt_no, sub_grp_acnt_name,first_meet_date, formation_date,"
                + "lchg_date,nxt_meet_date, rcre_time,GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID,"
                + "SUB_GP_CHAIR,SUB_GP_TREASURER,SUB_GP_SECRETARY) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111),try_convert(date, getDate(), 111) ,try_convert(date, ?, 111)  ,try_convert(date, ?, 111),try_convert(date, getdate(), 111),?,?,?,?,?,?)";
        if (bankId.isEmpty()) {
            bankId = "001";
        }
        if (solId.isEmpty()) {
            solId = "001";
        }
        if (countryCode.isEmpty()) {
            countryCode = "UG";
        }
        if (rcreTime == null) {
            parseDates(new Date());
        }
        branchName = Group.getbranchName(solId);

        String in = bankId + "," + branchName + "," + countryCode + "," + delFlg
                + "," + groupId + "," + lastOper + "," + lchgUserId + "," + loanAccounts + "," + maxAllowedMembers + ","
                + meetFrequency + "," + meetPlace + "," + noOfMembers + "," + outstandingBal + "," + rcreUserId + ","
                + savingAccounts + "," + savingsAmt + "," + solId + "," + subGpRegion + "," + subGpStatus + ","
                + subGpStatusCode + "," + subGroupAddress + "," + subGroupCenter + "," + subgroupCode + ","
                + subGroupId + "," + subGroupLoans + "," + subGroupName + "," + subGroupPhone + "," + subGroupVillage
                + "," + subGrpMgrId + "," + subGrpRegNo + "," + accountNo + ","
                + accountName + "," + parseDates(firstMeetDate) + "," + parseDates(formationDate) + ","
                + parseDates(lchgDate) + ","
                + parseDates(getNextMettingDate(firstMeetDate, meetFrequency)) + ","
                + gpChairId + "," + gpTreasurerId + "," + gpSecretaryId + "," + subGpChair + "," + subGpTreasurer + "," + subGpSecretary;
        int addmod = AdminDb.dbWork(sql, 41, in);
        return addmod != 0;
    }
//get official name

    public static String getOfficialName(String custId) {
        String sql = "select ACCT_NAME from GENERAL_ACCT_MAST_TABLE where CUST_ID = ?";
        return AdminDb.getValue(sql, 1, 1, custId);
    }
//     public static void main(String[] args) {
//         System.out.println(getOfficialName("000037025"));  
//    }

    public static boolean executeaddSubGroup(String bankId, String countryCode, String delFlg, String subGroupAddress, double subGroupLoans, String subGroupName, String subGroupPhone, String subGrpMgrId, String subGrpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int groupId, int noOfMembers, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String subGpRegion, String subgroupCode, String formationDate, String subGroupCenter, String subGroupVillage, String firstMeetDate, String meetTime, String meetPlace, String subGpChair, String subGpTreasurer, String subGpSecretary, String subGpStatus, String subGpStatusCode, int loanAccounts, int savingAccounts, String solId, String branchName, String meetFrequency, String lastOper, String accountNo, String accountName, String gpChairId, String gpTreasurerId, String gpSecretaryId) {
        System.out.println("Sub group code is " + subgroupCode);

        if (branchName.isEmpty()) {
            branchName = Group.getbranchName(solId);
        }
        if (subGpChair.isEmpty()) {
            subGpChair = getOfficialName(gpChairId);
        }
        if (subGpTreasurer.isEmpty()) {
            subGpTreasurer = getOfficialName(gpTreasurerId);
        }
        if (subGpSecretary.isEmpty()) {
            branchName = Group.getbranchName(solId);
        }
        savingsAmt = getMemberSavings(subgroupCode);

        int subgroupid = addsubGroupDetails(bankId, countryCode, delFlg, subGroupAddress, subGroupLoans, subGroupName, subGroupPhone, subGrpMgrId, subGrpRegNo, lchgDate, lchgUserId, maxAllowedMembers, groupId, noOfMembers, outstandingBal, savingsAmt, rcreTime, rcreUserId, subGpRegion, subgroupCode, formationDate, subGroupCenter, subGroupVillage, firstMeetDate, meetTime, meetPlace, subGpChair, subGpTreasurer, subGpSecretary, subGpStatus, subGpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, accountNo, accountName, gpChairId, gpTreasurerId, gpSecretaryId);
        getSubGroupModDetails(subgroupid, lastOper);
        log.debug("Sub-Group Id: " + subgroupid + " Added Successfully");
        return subgroupid != 0;
    }

    public static void verifySubGroup(int groupId) {
        String sql = "delete from sub_grp_table_mod where sub_group_id = ?";
        AdminDb.dbWork(sql, 1, String.valueOf(groupId));
    }

    public static void deleteSubGroup(int groupId, String username) {
        String sql = "update sub_grp_table set del_flg = ?, lchg_user_id = ? where sub_group_id = ?";
        String in = "Y," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void modifySubGroup(String subgrpcode, String username) {
        String sql = "select sub_group_name, rcre_time, rcre_user_id, sol_id, branch_name, sub_grp_mgr_id, " //0-5
                + "sub_grp_reg_no, formation_date, sub_gp_region, sub_group_center, sub_group_village, sub_group_address, sub_group_phone,"//6-12
                + "first_meet_date, nxt_meet_date, meet_time, meet_place, max_allowed_members, sub_gp_chair, sub_gp_treasurer, sub_gp_secretary,"//13-20
                + "sub_gp_status, sub_gp_status, sub_gp_status_code, no_of_members, meet_frequency, saving_accounts, savings_amt, loan_accounts, "//21-28
                + "outstanding_bal, sub_grp_acnt_no, sub_grp_acnt_name from sub_grp_table_mod where sub_group_code = ?";//29-31
        String val = AdminDb.getValue(sql, 32, 1, subgrpcode);
        String sq = "update sub_grp_table set sub_group_name =?, rcre_time=?, rcre_user_id=?, sol_id=?, branch_name=?, sub_grp_mgr_id=?, sub_grp_reg_no=?,"//1-7
                + " formation_date=?, sub_gp_region=?, sub_group_center=?, sub_group_village=?, sub_group_address=?, sub_group_phone=?,first_meet_date=?,"//8-14
                + " nxt_meet_date=?, meet_time=?, meet_place=?, max_allowed_members=?, sub_gp_chair=?, sub_gp_treasurer=?, sub_gp_secretary=?, sub_gp_status=?,"//15-22
                + " sub_gp_status=?, sub_gp_status_code=?, no_of_members=?, meet_frequency=?, saving_accounts=?, savings_amt=?, loan_accounts=?, outstanding_bal=?,"//23-30
                + " sub_grp_acnt_no=?, sub_grp_acnt_name = ?, lchg_date = GETDATE(), lchg_user_id = ? where sub_group_code = ?";//31-34
        String n = val + "," + username+ "," + subgrpcode;
        AdminDb.dbWork(sq, 33, n);
    }

    private static String parseDates(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dateTime.toString(fmt);
    }

    private static Date getNextMettingDate(Date meetDate, String freq) {
        Date date = null;
        try {
            int days;
            switch (freq) {
                case "W":
                    days = 7;
                    break;
                case "M":
                    days = 30;
                    break;
                case "Q":
                    days = 90;
                    break;
                case "H":
                    days = 180;
                    break;
                default:
                    days = 365;
                    break;
            }
            Calendar c = Calendar.getInstance();
            c.setTime(meetDate); // Now use today date.
            c.add(Calendar.DATE, days); // Adding 5 days
            String output = sdf.format(c.getTime());
            date = sdf.parse(output);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return date;
    }

}
