package com.sfe.common.methods;

import com.sfe.conn.AdminDb;
import com.sfe.dao.tran.Transact;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CommonMethods {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static ArrayList getMemberDetails(String acid) {
        String sql = "select acct_name, acid, bank_id, cust_id, acct_crncy_code, foracid, acct_ownership, del_flg, schm_code from general_acct_mast_table where acid = ?";
        return AdminDb.execArrayLists(sql, 1, acid, 9);
    }
//get clear balance

    public static String getClearBalanceAmt(String cust_Id) {
        String sql = "select  cast(CLR_BAL_AMT as decimal(15,2))  from general_acct_mast_table where cust_id = ?  and SCHM_CODE LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_Id);
        BigDecimal amts = new BigDecimal(amt).abs();
        return amts.toString();
    }

    public static double getClearBalance(String cust_Id) {
        double custId = 0.0;
        String sql = "select SUM(cast(CLR_BAL_AMT as decimal(15,2))) from general_acct_mast_table where cust_id = ?  and SCHM_CODE LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_Id);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

    public static double getMemberOtherSavings(String cust_id) {
        double custId = 0.0;
        String sql = "select sum(cast(CLR_BAL_AMT as decimal(15,2)))AMT from general_acct_mast_table where cust_id = ?  and SCHM_CODE NOT LIKE 'GLS%' and SCHM_CODE NOT LIKE 'SBGCO'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_id);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

    public static double getMemberSavings(String cust_id) {
        double custId = 0.0;
        String sql = "select sum(cast(CLR_BAL_AMT as decimal(15,2)))AMT from general_acct_mast_table where cust_id = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_id);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

    public static BigDecimal getMemberSaving(String cust_id) {
        BigDecimal custId = BigDecimal.ZERO;
        String sql = "select sum(cast(CLR_BAL_AMT as decimal(15,2)))AMT from general_acct_mast_table where cust_id = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_id);
        custId = new BigDecimal(amt);
        return custId;
    }

    public static String getLoanAcc(String cust_id) {
        String sql = "select foracid from general_acct_mast_table where cust_id = ?  and SCHM_CODE LIKE 'GLS%'";
        String loanAcc = AdminDb.getValue(sql, 1, 1, cust_id);
        return loanAcc;
    }

    public static String getSubGrpCode(String cust_id) {
        String sql = "select SUB_GROUP_CODE from general_acct_mast_table where cust_id = ?";
        String subGrpCode = AdminDb.getValue(sql, 1, 1, cust_id);
        return subGrpCode;
    }

    public static double getSubgroupSavings(String cust_id) {
        double custId = 0.0;
        String subgroupCode = getSubGrpCode(cust_id);
        String sql = "select sum(cast(CLR_BAL_AMT as decimal(15,2)))AMT from general_acct_mast_table where SUB_GROUP_CODE = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, subgroupCode);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

//get no of individual savings accounts
    public static int getNoOfSavingAccs(String cust_id) {
        String sql = "select count(*)CNT from general_acct_mast_table where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        String str = AdminDb.getValue(sql, 1, 1, cust_id);
        return Integer.parseInt(str);
    }
    //get a list of  Individual Member ClrBalAmt

    public static ArrayList getIndividualMemberClrBalAmt(String cust_id) {
        String sql = "select cast(CLR_BAL_AMT as decimal(15,2)) from general_acct_mast_table where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0.00 and SCHM_CODE NOT LIKE 'GLS%'";
        return AdminDb.execArrayLists(sql, 1, cust_id, 1);
    }

    /*
    check member saving accs, subtract loan from svings, update saving acc balance
    
     */
    //amt,trnDate,
    public static double updateIndividualMemberClrBalAmt(String cust_id, double newClearBal, String uname) {
        ArrayList currentClearBalance = getIndividualMemberClrBalAmt(cust_id);
        String f_Date = parseDates(new Date());
        Date fDate = new Date();
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fDate = fmt.parse(f_Date);

        } catch (ParseException ex) {
            Logger.getLogger(CommonMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        String subgroup = getSubGrpCode(cust_id);
        int lastId = Transact.getLastInsertId();
        String bankTranId = "PBUG" + lastId;
        String acnt = Transact.getDebitAccountNumber(subgroup);
        for (int i = 0; i < currentClearBalance.size(); i++) {
            ArrayList one = (ArrayList) currentClearBalance.get(i);
            double clrBal = Double.parseDouble((String) one.get(0));
            newClearBal = (clrBal - newClearBal);
            if (newClearBal >= 0.00) {
                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT = ? where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;
                AdminDb.dbWork(sql, 3, in);

            } else {

                newClearBal = newClearBal - clrBal;
                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT = ? where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;
                AdminDb.dbWork(sql, 3, in);

                newClearBal = (-1) * newClearBal;
            }
        }
        return newClearBal;
    }

    public static void updateSubGrpMemberClrBalAmt(String cust_id, double newClearBal, String uname) {
        ArrayList currentClearBalance = new ArrayList();//getSubGrpMemberClrBalAmt(cust_id);
        String f_Date = parseDates(new Date());
        Date fDate = new Date();
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fDate = fmt.parse(f_Date);

        } catch (ParseException ex) {
            Logger.getLogger(CommonMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        String subgroup = getSubGrpCode(cust_id);
        int lastId = Transact.getLastInsertId();
        String bankTranId = "PBUG" + lastId;
        String acnt = Transact.getDebitAccountNumber(subgroup);
        for (int i = 0; i < currentClearBalance.size(); i++) {
            ArrayList one = (ArrayList) currentClearBalance.get(i);
            double clrBal = Double.parseDouble((String) one.get(0));
            newClearBal = clrBal - newClearBal;
            if (clrBal >= 0 && newClearBal >= 0) {

                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;

                AdminDb.dbWork(sql, 3, in);

            }
        }
    }

    /*
 get groupId,
 get sbgrpcodes by grpId
 update member savings bal by sbgrpcode
     */
    public static ArrayList getSubGrpClrBalAmt(String subgroupCode) {
        String sql = "select cast(CLR_BAL_AMT as decimal(15,2)) from general_acct_mast_table where SUB_GROUP_CODE = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        return AdminDb.execArrayLists(sql, 1, subgroupCode, 1);
    }

//public static void main(String[] args) {
//     ArrayList subgroupCodes =getSubGroupCodesByGroupId("000037026");
//     for (int i = 0; i < subgroupCodes.size(); i++) {
//            ArrayList one = (ArrayList) subgroupCodes.get(i);
//            String subgroupCode = (String) one.get(0);
//             System.out.println(subgroupCode);
//        }
//        
//    }
    public static void updateGrpMemberClrBalAmt(String cust_id, double newClearBal, String uname) {
        ArrayList currentClearBalance = new ArrayList();//getGrpMemberClrBalAmt(cust_id);
        String f_Date = parseDates(new Date());
        Date fDate = new Date();
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            fDate = fmt.parse(f_Date);

        } catch (ParseException ex) {
            Logger.getLogger(CommonMethods.class.getName()).log(Level.SEVERE, null, ex);
        }

        String subgroup = getSubGrpCode(cust_id);
        int lastId = Transact.getLastInsertId();
        String bankTranId = "PBUG" + lastId;
        String acnt = Transact.getDebitAccountNumber(subgroup);
        for (int i = 0; i < currentClearBalance.size(); i++) {
            ArrayList one = (ArrayList) currentClearBalance.get(i);
            double clrBal = Double.parseDouble((String) one.get(0));
            newClearBal = clrBal - newClearBal;
            if (clrBal >= 0 && newClearBal >= 0) {

                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;
                AdminDb.dbWork(sql, 3, in);

            }
        }
    }

    public static String getSubGroupSize(String cust_id) {
        String subgroupCode = getSubGrpCode(cust_id);
        String sql = "select count(*)CNT from general_acct_mast_table where SUB_GROUP_CODE = ?";
        String str = AdminDb.getValue(sql, 1, 1, subgroupCode);
        return str;
    }

    //get group name
    public static String getGrpCode(String subgroupCode) {
        int groupId = getGroupId(subgroupCode);
        String sql = "select GROUP_NAME from GROUPS_TABLE where GROUP_ID = ?";
        String grpId = String.valueOf(groupId);
        String subGrpCode = AdminDb.getValue(sql, 1, 1, grpId);
        return subGrpCode;
    }

//get group code
    public static int getGroupId(String subgroupCode) {
        String sql = "select GROUP_ID from SUB_GRP_TABLE where SUB_GROUP_CODE =?";
        String grpId = AdminDb.getValue(sql, 1, 1, subgroupCode);
        return Integer.parseInt(grpId);
    }

    public static int getGroupIdBySubGrpCode(String cust_id) {
        String subgroupCode = getSubGrpCode(cust_id);
        String sql = "select GROUP_ID from SUB_GRP_TABLE where SUB_GROUP_CODE =?";
        String grpId = AdminDb.getValue(sql, 1, 1, subgroupCode);
        return Integer.parseInt(grpId);
    }

    public static int getSubGrpSize(String subgroupCode) {
        String sql = "select count(*)CNT from general_acct_mast_table where SUB_GROUP_CODE = ?";
        String str = AdminDb.getValue(sql, 1, 1, subgroupCode);
        return Integer.parseInt(str);
    }

    public static ArrayList getSubGroupCodesByGroupId(String cust_id) {
        int groupId = getGroupIdBySubGrpCode(cust_id);
        String sql = "select SUB_GROUP_CODE from SUB_GRP_TABLE where GROUP_ID =?";
        String grpId = String.valueOf(groupId);
        return AdminDb.execArrayLists(sql, 1, grpId, 1);
    }

    public static int getGroupSize(String cust_id) {
        ArrayList ar = getSubGroupCodesByGroupId(cust_id);
        int noOfMembers = 0;
        for (int i = 0; i < ar.size(); i++) {
            ArrayList one = (ArrayList) ar.get(i);
            int sbSize = getSubGrpSize((String) one.get(0));
            noOfMembers += sbSize;
        }
        return noOfMembers;
    }

    public static double getSubgroupSaving(String subgroupCode) {
        double custId = 0.0;
        String sql = "select sum(cast(CLR_BAL_AMT as decimal(15,2)))AMT from general_acct_mast_table where SUB_GROUP_CODE = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, subgroupCode);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

    public static void updateMemberLoanAccBal(String cust_id) {
        String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT = ? where CUST_ID = ?  and SCHM_CODE LIKE ?";
        String schemeCode = "GLS%";
        double newClearBal = 0.00;
        String in = newClearBal + "," + cust_id + "," + schemeCode;
        AdminDb.dbWork(sql, 3, in);
    }

    public static double getGroupMembersSavings(String cust_id) {
        ArrayList ar = getSubGroupCodesByGroupId(cust_id);
        double custId = 0;
        for (int i = 0; i < ar.size(); i++) {
            ArrayList one = (ArrayList) ar.get(i);
            String subgroupCode = (String) one.get(0);
            if (subgroupCode != null) {
                custId = custId + getSubgroupSaving(subgroupCode);

            }
        }
        return custId;
    }

    public static boolean memberCleared(double loanAmount) {
        boolean cleared = false;
        if (loanAmount >= 0.00) {
            cleared = true;
        }
        return cleared;
    }

    public static boolean clearLoanBeforeExit(String cust_id, String uname) {
        boolean exitMember = false;
        String loanAmount = getClearBalanceAmt(cust_id);
        String loanAccount = getLoanAcc(cust_id);
        ExitMatrix.doExit(loanAccount, cust_id, loanAmount, uname);
        exitMember = true;
        return exitMember;
    }

    public static boolean getMemberLoans(String acid, String uname) {
        boolean exit = false;
        boolean cleared = false;
        cleared = clearLoanBeforeExit(acid, uname);
        if (cleared) {
            exit = true;
        }
        return exit;
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

    private static String parseDates(Date date) {
        DateTime dateTime = new DateTime(date);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return dateTime.toString(fmt);
    }

    public static String getAccMgrs(String accMgr) {
        String sql = " select  count(ACCT_MGR_USER_ID)  from GROUP_USER_REPORT where ACCT_MGR_USER_ID =?  ";
        String str = AdminDb.getValue(sql, 1, 1, accMgr);
        return str;
    }

    // getSubGrpMemberClrBalAmt
    public static ArrayList getSubGrpMemberClrBalAmt(String subgroupCode) {
        String sql = "select cast(CLR_BAL_AMT as decimal(15,2)) from general_acct_mast_table where SUB_GROUP_CODE = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        return AdminDb.execArrayLists(sql, 1, subgroupCode, 1);
    }

    public static void updateMemberSavingAccBal(String cust_id, BigDecimal bal) {
        String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT = ? where CUST_ID = ? and cast(clr_bal_amt as decimal(10,2)) > 0.00  and SCHM_CODE NOT LIKE ?";
        String schemeCode = "GLS%";
        String in = bal + "," + cust_id + "," + schemeCode;
        AdminDb.dbWork(sql, 3, in);

    }

    public static void updateSbGrpSavings(String custId, String subgroupCode, BigDecimal indDeduct) {
        ArrayList clearBalArr = getSubGrpMemberClrBalAmt(subgroupCode);
        BigDecimal curClrBal = BigDecimal.ZERO;
        BigDecimal bal = BigDecimal.ZERO;
        BigDecimal zero = BigDecimal.ZERO;
        for (int i = 0; i < clearBalArr.size(); i++) {
            ArrayList one = (ArrayList) clearBalArr.get(i);
            curClrBal = new BigDecimal((String) one.get(0));
            bal = curClrBal.subtract(indDeduct);
            if (zero.compareTo(bal) == 1) {
                //update clr bal to zero
                bal = BigDecimal.ZERO;
            }
            String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0  and SCHM_CODE NOT LIKE ?";
            String schemeCode = "GLS%";
            String in = bal + "," + custId + "," + schemeCode;
            AdminDb.dbWork(sql, 3, in);
        }
    }

    // update Grp Member ClrBalAmt
    public static void updateGrpMemberSaving(String custId, BigDecimal indDeduct) {
        ArrayList subgroupCodes = getSubGroupCodesByGroupId(custId);
        BigDecimal curClrBal = BigDecimal.ZERO;
        BigDecimal bal = BigDecimal.ZERO;
        BigDecimal zero = BigDecimal.ZERO;
        for (int i = 0; i < subgroupCodes.size(); i++) {
            ArrayList one = (ArrayList) subgroupCodes.get(i);
            String subgroupCode = (String) one.get(0);
            ArrayList clearBalArr = getSubGrpMemberClrBalAmt(subgroupCode);
            for (int j = 0; j < clearBalArr.size(); j++) {
                ArrayList two = (ArrayList) clearBalArr.get(j);
                curClrBal = new BigDecimal((String) two.get(0));
                bal = curClrBal.subtract(indDeduct);
                if (zero.compareTo(bal) == 1) {
                    //update clr bal to zero
                    bal = BigDecimal.ZERO;
                }
                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and cast(CLR_BAL_AMT as decimal(15,2)) > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = bal + "," + custId + "," + schemeCode;
                AdminDb.dbWork(sql, 3, in);
            }

        }

    }
}
