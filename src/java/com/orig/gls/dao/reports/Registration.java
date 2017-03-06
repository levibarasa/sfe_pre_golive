package com.orig.gls.dao.reports;

public class Registration {

    String ACCT_NAME, CLR_BAL_AMT, CUST_ID, FORACID, SCHM_CODE, SOL_ID, SUB_GROUP_CODE;

    public Registration(String ACCT_NAME, String CLR_BAL_AMT, String CUST_ID, String FORACID, String SCHM_CODE, String SOL_ID, String SUB_GROUP_CODE) {
        this.ACCT_NAME = ACCT_NAME;
        this.CLR_BAL_AMT = CLR_BAL_AMT;
        this.CUST_ID = CUST_ID;
        this.FORACID = FORACID;
        this.SCHM_CODE = SCHM_CODE;
        this.SOL_ID = SOL_ID;
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

    public String getACCT_NAME() {
        return ACCT_NAME;
    }

    public void setACCT_NAME(String ACCT_NAME) {
        this.ACCT_NAME = ACCT_NAME;
    }

    public String getCLR_BAL_AMT() {
        return CLR_BAL_AMT;
    }

    public void setCLR_BAL_AMT(String CLR_BAL_AMT) {
        this.CLR_BAL_AMT = CLR_BAL_AMT;
    }

    public String getCUST_ID() {
        return CUST_ID;
    }

    public void setCUST_ID(String CUST_ID) {
        this.CUST_ID = CUST_ID;
    }

    public String getFORACID() {
        return FORACID;
    }

    public void setFORACID(String FORACID) {
        this.FORACID = FORACID;
    }

    public String getSCHM_CODE() {
        return SCHM_CODE;
    }

    public void setSCHM_CODE(String SCHM_CODE) {
        this.SCHM_CODE = SCHM_CODE;
    }

    public String getSOL_ID() {
        return SOL_ID;
    }

    public void setSOL_ID(String SOL_ID) {
        this.SOL_ID = SOL_ID;
    }

    public String getSUB_GROUP_CODE() {
        return SUB_GROUP_CODE;
    }

    public void setSUB_GROUP_CODE(String SUB_GROUP_CODE) {
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

}
