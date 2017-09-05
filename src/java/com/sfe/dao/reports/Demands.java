package com.sfe.dao.reports;

public class Demands {

    String FORACID, ACCT_NAME, DMD_AMT, DMD_DATE, SUB_GROUP_CODE, START_DATE, END_DATE;

    public Demands(String FORACID, String ACCT_NAME, String DMD_AMT, String DMD_DATE, String SUB_GROUP_CODE, String START_DATE, String END_DATE) {
        this.ACCT_NAME = ACCT_NAME;
        this.DMD_AMT = DMD_AMT;
        this.DMD_DATE = DMD_DATE;
        this.FORACID = FORACID;
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getFORACID() {
        return FORACID;
    }

    public void setFORACID(String FORACID) {
        this.FORACID = FORACID;
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
