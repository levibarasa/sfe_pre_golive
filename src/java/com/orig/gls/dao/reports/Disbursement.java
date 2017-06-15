package com.orig.gls.dao.reports;

public class Disbursement {
//foracid,acct_name,acct_mgr_user_id, dis_amt ,dis_shdl_date
    String FORACID,ACCT_NAME,ACCT_MGR_USER_ID, DIS_AMT, DIS_SHDL_DATE, SUB_GROUP_CODE, START_DATE, END_DATE;

    public Disbursement(String FORACID, String ACCT_NAME, String ACCT_MGR_USER_ID, String DIS_AMT, String DIS_SHDL_DATE, String SUB_GROUP_CODE, String START_DATE, String END_DATE) {
        this.FORACID = FORACID;
        this.ACCT_NAME = ACCT_NAME;
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
        this.DIS_AMT = DIS_AMT;
        this.DIS_SHDL_DATE = DIS_SHDL_DATE;
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getFORACID() {
        return FORACID;
    }

    public String getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(String START_DATE) {
        this.START_DATE = START_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public void setFORACID(String FORACID) {
        this.FORACID = FORACID;
    }

    public String getACCT_NAME() {
        return ACCT_NAME;
    }

    public void setACCT_NAME(String ACCT_NAME) {
        this.ACCT_NAME = ACCT_NAME;
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
