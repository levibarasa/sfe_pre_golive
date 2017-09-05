/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfe.dao.reports;

/**
 *
 * @author wachira
 */
public class ChargeOff {

    String FORACID, ACCT_NAME, SCHM_CODE, DIS_AMT, DIS_SHDL_DATE, CHRGE_OFF_PRINCIPAL, PENDING_INTEREST, TOTAL, LOAN_DUE_DAYS, CHRGE_OFF_DATE, START_DATE, END_DATE;

    public ChargeOff(String FORACID, String ACCT_NAME, String SCHM_CODE, String DIS_AMT, String DIS_SHDL_DATE, String CHRGE_OFF_PRINCIPAL, String PENDING_INTEREST, String TOTAL, String LOAN_DUE_DAYS, String CHRGE_OFF_DATE, String START_DATE, String END_DATE) {
        this.FORACID = FORACID;
        this.ACCT_NAME = ACCT_NAME;
        this.SCHM_CODE = SCHM_CODE;
        this.DIS_AMT = DIS_AMT;
        this.DIS_SHDL_DATE = DIS_SHDL_DATE;
        this.CHRGE_OFF_PRINCIPAL = CHRGE_OFF_PRINCIPAL;
        this.PENDING_INTEREST = PENDING_INTEREST;
        this.TOTAL = TOTAL;
        this.LOAN_DUE_DAYS = LOAN_DUE_DAYS;
        this.CHRGE_OFF_DATE = CHRGE_OFF_DATE;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
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

    public String getSCHM_CODE() {
        return SCHM_CODE;
    }

    public void setSCHM_CODE(String SCHM_CODE) {
        this.SCHM_CODE = SCHM_CODE;
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

    public String getCHRGE_OFF_PRINCIPAL() {
        return CHRGE_OFF_PRINCIPAL;
    }

    public void setCHRGE_OFF_PRINCIPAL(String CHRGE_OFF_PRINCIPAL) {
        this.CHRGE_OFF_PRINCIPAL = CHRGE_OFF_PRINCIPAL;
    }

    public String getPENDING_INTEREST() {
        return PENDING_INTEREST;
    }

    public void setPENDING_INTEREST(String PENDING_INTEREST) {
        this.PENDING_INTEREST = PENDING_INTEREST;
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

    public String getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL) {
        this.TOTAL = TOTAL;
    }

    public String getLOAN_DUE_DAYS() {
        return LOAN_DUE_DAYS;
    }

    public void setLOAN_DUE_DAYS(String LOAN_DUE_DAYS) {
        this.LOAN_DUE_DAYS = LOAN_DUE_DAYS;
    }

    public String getCHRGE_OFF_DATE() {
        return CHRGE_OFF_DATE;
    }

    public void setCHRGE_OFF_DATE(String CHRGE_OFF_DATE) {
        this.CHRGE_OFF_DATE = CHRGE_OFF_DATE;
    }
}
