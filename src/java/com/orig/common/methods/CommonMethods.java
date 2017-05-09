package com.orig.common.methods;

import com.orig.gls.conn.AdminDb;
import java.util.ArrayList;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class CommonMethods {

    private static final Log log = LogFactory.getLog("origlogger");

    public static ArrayList getMemberDetails(String acid) {
        String sql = "select acct_name, acid, bank_id, cust_id, acct_crncy_code, foracid, acct_ownership, del_flg, schm_code from general_acct_mast_table where acid = ?";
        return AdminDb.execArrayLists(sql, 1, acid, 9);
    }
//get clear balance

    public static double getClearBalance(String cust_Id) {
        double custId = 0.0;
        String sql = "select sum(CLR_BAL_AMT)AMT from general_acct_mast_table where cust_id = ?  and SCHM_CODE LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_Id);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }

    public static double getMemberSavings(String cust_id) {
        double custId = 0.0;
        String sql = "select sum(CLR_BAL_AMT)AMT from general_acct_mast_table where cust_id = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, cust_id);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
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
        String sql = "select sum(CLR_BAL_AMT)AMT from general_acct_mast_table where SUB_GROUP_CODE = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, subgroupCode);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
    }
//get no of individual savings accounts

    public static int getNoOfSavingAccs(String cust_id) {
        String sql = "select count(*)CNT from general_acct_mast_table where CUST_ID = ? and CLR_BAL_AMT > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        String str = AdminDb.getValue(sql, 1, 1, cust_id);
        return Integer.parseInt(str);
    }
    //get a list of  Individual Member ClrBalAmt

    public static ArrayList getIndividualMemberClrBalAmt(String cust_id) {
        String sql = "select CLR_BAL_AMT from general_acct_mast_table where CUST_ID = ? and CLR_BAL_AMT > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        return AdminDb.execArrayLists(sql, 1, cust_id, 1);
    }

    public static void updateIndividualMemberClrBalAmt(String cust_id, double newClearBal) {
        ArrayList currentClearBalance = getIndividualMemberClrBalAmt(cust_id);
        for (int i = 0; i < currentClearBalance.size(); i++) {
            ArrayList one = (ArrayList) currentClearBalance.get(i);
            double clrBal = Double.parseDouble((String) one.get(0));
            newClearBal = clrBal - newClearBal;
            if (clrBal >= 0 && newClearBal >= 0) {

                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and CLR_BAL_AMT > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;

                AdminDb.dbWork(sql, 3, in);

            }
        }
    }

    // getSubGrpMemberClrBalAmt
    public static ArrayList getSubGrpMemberClrBalAmt(String cust_id) {
        String subgroupCode = getSubGrpCode(cust_id);
        String sql = "select CLR_BAL_AMT from general_acct_mast_table where SUB_GROUP_CODE = ? and CLR_BAL_AMT > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        return AdminDb.execArrayLists(sql, 1, subgroupCode, 1);
    }

    public static void updateSubGrpMemberClrBalAmt(String cust_id, double newClearBal) {
        ArrayList currentClearBalance = getSubGrpMemberClrBalAmt(cust_id);
        for (int i = 0; i < currentClearBalance.size(); i++) {
            ArrayList one = (ArrayList) currentClearBalance.get(i);
            double clrBal = Double.parseDouble((String) one.get(0));
            newClearBal = clrBal - newClearBal;
            if (clrBal >= 0 && newClearBal >= 0) {

                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and CLR_BAL_AMT > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;

                AdminDb.dbWork(sql, 3, in);

            }
        }
    }

//    public static ArrayList getSubGrpClrBalAmt(String subgroupCode) {
//        String sql = "select CLR_BAL_AMT from general_acct_mast_table where SUB_GROUP_CODE = ? and CLR_BAL_AMT > 0 and SCHM_CODE NOT LIKE 'GLS%'";
//        String in = subgroupCode ;
//        return AdminDb.execArrayLists(sql, 1, in, 1);
//    }
    
public static ArrayList getSubGrpClrBalAmt(String subgroupCode) {
      //  String subgroupCode = getSubGrpCode(cust_id);
        String sql = "select CLR_BAL_AMT from general_acct_mast_table where SUB_GROUP_CODE = ? and CLR_BAL_AMT > 0 and SCHM_CODE NOT LIKE 'GLS%'";
        return AdminDb.execArrayLists(sql, 1, subgroupCode, 1);
    }
    
    // getn Grp Member ClrBalAmt
    public static ArrayList getGrpMemberClrBalAmt(String cust_id) {
        ArrayList subgroupCodes = getSubGroupCodesByGroupId(cust_id);

        ArrayList ar = new ArrayList();
        ArrayList rs = new ArrayList();
        for (int i = 0; i < subgroupCodes.size(); i++) {
            ArrayList one = (ArrayList) subgroupCodes.get(i);
            String subgroupCode = (String) one.get(0);
            if (subgroupCode != null) {
                ar =getSubGrpClrBalAmt(subgroupCode);
                for(int j=0;j<ar.size();j++){
               ArrayList ind = (ArrayList) ar.get(j);     
                    rs.add(ind);
                 }
            }
        }
        return rs;
    }
     
   
    public static void updateGrpMemberClrBalAmt(String cust_id, double newClearBal) {
        ArrayList currentClearBalance = getGrpMemberClrBalAmt(cust_id);
        for (int i = 0; i < currentClearBalance.size(); i++) {
            ArrayList one = (ArrayList) currentClearBalance.get(i);
            double clrBal = Double.parseDouble((String) one.get(0));
            newClearBal = clrBal - newClearBal;
            if (clrBal >= 0 && newClearBal >= 0) {

                String sql = "update GENERAL_ACCT_MAST_TABLE SET CLR_BAL_AMT= ? where CUST_ID = ? and CLR_BAL_AMT > 0  and SCHM_CODE NOT LIKE ?";
                String schemeCode = "GLS%";
                String in = newClearBal + "," + cust_id + "," + schemeCode;

                AdminDb.dbWork(sql, 3, in);

            }
        }
    }

    public static int getSubGroupSize(String cust_id) {
        String subgroupCode = getSubGrpCode(cust_id);
        String sql = "select count(*)CNT from general_acct_mast_table where SUB_GROUP_CODE = ?";
        String str = AdminDb.getValue(sql, 1, 1, subgroupCode);
        return Integer.parseInt(str);
    }
    //get group name
    public static String getGrpCode(String subgroupCode) {
        int groupId =getGroupId(subgroupCode) ;
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
        String sql = "select sum(CLR_BAL_AMT)AMT from general_acct_mast_table where SUB_GROUP_CODE = ?  and SCHM_CODE NOT LIKE 'GLS%'";
        String amt = AdminDb.getValue(sql, 1, 1, subgroupCode);
        if (amt != null) {
            custId = Double.parseDouble(amt);
        }
        return custId;
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

    public static boolean clearLoanBeforeExit(String cust_id) {
        
        boolean exitMember = false;
        if(cust_id != null || cust_id != ""){
            
        double loanAmount =  getClearBalance(cust_id);
        double sumOfSavings = getMemberSavings(cust_id); 
        double subgroupSavings = getSubgroupSavings(cust_id);
        
        int subgroupSize = getSubGroupSize(cust_id);  
        int groupSize = getGroupSize(cust_id);
        double totalGroupMembersSavings = getGroupMembersSavings(cust_id);
        int noOfIndividualMemberSavingAcc = getNoOfSavingAccs(cust_id);
        
        double newCustSavings = (sumOfSavings + loanAmount);
        double newSubgrpSavings = (subgroupSavings + loanAmount);
        double newGrpSavings = (totalGroupMembersSavings + loanAmount);
        //!dValue.isNaN()
          
        if (loanAmount >= 0.0) {
            exitMember = true;
        } else {
            //if member savings account is sufficient
            if (newCustSavings >= 0) {
                loanAmount = (loanAmount * -1);
                double indivSavingAcc = loanAmount / noOfIndividualMemberSavingAcc;
                updateIndividualMemberClrBalAmt(cust_id, indivSavingAcc);
                exitMember = true;
                //if subgroup savings account is sufficient
            } else if (newSubgrpSavings >= 0.0) {
                loanAmount = (loanAmount * -1);
                double indivSavingAcc = loanAmount / subgroupSize;
                updateSubGrpMemberClrBalAmt(cust_id, indivSavingAcc);
                exitMember = true;
                //if group savings account is sufficient
            } else if (newGrpSavings >= 0.0) {
                loanAmount = (loanAmount * -1);
                double indivSavingAcc = loanAmount / groupSize;
                 updateGrpMemberClrBalAmt(cust_id, indivSavingAcc);
                exitMember = true;
            }
         
        }
        }else{
        exitMember = false;
        }
        return exitMember;
    }

    public static boolean getMemberLoans(String acid) {
        boolean exit = false;
        boolean cleared = false;
        cleared = clearLoanBeforeExit(acid);
        if(cleared){
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
//    public static int daysBetween(Date d1, Date d2){
//             return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
//     }

}
