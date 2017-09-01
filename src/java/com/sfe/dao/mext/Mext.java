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
       // int k = Integer.parseInt(foracid);
        ArrayList arr = new ArrayList();
         String operAcc ="001";
         String loanAcc ="001";
         String saveAcc ="001";
         String  subGroupName =" ";
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            operAcc =Customer.getlinkedAccounts("GLS%", foracid);
            loanAcc = Customer.getlinkedAccounts("SBGCO", foracid);
            saveAcc = Customer.getlinkedAccounts("SBGLS", foracid);
            subGroupName = Customer.getSubGroupName((String) hs.get(2));
            ArrayList one = new ArrayList();
            one.add(foracid);
            one.add((String) hs.get(0));
            if(operAcc.isEmpty()){
            operAcc ="001";
            }
             if(loanAcc.isEmpty()){
            loanAcc ="001";
            }
             if(saveAcc.isEmpty()){
            saveAcc ="001";
            }
            one.add(operAcc);
            one.add(loanAcc);
            one.add(saveAcc);
            one.add((String) hs.get(1));
            one.add((String) hs.get(2));
            one.add(subGroupName);//5
            one.add((String) hs.get(3));
            arr.add(one);
        }
        return arr;
    }
    
      
//    public static void main(String[] args) {
//    ArrayList all = getAllMembers("000037026", "A");
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
//            System.out.println(" id:"+custId+"\n accName: "+acctName+"\n operAcc:"+operAcnt+"\n loanAcc:"+
//                    loanAcnt+"\n saveAcc:"+savingsAcnt+"\n subgrpname:"+subgroupName+"\n"
//            );
//    }
//    }
    
    /*
    
    
    boolean  computebefore = false;//computeBeforeExit(String custId, String uname);
    
    boolean hasLoan = CommonMethods.getMemberLoans(custId);
    if(hasLoan){
    int exitmem = exitMember(custId, uname);
    computebefore = true;
    if(exitmem >0){
    -markVoluntaryExit(custId); or 
    -markDecease(custId);   or
    -markExpelled(custId);
    }
    }
    */
    
    public static boolean computeBeforeExit(String custId, String uname, String mStatus) {
        if (CommonMethods.getMemberLoans(custId,uname)) {
            exitMember(custId, uname,mStatus);
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
    
    public static int exitMember(String custId, String uname,String mStatus) { 
        String sql = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OPN_DATE,"
                + "ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,"
                + "FORACID,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID,LIEN_AMT,RCRE_TIME,RCRE_USER_ID,"
                + "SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,SUB_GROUP_CODE  from general_acct_mast_table where cust_id = ?";
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
        in = in + mStatus;
//        int n = AdminDb.dbWork(s, 25, in);
//        if (n > 0) {
//            markExited(custId);  
//        } 
      return AdminDb.dbWork(s, 25, in);
    }
 
//    public static void main(String[] args) {
//        System.out.println(exitMember("000037036", "ELIUD","V"));
//    }
    
    //mark voluntary exit
    public static void markVoluntaryExit(String in) {
        String sql = "update general_acct_mast_table set member_status = ? where cust_id = ?";
        String ins = "V," + in;
        AdminDb.dbWork(sql, 2, ins);
    }
    //mark Dead member exit
    public static void markDeceased(String in) {
        String sql = "update general_acct_mast_table set member_status = ? where cust_id = ?";
        String ins = "D," + in;
        AdminDb.dbWork(sql, 2, ins);
    }
    //mark expulsion  
    public static void markExpelled(String in) {
        String sql = "update general_acct_mast_table set member_status = ? where cust_id = ?";
        String ins = "E," + in;
        AdminDb.dbWork(sql, 2, ins);
    }
}
