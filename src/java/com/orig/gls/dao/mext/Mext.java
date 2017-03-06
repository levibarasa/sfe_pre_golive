package com.orig.gls.dao.mext;

import com.orig.common.methods.CommonMethods;
import com.orig.gls.conn.AdminDb;
import com.orig.gls.dao.customer.Customer;
import java.util.ArrayList;

public class Mext {

    // Current Account Details
    public static ArrayList getAllMembers(String foracid, String mStatus) {
        String sql = "select acct_name, sol_id, sub_group_code, acct_type from general_acct_mast_table where cust_id = ? and member_status = ? and mapped_flg = ?";
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

    public static boolean computeBeforeExit(String custId, String uname) {
        if (CommonMethods.getMemberLoans(custId)) {
            exitMember(custId, uname);
            return true;
        } else {
            return false;
        }
    }

    public static int getSubGrpCode(String custId) {
        String s = "select sub_group_code from general_acct_mast_table where cust_id = ?";
        String r = AdminDb.getValue(s, 1, 1, custId);
        String sql = "select group_id from sub_grp_table where sub_group_code = ?";
        String re = AdminDb.getValue(sql, 1, 1, r);
        return Integer.parseInt(re);
    }

    public static void exitMember(String custId, String uname) {
        String sql = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OPN_DATE,ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID,LIEN_AMT,RCRE_TIME,RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID, group_id, sub_group_code from general_acct_mast_table where cust_id = ?";
        String str = AdminDb.getValue(sql, 25, 1, custId);
        String[] args = str.split("\\s*,\\s*");
        String s = "insert into general_acct_mast_table_mod(ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OPN_DATE,ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID,LIEN_AMT,RCRE_TIME,RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,GROUP_CODE,SUB_GROUP_CODE,MOD_ID,LAST_OPER) values"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,GLS_SEQ.NEXTVAL,?)";
        String in = "";
        for (int w = 0; w < 25; w++) {
            in = in + args[w] + ",";
        }
        in = in + "D";
        int n = AdminDb.dbWork(s, 26, in);
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
