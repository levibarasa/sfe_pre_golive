package com.sfe.common.methods;

import com.sfe.conn.AdminDb;
import com.sfe.dao.tran.ProcessTran;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class Exit_Matrix {

    public static String doExit(String loanAccount, String custId, String loanAmount) {
        String sql = "select foracid, clr_bal_amt, sub_group_code from general_acct_mast_table where cust_id = ? and cast(clr_bal_amt as decimal(10,2)) > 0.00";
        ArrayList arr = AdminDb.execArrayLists(sql, 1, custId, 3);
        BigDecimal amts = new BigDecimal(loanAmount);
        BigDecimal paidAmt = BigDecimal.ZERO;
        BigDecimal ownerAmt = BigDecimal.ZERO;
        BigDecimal subgrpAmt = BigDecimal.ZERO;
        BigDecimal grpAmt = BigDecimal.ZERO;
        for (int k = 0; k < arr.size(); k++) {
            ArrayList one = (ArrayList) arr.get(k);
            BigDecimal amt = new BigDecimal((String) one.get(1));
            BigDecimal repayment;
            if (amt.compareTo(amts) == 1) {
                repayment = amts;
            } else {
                repayment = amt;
            }

            BigInteger finalAmt = repayment.toBigInteger();
            System.out.println("");
            if (amt.compareTo(amts) == 1) {
                if (postExitMember((String) one.get(0), loanAccount, String.valueOf(finalAmt), "LOAN REPAY AC NO " + loanAccount + " OWNER")) {
                    paidAmt = paidAmt.add(repayment);
                    ownerAmt = paidAmt;
                    if (paidAmt.compareTo(amts) == -1) {
                        BigDecimal subAmt = amts.subtract(paidAmt);
                        BigDecimal subgroupAmt = postExitTransactionsSubgroup((String) one.get(2), subAmt, loanAccount, (String) one.get(0));
                        subgrpAmt = subgroupAmt;
                        if (subgroupAmt.compareTo(subAmt) == -1) {
                            BigDecimal groupAmt = subAmt.subtract(subgroupAmt);
                            grpAmt = postExitTransactionsMainGroup((String) one.get(2), groupAmt, loanAccount);
                        }
                    }
                }
            }
        }
        return "Owner payment " + ownerAmt + " sub group payment " + subgrpAmt + " group payment " + grpAmt;
    }

    public static boolean postExitMember(String compSavings, String loanAccount, String amount, String tranpsrts) {
        ProcessTran post = new ProcessTran();
        String resp = post.postTran(compSavings, loanAccount, amount, tranpsrts);
        return resp.equalsIgnoreCase("000");
    }

    public static BigDecimal postExitTransactionsSubgroup(String accountNo, BigDecimal amount, String loanAccount, String ownerAccount) {
        String sql = "select foracid, clr_bal_amt from general_acct_mast_table where sub_group_code = ? and cast(clr_bal_amt as decimal(10,2)) > 0.00 and foracid <> ?";
        String in = accountNo + "," + ownerAccount;
        ArrayList arr = AdminDb.execArrayLists(sql, 2, in, 2);
        int members = arr.size();
        BigDecimal paidAmt = BigDecimal.ZERO;
        for (int k = 0; k < arr.size(); k++) {
            ArrayList one = (ArrayList) arr.get(k);
            BigDecimal repayAmt = amount.divide(new BigDecimal(members));
            BigDecimal amt = new BigDecimal((String) one.get(1));
            BigDecimal repayment;
            if (repayAmt.compareTo(amt) == 1) {
                repayment = amt;
            } else {
                repayment = repayAmt;
            }
            BigInteger finalAmt = repayment.toBigInteger();
            if (postExitMember((String) one.get(0), loanAccount, String.valueOf(finalAmt), "LOAN REPAY AC NO " + loanAccount + " SUB GROUP " + accountNo)) {
                paidAmt = paidAmt.add(repayment);
            }
        }
        return paidAmt;
    }

    public static BigDecimal postExitTransactionsMainGroup(String accountNo, BigDecimal amount, String loanAccount) {
        String sql = "select foracid, clr_bal_amt, sub_group_code from general_acct_mast_table where sub_group_code in (select sub_group_code from sub_grp_table where group_id  = (select group_id from sub_grp_table where sub_group_code = ?)) and cast(clr_bal_amt as decimal(10,2)) > 0.00 and sub_group_code <> ?";
        String in = accountNo + "," + accountNo;
        ArrayList arr = AdminDb.execArrayLists(sql, 2, in, 3);
        int members = arr.size();
        BigDecimal paidAmt = BigDecimal.ZERO;
        for (int k = 0; k < arr.size(); k++) {
            ArrayList one = (ArrayList) arr.get(k);
            BigDecimal repayAmt = amount.divide(new BigDecimal(members));
            BigDecimal amt = new BigDecimal((String) one.get(1));
            BigDecimal repayment;
            if (repayAmt.compareTo(amt) == 1) {
                repayment = amt;
            } else {
                repayment = repayAmt;
            }
            BigInteger finalAmt = repayment.toBigInteger();
            if (postExitMember((String) one.get(0), loanAccount, String.valueOf(finalAmt), "LOAN REPAY AC NO " + loanAccount + " SUB GROUP " + (String) one.get(2))) {
                paidAmt = paidAmt.add(repayment);
            }
        }
        return paidAmt;
    }
}
