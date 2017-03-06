package com.orig.gls.dao.reports;

public class Demands {

    String FORACID, ACCT_NAME, DMD_AMT, DMD_DATE, SUB_GROUP_CODE;

    public Demands(String FORACID, String ACCT_NAME, String DMD_AMT, String DMD_DATE, String SUB_GROUP_CODE) {
        this.ACCT_NAME = ACCT_NAME;
        this.DMD_AMT = DMD_AMT;
        this.DMD_DATE = DMD_DATE;
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

    public String getDMD_AMT() {
        return DMD_AMT;
    }

    public void setDMD_AMT(String DMD_AMT) {
        this.DMD_AMT = DMD_AMT;
    }

    public String getDMD_DATE() {
        return DMD_DATE;
    }

    public void setDMD_DATE(String DMD_DATE) {
        this.DMD_DATE = DMD_DATE;
    }

    public String getSUB_GROUP_CODE() {
        return SUB_GROUP_CODE;
    }

    public void setSUB_GROUP_CODE(String SUB_GROUP_CODE) {
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

}
