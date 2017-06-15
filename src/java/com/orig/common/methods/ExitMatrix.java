package com.orig.common.methods;

import static com.orig.common.methods.CommonMethods.getGroupSize;
import com.orig.gls.conn.AdminDb;
import com.orig.gls.dao.tran.ProcessTran;
import com.orig.gls.dao.tran.Transact;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class ExitMatrix {

    public static void main(String[] args) {
        System.out.println(doExit("001SIN0001000135", "000037043", "55486.26", "MARK"));

    }

    public static String doExit(String loanAccount, String custId, String loanAmount, String uname) {
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
            Random random = new Random();
            int randomInt = random.nextInt(1000);
            String runNu = String.format("%03d", randomInt);
            String bankTranId = "PBUG" + runNu;
            BigDecimal bal = BigDecimal.ZERO;
            if (amt.compareTo(amts) == 1) {
                if (postExitMember((String) one.get(0), loanAccount, String.valueOf(finalAmt), "LOAN REPAY AC NO " + loanAccount + " OWNER")) {
                    paidAmt = paidAmt.add(repayment);
                    ownerAmt = paidAmt;
                     Transact.createTransactionHistory(ownerAmt, (String) one.get(0), "LOAN REPAY AC NO " + loanAccount + " OWNER", uname, uname, "N", "N", "C", bankTranId, (String) one.get(2));
                    BigDecimal curClrBal = CommonMethods.getMemberSaving(custId);
                    bal = curClrBal.subtract(ownerAmt);
                    CommonMethods.updateMemberLoanAccBal(custId);
                    CommonMethods.updateMemberSavingAccBal(custId, bal);

                    if (paidAmt.compareTo(amts) == -1) {
                        BigDecimal subAmt = amts.subtract(paidAmt);
                        BigDecimal subgroupAmt = postExitTransactionsSubgroup((String) one.get(2), subAmt, loanAccount, (String) one.get(0));
                        subgrpAmt = subgroupAmt;
                        BigDecimal zero = BigDecimal.ZERO;
                        BigDecimal indDeduct = BigDecimal.ZERO;
                        BigDecimal indGrpDeduct = BigDecimal.ZERO;
                        String subgroupSize = CommonMethods.getSubGroupSize(custId);
                        BigDecimal sbgrpSize = new BigDecimal(subgroupSize);
                        if (subgrpAmt.compareTo(zero) == 1) {
                        Transact.createTransactionHistory(subgrpAmt, (String) one.get(0), "LOAN REPAY AC NO " + loanAccount + " OWNER", uname, uname, "N", "N", "C", bankTranId, (String) one.get(2));
                            indDeduct = subgrpAmt.divide(sbgrpSize);
                            CommonMethods.updateMemberLoanAccBal(custId);
                            CommonMethods.updateSbGrpSavings(custId, (String) one.get(2), indDeduct);
                        }
                        if (subgroupAmt.compareTo(subAmt) == -1) {
                            BigDecimal groupAmt = subAmt.subtract(subgroupAmt);
                            grpAmt = postExitTransactionsMainGroup((String) one.get(2), groupAmt, loanAccount);
                            String groupSize = String.valueOf(getGroupSize(custId));
                            BigDecimal grpSize = new BigDecimal(groupSize);
                            indGrpDeduct = groupAmt.divide(grpSize);

                            if (grpAmt.compareTo(zero) == 1) {
                                Transact.createTransactionHistory(grpAmt, (String) one.get(0), "LOAN REPAY AC NO " + loanAccount + " OWNER", uname, uname, "N", "N", "C", bankTranId, (String) one.get(2));
                                CommonMethods.updateMemberLoanAccBal(custId);
                                //update group members
                                CommonMethods.updateGrpMemberSaving(custId, indGrpDeduct);
                            }
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
        String sql = "select foracid, clr_bal_amt from general_acct_mast_table where sub_group_code = ? and cast(clr_bal_amt as decimal(10,2)) > 0 and foracid <> ?";
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
        String sql = "select foracid, clr_bal_amt, sub_group_code from general_acct_mast_table where sub_group_code in (select sub_group_code from sub_grp_table where group_id  = (select group_id from sub_grp_table where sub_group_code = ?)) and cast(clr_bal_amt as decimal(10,2)) > 0 and sub_group_code <> ?";
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
