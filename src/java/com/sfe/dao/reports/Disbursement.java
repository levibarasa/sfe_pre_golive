package com.sfe.dao.reports;

public class Disbursement {
//foracid,acct_name,acct_mgr_user_id, dis_amt ,dis_shdl_date

    String FORACID, ACCT_NAME, LOAN_SERIES, TERMS, DIS_AMT, DIS_SHL_DATE, INTERESTE_RATE, INSTALMENT_AMT, ACCT_MGR_OFFICER, APPROVED_BY, APPROVED_DATE, START_DATE, END_DATE;

    public Disbursement(String FORACID, String ACCT_NAME, String LOAN_SERIES, String TERMS, String DIS_AMT, String DIS_SHL_DATE, String INTERESTE_RATE, String INSTALMENT_AMT, String ACCT_MGR_OFFICER, String APPROVED_BY, String APPROVED_DATE, String START_DATE, String END_DATE) {
        this.FORACID = FORACID;
        this.ACCT_NAME = ACCT_NAME;
        this.LOAN_SERIES = LOAN_SERIES;
        this.TERMS = TERMS;
        this.DIS_AMT = DIS_AMT;
        this.DIS_SHL_DATE = DIS_SHL_DATE;
        this.INTERESTE_RATE = INTERESTE_RATE;
        this.INSTALMENT_AMT = INSTALMENT_AMT;
        this.ACCT_MGR_OFFICER = ACCT_MGR_OFFICER;
        this.APPROVED_BY = APPROVED_BY;
        this.APPROVED_DATE = APPROVED_DATE;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
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

    public String getLOAN_SERIES() {
        return LOAN_SERIES;
    }

    public void setLOAN_SERIES(String LOAN_SERIES) {
        this.LOAN_SERIES = LOAN_SERIES;
    }

    public String getTERMS() {
        return TERMS;
    }

    public void setTERMS(String TERMS) {
        this.TERMS = TERMS;
    }

    public String getDIS_AMT() {
        return DIS_AMT;
    }

    public void setDIS_AMT(String DIS_AMT) {
        this.DIS_AMT = DIS_AMT;
    }

    public String getDIS_SHL_DATE() {
        return DIS_SHL_DATE;
    }

    public void setDIS_SHL_DATE(String DIS_SHL_DATE) {
        this.DIS_SHL_DATE = DIS_SHL_DATE;
    }

    public String getINTERESTE_RATE() {
        return INTERESTE_RATE;
    }

    public void setINTERESTE_RATE(String INTERESTE_RATE) {
        this.INTERESTE_RATE = INTERESTE_RATE;
    }

    public String getINSTALMENT_AMT() {
        return INSTALMENT_AMT;
    }

    public void setINSTALMENT_AMT(String INSTALMENT_AMT) {
        this.INSTALMENT_AMT = INSTALMENT_AMT;
    }

    public String getACCT_MGR_OFFICER() {
        return ACCT_MGR_OFFICER;
    }

    public void setACCT_MGR_OFFICER(String ACCT_MGR_OFFICER) {
        this.ACCT_MGR_OFFICER = ACCT_MGR_OFFICER;
    }

    public String getAPPROVED_BY() {
        return APPROVED_BY;
    }

    public void setAPPROVED_BY(String APPROVED_BY) {
        this.APPROVED_BY = APPROVED_BY;
    }

    public String getAPPROVED_DATE() {
        return APPROVED_DATE;
    }

    public void setAPPROVED_DATE(String APPROVED_DATE) {
        this.APPROVED_DATE = APPROVED_DATE;
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

}
