package com.orig.gls.dao.group;

import com.orig.gls.conn.AdminDb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Group {

    /*
    1. create a method to autoincrement members during member/customer mapping.
    2. Group members should not exceed 6
    -should fetch sub-group chair person, secretery and treasure details
    
    
     */
    private static final Log log = LogFactory.getLog("origlogger");
    private static int groupId;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static boolean groupExists(String groupCode, String groupName) {
        String sql = "select count(*)CNT from groups_table where group_code= ? and group_name = ?";
        String in = groupCode + "," + groupName;
        String val = AdminDb.getValue(sql, 1, 2, in);
        int count = Integer.parseInt(val);
        return count > 0;
    }

    public static int getNumberOfSbGrps(String groupId) {
        String s = "select NO_OF_SUB_GRPS from GROUPS_TABLE where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1,  groupId);
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static int getMaxSbGrps(int groupId) {
        String s = "select MAX_ALLOWED_MEMBERS from GROUPS_TABLE where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    
    public static int getGroupId(String groupCode, String groupName) {
        String sql = "select group_id from groups_table where group_code = ? and group_name =?";
        String in = groupCode + "," + groupName;
        String r = AdminDb.getValue(sql, 1, 2, in);
        int k = 0;
        try {
            k = Integer.parseInt(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public static int getGroupIdForSubGrp(String groupCode) {
        String sql = "select group_id from groups_table where group_code = ?";
        String r = AdminDb.getValue(sql, 1, 1, groupCode);
        int k = 0;
        try {
            k = Integer.parseInt(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public static String getbranchName(String solId) {
        String sql = "select sol_desc from service_outlet_table where sol_id = ?";
        return AdminDb.getValue(sql, 1, 1, solId);
    }

    public static String getLastOper(int groupCode) {
        String sql = "select last_oper from groups_table_mod where group_id = ?";
        return AdminDb.getValue(sql, 1, 1, String.valueOf(groupCode));
    }

    public static int getGroupIdError(String groupCode) {
        String sql = "select group_id from groups_table where group_code =?";
        String r = AdminDb.getValue(sql, 1, 1, groupCode);
        int k = 0;
        try {
            k = Integer.parseInt(r);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public static String getgroupDetails(int groupId) {
        String sql = "select group_code, group_name from groups_table where group_id = ?";
        return AdminDb.getValue(sql, 2, 1, String.valueOf(groupId));
    }

    public static int addGroupDetails(String bankId, String countryCode, String delFlg, String groupAddress, double groupLoans, String groupName, String groupPhone, String grpMgrId, String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, String groupCode, Date formationDate, String groupCenter, String groupVillage, Date firstMeetDate, String meetTime, String meetPlace, String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, int savingAccounts, String solId, String branchName, String meetFrequency, String groupType, String gpChairId, String gpTreasurerId, String gpSecretaryId) {
        String sql = "insert into groups_table( BANK_ID,BRANCH_NAME,"
                + " COUNTRY_CODE,DEL_FLG, FIRST_MEET_DATE,FORMATION_DATE,"
                + "NXT_MEET_DATE,GP_CHAIR,GP_REGION, GP_SECRETARY,"
                + "GP_STATUS,GP_STATUS_CODE,GP_TREASURER,GROUP_ADDRESS,"
                + "GROUP_CENTER,GROUP_CODE,GROUP_LOANS,GROUP_NAME,GROUP_PHONE,"
                + "GROUP_VILLAGE,GRP_MGR_ID,GRP_REG_NO,LCHG_USER_ID,LOAN_ACCOUNTS,"
                + "MAX_ALLOWED_MEMBERS,MAX_ALLOWED_SUB_GRPS,MEET_FREQUENCY, "
                + "MEET_PLACE,MEET_TIME,NO_OF_MEMBERS, NO_OF_SUB_GRPS, "
                + "OUTSTANDING_BAL, RCRE_USER_ID,SAVING_ACCOUNTS,SAVINGS_AMT,"
                + "SOL_ID,GROUP_TYPE, LCHG_DATE,RCRE_TIME,GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID) values"
                + "(?,?,?,?,try_convert(date, ?, 111) ,try_convert(date, ?, 111) ,"
                + "try_convert(date, ?, 111) ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111) ,try_convert(date, ?, 111),?,?,?)";
        if (bankId.isEmpty()) {
            bankId = "001";
        }
        if (countryCode.isEmpty()) {
            countryCode = "UG";
        }
        if (rcreTime == null) {
            parseDates(new Date());
        }
        branchName = getbranchName(solId);
        String in = bankId + "," + branchName + "," + countryCode + "," + delFlg + "," + parseDates(firstMeetDate)
                + "," + parseDates(formationDate) + "," + parseDates(getNextMettingDate(firstMeetDate, meetFrequency))
                + "," + gpChair + "," + gpRegion + "," + gpSecretary + "," + gpStatus + "," + gpStatusCode + ","
                + gpTreasurer + "," + groupAddress + "," + groupCenter + "," + groupCode + "," + groupLoans + ","
                + groupName + "," + groupPhone + "," + groupVillage + "," + grpMgrId + "," + grpRegNo + ","
                + lchgUserId + "," + loanAccounts + "," + maxAllowedMembers + "," + maxAllowedSubGrps + ","
                + meetFrequency + "," + meetPlace + "," + meetTime + "," + noOfMembers + "," + noOfSubGrps + ","
                + outstandingBal + "," + rcreUserId + "," + savingAccounts + "," + savingsAmt + "," + solId + ","
                + groupType + "," + parseDates(lchgDate) + "," + parseDates(rcreTime) + "," + gpChairId + "," + gpTreasurerId + "," + gpSecretaryId;
        int k = AdminDb.dbWork(sql, 42, in);
        if (k > 0) {
            String s = "select group_id from groups_table where group_code = ?";
            String r = AdminDb.getValue(s, 1, 1, groupCode);
            groupId = Integer.parseInt(r);
        }
        return groupId;
    }

    public static ArrayList getAllVerifiedGroups() {
        String sql = "select group_code, group_id, group_name, rcre_time, rcre_user_id, sol_id,"//0-5
                + " branch_name, grp_mgr_id, grp_reg_no, formation_date,gp_region, group_center,"//6-11
                + " group_village, group_address, group_phone, first_meet_date, nxt_meet_date,"//12-16
                + " meet_time,meet_place, max_allowed_members, max_allowed_sub_grps,  gp_chair,"//17-21
                + " gp_treasurer, gp_secretary, gp_status, gp_status_code, no_of_members,"//22-26
                + " meet_frequency, saving_accounts,savings_amt, loan_accounts,"//27-30
                + " outstanding_bal,GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID,MEET_TIME from "//31-35(32,33,34)
                + "groups_table where group_id not in (select group_id from groups_table_mod)";
        return AdminDb.execArrayLists(sql, 0, "", 36);
    }

  
    public static ArrayList getAllGroups() {
        String sql = "select group_code, group_id, group_name, rcre_time, rcre_user_id, sol_id,"//0-5
                + "  branch_name, grp_mgr_id, grp_reg_no, formation_date,gp_region, group_center, "//6-11
                + "group_village, group_address, group_phone, first_meet_date,  nxt_meet_date,"//12-16
                + " meet_time,meet_place, max_allowed_members, max_allowed_sub_grps,gp_chair, "//17-21
                + "gp_treasurer, gp_secretary, gp_status, gp_status_code, no_of_members,  meet_frequency, "//22-27
                + "saving_accounts,savings_amt, loan_accounts, outstanding_bal,"//28-31
                + "GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID from groups_table";//32-34
        return AdminDb.execArrayLists(sql, 0, "", 35);
    }

//    public static void main(String[] args) {
//        ArrayList all = getAllGroups();
//        int size = all.size();
//        for (int i = 0; i < size; i++) {
//            ArrayList one = (ArrayList) all.get(i);
//            System.out.println(one.get(32)+" "+one.get(33)+" "+one.get(34));
//        }
//    }
  //return unverified users
    public static ArrayList getUnverifiedGroups(String username) {
        String sql = "select group_code, group_id, group_name, rcre_time, rcre_user_id, sol_id, branch_name, grp_mgr_id, grp_reg_no, formation_date,gp_region, "
                + "group_center, group_village, group_address, group_phone, first_meet_date, nxt_meet_date, meet_time,meet_place, max_allowed_members, max_allowed_sub_grps, "
                + "gp_chair, gp_treasurer, gp_secretary, gp_status, gp_status_code, no_of_members, meet_frequency, saving_accounts,"
                + "savings_amt, loan_accounts, outstanding_bal,GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID from groups_table_mod where rcre_user_id <> ?";
        return AdminDb.execArrayLists(sql, 1, username, 35);
    }

    public static ArrayList getUnverifiedGroups() {
        String sql = "select group_code, group_id, group_name, rcre_time, rcre_user_id, sol_id, branch_name, grp_mgr_id, grp_reg_no, formation_date,gp_region, "
                + "group_center, group_village, group_address, group_phone, first_meet_date, nxt_meet_date, meet_time,meet_place, max_allowed_members, max_allowed_sub_grps, "
                + "gp_chair, gp_treasurer, gp_secretary, gp_status, gp_status_code, no_of_members, "
                + "meet_frequency, saving_accounts, savings_amt, loan_accounts,"
                + " outstanding_bal,GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID from groups_table_mod";
        return AdminDb.execArrayLists(sql, 0, "", 35);
    }

    public static boolean addGroupModDetails(int groupId, String bankId, String countryCode, String delFlg, String groupAddress, double groupLoans, String groupName, String groupPhone, String grpMgrId, String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, String groupCode, Date formationDate, String groupCenter, String groupVillage, Date firstMeetDate, Date nxtMeetDate, String meetTime, String meetPlace, String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, int savingAccounts, String solId, String branchName, String meetFrequency, String lastOper, String gpChairId, String gpTreasurerId, String gpSecretaryId) {
        String sql = "insert into groups_table_mod(GROUP_ID, BANK_ID,BRANCH_NAME, "
                + "COUNTRY_CODE,DEL_FLG,GP_CHAIR,GP_REGION, GP_SECRETARY,GP_STATUS,"
                + "GP_STATUS_CODE,GP_TREASURER,GROUP_ADDRESS,GROUP_CENTER,GROUP_CODE,"
                + "GROUP_LOANS,GROUP_NAME,GROUP_PHONE,GROUP_VILLAGE,GRP_MGR_ID,GRP_REG_NO,"
                + "LCHG_USER_ID,LOAN_ACCOUNTS,MAX_ALLOWED_MEMBERS,MAX_ALLOWED_SUB_GRPS,"
                + "MEET_FREQUENCY, MEET_PLACE,MEET_TIME,NO_OF_MEMBERS, NO_OF_SUB_GRPS,"
                + "OUTSTANDING_BAL, RCRE_USER_ID,SAVING_ACCOUNTS,SAVINGS_AMT,SOL_ID,"
                + "LAST_OPER, FIRST_MEET_DATE,FORMATION_DATE,RCRE_TIME,LCHG_DATE,NXT_MEET_DATE,GP_CHAIR_ID,GP_SECRETARY_ID,GP_TREASURER_ID) values"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "try_convert(date, ?, 111) ,try_convert(date, ?, 111) ,try_convert(date, ?, 111) "
                + ",try_convert(date, ?, 111) ,try_convert(date, ?, 111),?,?,?)";
        branchName = getbranchName(solId);
        String in = groupId + "," + bankId + "," + branchName + "," + countryCode + "," + delFlg + "," + gpChair + "," + gpRegion + "," + gpSecretary + "," + gpStatus + "," + gpStatusCode + "," + gpTreasurer + "," + groupAddress + "," + groupCenter + "," + groupCode + "," + groupLoans + "," + groupName + "," + groupPhone + "," + groupVillage + "," + grpMgrId + "," + grpRegNo + "," + lchgUserId + "," + loanAccounts + "," + maxAllowedMembers + "," + maxAllowedSubGrps + "," + meetFrequency + "," + meetPlace + "," + meetTime + "," + noOfMembers + "," + noOfSubGrps + "," + outstandingBal + "," + rcreUserId + "," + savingAccounts + "," + savingsAmt + "," + solId + "," + lastOper + "," + parseDates(firstMeetDate) + "," + parseDates(formationDate) + "," + parseDates(rcreTime) + "," + parseDates(lchgDate) + "," + parseDates(getNextMettingDate(firstMeetDate, meetFrequency)) + "," + gpChairId + "," + gpTreasurerId + "," + gpSecretaryId;
        int addmod = AdminDb.dbWork(sql, 43, in);
        return addmod != 0;
    }

    public static boolean executeaddGroup(String bankId, String countryCode, String delFlg, String groupAddress, double groupLoans, String groupName, String groupPhone, String grpMgrId, String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers, int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal, double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, Date formationDate, String groupCenter, String groupVillage, Date firstMeetDate, String meetTime, String meetPlace, String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, int savingAccounts, String solId, String branchName, String meetFrequency, String lastOper, String groupType, String gpChairId, String gpTreasurerId, String gpSecretaryId) {
        Random random = new Random();
        int randomInt = random.nextInt(100);
        String runNu = String.format("%03d", randomInt);
        if (bankId.isEmpty()) {
            bankId = "001";
        }
        if (countryCode.isEmpty()) {
            countryCode = "UG";
        }
        if (rcreTime == null) {
            parseDates(new Date());
        }
        branchName = getbranchName(solId);
        //, gpChairId, gpTreasurerId, gpSecretaryId
        String groupCode = groupCenter.substring(0, 3) + solId + runNu;
        int groupid = addGroupDetails(bankId, countryCode, delFlg, groupAddress, groupLoans, groupName,
                groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps,
                noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, groupCode,
                formationDate, groupCenter, groupVillage, firstMeetDate, meetTime, meetPlace, gpChair, gpTreasurer,
                gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, groupType, gpChairId, gpTreasurerId, gpSecretaryId);

        addGroupModDetails(groupid, bankId, countryCode, delFlg, groupAddress, groupLoans, groupName, groupPhone, grpMgrId, grpRegNo, lchgDate, lchgUserId, maxAllowedMembers, maxAllowedSubGrps, noOfMembers, noOfSubGrps, outstandingBal, savingsAmt, rcreTime, rcreUserId, gpRegion, groupCode, formationDate, groupCenter, groupVillage, firstMeetDate, getNextMettingDate(firstMeetDate, meetFrequency), meetTime, meetPlace, gpChair, gpTreasurer, gpSecretary, gpStatus, gpStatusCode, loanAccounts, savingAccounts, solId, branchName, meetFrequency, lastOper, gpChairId, gpTreasurerId, gpSecretaryId);
        log.debug("Group Id: " + groupid + " Added Successfully");
        return groupid != 0;
    }
    //String bankId, String countryCode, String delFlg, String groupAddress, 
    //double groupLoans, String groupName, String groupPhone, String grpMgrId,
    //String grpRegNo, Date lchgDate, String lchgUserId, int maxAllowedMembers,
    //int maxAllowedSubGrps, int noOfMembers, int noOfSubGrps, double outstandingBal,
    //double savingsAmt, Date rcreTime, String rcreUserId, String gpRegion, Date formationDate, --
    //String groupCenter, String groupVillage, Date firstMeetDate, String meetTime, String meetPlace, 
    //String gpChair, String gpTreasurer, String gpSecretary, String gpStatus, String gpStatusCode, int loanAccounts, 
    //int savingAccounts, int solId, String branchName, String meetFrequency, String lastOper, String groupType 
//        public static void main(String[] args) {
//       boolean r = executeaddGroup("001", "KE", "N", "BOX 1002 NRB", 100000, 
//               "ARID MERCHANTS GROUP", "07354748498", "0100", "8200376", new Date(), 
//               "MIKE", 5, 5, 4, 5, 10000.00, 2000.00,  new Date(), "MIKE", "GITHURAI", new Date(),
//               "Center","Village", new Date(),"10:00AM","Office","Chair","Treasure","Secretary","N","DF",4,
//               4,1,"CBD","W","C","DF"
//               ); 
//       /*
//       "GRP001",  new Date(), "GC001", "VL",  new Date(), "10:00", "Office",
//               "SAM", "KIM", "OLIVER", "N", "N", 4, 4, 1, "CBD", "W", "DF"
//       */
//            System.out.println(" Results"+r);
//        }

    public static void verifyGroup(int groupId) {
        String sql = "delete from groups_table_mod where group_id = ?";
        AdminDb.dbWork(sql, 1, String.valueOf(groupId));
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
//    public static void main(String[] args) {
//        try{
//        System.out.println(sdf.format(getNextMettingDate(sdf.parse("2017-04-18"), "W")));
//        }
//        catch(Exception e){
//        e.printStackTrace();
//        }
//    }

    public static void deleteGroup(int groupId, String username) {
        String sql = "update groups_table set del_flg = ?, lchg_user_id = ? where group_id = ?";
        String in = "Y," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void addSubgroup(String groupCode, String username) {
        String s = "select no_of_sub_grps from groups_table where group_code = ?";
        String r = AdminDb.getValue(s, 1, 1, groupCode);
        int k = Integer.parseInt(r);
        String sql = "update groups_table set no_of_sub_grps = ?, lchg_user_id = ? where group_code = ?";
        String in = k + 1 + "," + username + "," + groupCode;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void removeSubgroup(String groupId, String username) {
        String s = "select no_of_sub_grps from groups_table where group_code = ?";
        String r = AdminDb.getValue(s, 1, 1, groupId);
        int k = Integer.parseInt(r);
        String sql = "update groups_table set no_of_sub_grps = ?, lchg_user_id = ? where group_code = ?";
        String in = k - 1 + "," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void modifyGroup(int groupId, String username) {
        String s = "select branch_name, FIRST_MEET_DATE, formation_date, gp_chair, gp_region, gp_secretary, gp_status, gp_status_code, gp_treasurer, group_address, group_center,"
                + "group_phone, group_village, grp_mgr_id, grp_reg_no, max_allowed_members, max_allowed_sub_grps, meet_frequency, meet_place, meet_time, nxt_meet_date, sol_id from "
                + "groups_table_mod where group_id = ?";
        String sql = "update groups_table set branch_name = ?, FIRST_MEET_DATE = ?, formation_date = ?, gp_chair = ?, gp_region = ?, gp_secretary = ?, gp_status = ?, gp_status_code= ?, gp_treasurer=?, group_address=?, group_center=?,"
                + "group_phone = ?, group_village = ?, grp_mgr_id = ?, grp_reg_no = ?, max_allowed_members = ?, max_allowed_sub_grps = ?, meet_frequency = ?, meet_place = ?, meet_time = ?, nxt_meet_date = ?, sol_id =?, lchg_user_id = ? where group_id  = ?";

        String val = AdminDb.getValue(s, 22, 1, String.valueOf(groupId));
        String[] vals = val.split("\\s*,\\s*");
        String in = "";
        for (int w = 0; w < 22; w++) {
            in = in + vals[w] + ",";
        }
        in = in + username + "," + groupId;
        AdminDb.dbWork(sql, 24, in);
    }

    private static String parseDates(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dateTime.toString(fmt);
    }

}
