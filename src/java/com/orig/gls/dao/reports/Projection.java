/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.dao.reports;

/**
 *
 * @author Levi
 */
public class Projection {
    
    String CUST_ID,ACCT_NAME,COLLATERAL_FUND,DIS_AMT,VOLUNTARY_SAVING,DIS_DATE,OTHER_SAVING,GROUP_ID,GROUP_NAME,TRAN_DATE, START_DATE, END_DATE;

    public Projection(String CUST_ID, String ACCT_NAME, String COLLATERAL_FUND, String DIS_AMT, String VOLUNTARY_SAVING, String DIS_DATE, String OTHER_SAVING, String GROUP_ID, String GROUP_NAME, String TRAN_DATE, String START_DATE, String END_DATE) {
        this.CUST_ID = CUST_ID;
        this.ACCT_NAME = ACCT_NAME;
        this.COLLATERAL_FUND = COLLATERAL_FUND;
        this.DIS_AMT = DIS_AMT;
        this.VOLUNTARY_SAVING = VOLUNTARY_SAVING;
        this.DIS_DATE = DIS_DATE;
        this.OTHER_SAVING = OTHER_SAVING;
        this.GROUP_ID = GROUP_ID;
        this.GROUP_NAME = GROUP_NAME;
        this.TRAN_DATE = TRAN_DATE;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getCUST_ID() {
        return CUST_ID;
    }

    public void setCUST_ID(String CUST_ID) {
        this.CUST_ID = CUST_ID;
    }

    public String getACCT_NAME() {
        return ACCT_NAME;
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

    public void setACCT_NAME(String ACCT_NAME) {
        this.ACCT_NAME = ACCT_NAME;
    }

    public String getCOLLATERAL_FUND() {
        return COLLATERAL_FUND;
    }

    public void setCOLLATERAL_FUND(String COLLATERAL_FUND) {
        this.COLLATERAL_FUND = COLLATERAL_FUND;
    }

    public String getDIS_AMT() {
        return DIS_AMT;
    }

    public void setDIS_AMT(String DIS_AMT) {
        this.DIS_AMT = DIS_AMT;
    }

    public String getVOLUNTARY_SAVING() {
        return VOLUNTARY_SAVING;
    }

    public void setVOLUNTARY_SAVING(String VOLUNTARY_SAVING) {
        this.VOLUNTARY_SAVING = VOLUNTARY_SAVING;
    }

    public String getDIS_DATE() {
        return DIS_DATE;
    }

    public void setDIS_DATE(String DIS_DATE) {
        this.DIS_DATE = DIS_DATE;
    }

    public String getOTHER_SAVING() {
        return OTHER_SAVING;
    }

    public void setOTHER_SAVING(String OTHER_SAVING) {
        this.OTHER_SAVING = OTHER_SAVING;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public String getTRAN_DATE() {
        return TRAN_DATE;
    }

    public void setTRAN_DATE(String TRAN_DATE) {
        this.TRAN_DATE = TRAN_DATE;
    }
    
}
