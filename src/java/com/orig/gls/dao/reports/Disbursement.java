package com.orig.gls.dao.reports;

public class Disbursement {
//acct_mgr_user_id, dis_amt ,dis_shdl_date
    String ACCT_MGR_USER_ID, DIS_AMT, DIS_SHDL_DATE, SUB_GROUP_CODE;

    public Disbursement(String ACCT_MGR_USER_ID, String DIS_AMT, String DIS_SHDL_DATE, String SUB_GROUP_CODE) {
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
        this.DIS_AMT = DIS_AMT;
        this.DIS_SHDL_DATE = DIS_SHDL_DATE; 
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

     public String getACCT_MGR_USER_ID() {
        return ACCT_MGR_USER_ID;
    }

    public void setACCT_MGR_USER_ID(String ACCT_MGR_USER_ID) {
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
    }

    public String getDIS_AMT() {
        return DIS_AMT;
    }

    public void setDIS_AMT(String DIS_AMT) {
        this.DIS_AMT = DIS_AMT;
    }

    public String getDIS_SHDL_DATE() {
        return DIS_SHDL_DATE;
    }

    public void setDIS_SHDL_DATE(String DIS_SHDL_DATE) {
        this.DIS_SHDL_DATE = DIS_SHDL_DATE;
    }

    public String getSUB_GROUP_CODE() {
        return SUB_GROUP_CODE;
    }

    public void setSUB_GROUP_CODE(String SUB_GROUP_CODE) {
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

}
