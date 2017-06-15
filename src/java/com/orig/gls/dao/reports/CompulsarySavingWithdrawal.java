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
public class CompulsarySavingWithdrawal {
    String SAVING_ACCOUNT,ACCT_NAME,LOAN_SERIES,DIS_AMT,SAVING_BAL,SAVING_WITHDRAW, START_DATE, END_DATE;

    public CompulsarySavingWithdrawal(String SAVING_ACCOUNT, String ACCT_NAME, String LOAN_SERIES, String DIS_AMT, String SAVING_BAL, String SAVING_WITHDRAW, String START_DATE, String END_DATE) {
        this.SAVING_ACCOUNT = SAVING_ACCOUNT;
        this.ACCT_NAME = ACCT_NAME;
        this.LOAN_SERIES = LOAN_SERIES;
        this.DIS_AMT = DIS_AMT;
        this.SAVING_BAL = SAVING_BAL;
        this.SAVING_WITHDRAW = SAVING_WITHDRAW;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getSAVING_ACCOUNT() {
        return SAVING_ACCOUNT;
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

    public void setSAVING_ACCOUNT(String SAVING_ACCOUNT) {
        this.SAVING_ACCOUNT = SAVING_ACCOUNT;
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

    public String getDIS_AMT() {
        return DIS_AMT;
    }

    public void setDIS_AMT(String DIS_AMT) {
        this.DIS_AMT = DIS_AMT;
    }

    public String getSAVING_BAL() {
        return SAVING_BAL;
    }

    public void setSAVING_BAL(String SAVING_BAL) {
        this.SAVING_BAL = SAVING_BAL;
    }

    public String getSAVING_WITHDRAW() {
        return SAVING_WITHDRAW;
    }

    public void setSAVING_WITHDRAW(String SAVING_WITHDRAW) {
        this.SAVING_WITHDRAW = SAVING_WITHDRAW;
    }
    
}
