package com.orig.gls.dao.reports;

public class Repayment {

    String FORACID, RCRE_USER_ID, LCHG_USER_ID, SUB_GROUP_CODE, TRAN_DATE, TRAN_AMT, START_DATE, END_DATE;

    public Repayment(String TRAN_AMT, String TRAN_DATE, String FORACID, String RCRE_USER_ID, String LCHG_USER_ID, String SUB_GROUP_CODE, String START_DATE, String END_DATE) {
        this.FORACID = FORACID;
        this.LCHG_USER_ID = LCHG_USER_ID;
        this.TRAN_AMT = TRAN_AMT;
        this.TRAN_DATE = TRAN_DATE;
        this.RCRE_USER_ID = RCRE_USER_ID;
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getTRAN_AMT() {
        return TRAN_AMT;
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

    public void setTRAN_AMT(String TRAN_AMT) {
        this.TRAN_AMT = TRAN_AMT;
    }

    public String getTRAN_DATE() {
        return TRAN_DATE;
    }

    public void setTRAN_DATE(String TRAN_DATE) {
        this.TRAN_DATE = TRAN_DATE;
    }

    public String getFORACID() {
        return FORACID;
    }

    public void setFORACID(String FORACID) {
        this.FORACID = FORACID;
    }

    public String getRCRE_USER_ID() {
        return RCRE_USER_ID;
    }

    public void setRCRE_USER_ID(String RCRE_USER_ID) {
        this.RCRE_USER_ID = RCRE_USER_ID;
    }

    public String getLCHG_USER_ID() {
        return LCHG_USER_ID;
    }

    public void setLCHG_USER_ID(String LCHG_USER_ID) {
        this.LCHG_USER_ID = LCHG_USER_ID;
    }

    public String getSUB_GROUP_CODE() {
        return SUB_GROUP_CODE;
    }

    public void setSUB_GROUP_CODE(String SUB_GROUP_CODE) {
        this.SUB_GROUP_CODE = SUB_GROUP_CODE;
    }

}
