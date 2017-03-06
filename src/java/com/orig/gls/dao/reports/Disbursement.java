package com.orig.gls.dao.reports;

public class Disbursement {

    String FORACID, ACCT_NAME, DIS_AMT, DIS_SHDL_DATE, SUB_GROUP_CODE;

    public Disbursement(String FORACID, String ACCT_NAME, String DIS_AMT, String DIS_SHDL_DATE, String SUB_GROUP_CODE) {
        this.ACCT_NAME = ACCT_NAME;
        this.DIS_AMT = DIS_AMT;
        this.DIS_SHDL_DATE = DIS_SHDL_DATE;
        this.FORACID = FORACID;
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

    public String getFORACID() {
        return FORACID;
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
