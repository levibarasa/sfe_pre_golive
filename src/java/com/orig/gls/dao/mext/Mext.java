package com.orig.gls.dao.mext;

import com.orig.common.methods.CommonMethods;
import com.orig.gls.conn.AdminDb;
import com.orig.gls.dao.customer.Customer;
import java.util.ArrayList;

public class Mext {

    // Current Account Details
    public static ArrayList getAllMembers(String foracid, String mStatus) {
        String sql = "select acct_name, sol_id, sub_group_code, credit_rating from general_acct_mast_table where cust_id = ? and member_status = ? and mapped_flg = ?";
        String in = foracid + "," + mStatus + ",Y";
        ArrayList ar = AdminDb.execArrayLists(sql, 3, in, 4);
        int k = Integer.parseInt(foracid);
        ArrayList arr = new ArrayList();
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            ArrayList one = new ArrayList();
            one.add(foracid);
            one.add((String) hs.get(0));
            one.add(Customer.getlinkedAccounts("GLS%", k));
            one.add(Customer.getlinkedAccounts("SBGCO", k));
            one.add(Customer.getlinkedAccounts("SBGLS", k));
            one.add((String) hs.get(1));
            one.add((String) hs.get(2));
            one.add(Customer.getSubGroupName((String) hs.get(2)));//5
            one.add((String) hs.get(3));
            arr.add(one);
        }
        return arr;
    }
//    public static void main(String[] args) {
//    ArrayList all = getAllMembers("37022", "A");
//    int size = all.size(); 
//    String custId="";
//    String acctName, savingsAcnt, loanAcnt, solId, subgroupCode, subgroupName, acctType, operAcnt;
//    acctName = savingsAcnt = loanAcnt = solId = subgroupCode = subgroupName = acctType = operAcnt = "";
//        //System.out.println(""+all);
//        for (int i = 0; i < size; i++) {
//       ArrayList one = (ArrayList) all.get(0); 
//       custId = (String) one.get(0);
//        acctName = (String) one.get(1);
//        savingsAcnt = (String) one.get(3);
//        operAcnt = (String) one.get(4);
//        loanAcnt = (String) one.get(2);
//        solId = (String) one.get(5);
//        subgroupCode = (String) one.get(6);
//        subgroupName = (String) one.get(7);
//        acctType = (String) one.get(8);
//            System.out.println(" id:"+custId+"\n accName: "+acctName+"\n operAcc:"+operAcnt+"\n loanAcc:"+loanAcnt+"\n saveAcc:"+savingsAcnt+"\n subgrpname:"+subgroupName+"\n");
//    }
//    }
    
    public static boolean computeBeforeExit(String custId, String uname) {
        if (CommonMethods.getMemberLoans(custId)) {
            exitMember(custId, uname);
            return true;
        } else {
            return false;
        }
    }
    public static String getSubGrCode(String custId) { 
      String s = "select sub_group_code from general_acct_mast_table where cust_id = ?";
        String r = AdminDb.getValue(s, 1, 1, custId);
        return r;
    }

    public static int getSubGrpCode(String custId) { 
        int subGrpId =0;
        try{
         String r =getSubGrCode(custId);
        String sql = "select GROUP_ID from SUB_GRP_TABLE where SUB_GROUP_CODE = ?";
        String re = AdminDb.getValue(sql, 1, 1,r);
        subGrpId = Integer.parseInt(re);
        }
        catch(NumberFormatException ex){
            ex.printStackTrace();
        }
        return subGrpId;
    }
//    public static void main(String[] args) {
//        System.out.println(""+getSubGrpCode("37022"));
//    }
    
    public static void exitMember(String custId, String uname) { 
        String sql = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OPN_DATE,"
                + "ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,"
                + "FORACID,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID,LIEN_AMT,RCRE_TIME,RCRE_USER_ID,"
                + "SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,sub_group_code from general_acct_mast_table where cust_id = ?";
        String str = AdminDb.getValue(sql, 24, 1, custId);
        String[] args = str.split("\\s*,\\s*");
         
        String s = "insert into general_acct_mast_table_mod(ACID,ACCT_CRNCY_CODE,"
                + "ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OPN_DATE,ACCT_OWNERSHIP,BANK_ID,"
                + "CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,"
                + "LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID,LIEN_AMT,RCRE_TIME,"
                + "RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,SUB_GROUP_CODE,LAST_OPER) values"
                + "(?,?,?,?,try_convert(date, ?, 111),?,?,?,?,?,?,?,?,try_convert(date, ?, 111),"
                + "try_convert(date, ?, 111),?,?,try_convert(date, ?, 111),?,?,?,?,?,?,?)";
        String in = "";
        for (int w = 0; w < 24; w++) {
            in = in + args[w] + ",";
        }
        in = in + "D";
        int n = AdminDb.dbWork(s, 25, in);
        if (n > 0) {
            markExited(custId);  
        } 
    }

    public static void markExited(String in) {
        String sql = "update general_acct_mast_table set member_status = ? where cust_id = ?";
        String ins = "E," + in;
        AdminDb.dbWork(sql, 2, ins);
    }
    // Get subgroup name    
}
