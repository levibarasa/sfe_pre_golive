package com.orig.gls.dao.loan;

import com.orig.gls.conn.AdminDb;
import com.orig.gls.prop.GlsProp;
import java.math.BigDecimal;
import java.util.ArrayList; 

public class LoanMapping {

        public static ArrayList getAllAccountsMappedtoSubGroup(String subgroupCode, String fromDate, String toDate) {
        String sql = "select foracid, acct_name, dis_amt, num_advance_instlmnt, rephasement_principal, upfront_instl_amt, dis_shdl_date from loan_disbursement_report where"
                + " sub_group_code = ? and dis_shdl_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111) ";
        String in = subgroupCode + "," + fromDate + "," + toDate;
        return AdminDb.execArrayLists(sql, 3, in, 7);
    }

    public static ArrayList getAlldemandsSubGroup(String subgroupCode, String fromDate, String toDate) {
        String sql = "select  distinct foracid, acct_name, sum(dmd_amt)dmd_amt from loan_demands_report where sub_group_code = ? and dmd_date between try_convert(date, ?, 111)  and try_convert(date, ?, 111)  group by foracid,dmd_amt, acct_name";
        String in = subgroupCode + "," + fromDate + "," + toDate;
        return AdminDb.execArrayLists(sql, 3, in, 3);
    }
//   public static void main(String[] args) {
//            
//       ArrayList all = getAlldemandsSubGroup("WES2010","2010-01-01","2019-01-01");
//                for (int i = 0; i < all.size(); i++) {
//                        ArrayList list = (ArrayList) all.get(i);
//                        
//                        String acid = (String) list.get(0);
//                        String name = (String) list.get(1);
//                        BigDecimal dmdAmt = new BigDecimal(""+list.get(2)+"");
//                        System.out.println(acid+" "+name+" "+dmdAmt);
//                }
//                 
//    }
   
    public static ArrayList getAccountMappedtoSubGroup(String subgroupCode) {
        GlsProp pr = new GlsProp();
        String sql = "select foracid, acct_name from general_acct_mast_table where sub_group_code = ? and schm_code <> ?";
        String schm = pr.getDBProperty().getProperty("operative.schmecode");
        String in = subgroupCode + "," + schm;
        return AdminDb.execArrayLists(sql, 2, in, 2);
    }
}
