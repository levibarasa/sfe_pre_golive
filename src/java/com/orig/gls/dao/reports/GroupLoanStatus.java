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
public class GroupLoanStatus {
    String FORACID,ACCT_NAME,PHONE,FOR_ACID,SAVINGS,DIS_AMT,LOAN_INTEREST,LOAN_TOTAL, DIS_DATE,DUE_PRINCIPAL,DUE_INTEREST,DUE_TOTAL,PAID_PRINCIPAL,PAID_INTEREST, PAID_TOTAL,PREPAYMENT,ARREAR,LB_PRINCIPAL,LB_INTEREST,LB_TOTAL,BRANCH,SUBGROUPCODE, START_DATE, END_DATE;

    public GroupLoanStatus(String FORACID, String ACCT_NAME, String PHONE, String FOR_ACID, String SAVINGS, String DIS_AMT, String LOAN_INTEREST, String LOAN_TOTAL, String DIS_DATE, String DUE_PRINCIPAL, String DUE_INTEREST, String DUE_TOTAL, String PAID_PRINCIPAL, String PAID_INTEREST, String PAID_TOTAL, String PREPAYMENT, String ARREAR, String LB_PRINCIPAL, String LB_INTEREST, String LB_TOTAL, String BRANCH, String SUBGROUPCODE, String START_DATE, String END_DATE) {
        this.FORACID = FORACID;
        this.ACCT_NAME = ACCT_NAME;
        this.PHONE = PHONE;
        this.FOR_ACID = FOR_ACID;
        this.SAVINGS = SAVINGS;
        this.DIS_AMT = DIS_AMT;
        this.LOAN_INTEREST = LOAN_INTEREST;
        this.LOAN_TOTAL = LOAN_TOTAL;
        this.DIS_DATE = DIS_DATE;
        this.DUE_PRINCIPAL = DUE_PRINCIPAL;
        this.DUE_INTEREST = DUE_INTEREST;
        this.DUE_TOTAL = DUE_TOTAL;
        this.PAID_PRINCIPAL = PAID_PRINCIPAL;
        this.PAID_INTEREST = PAID_INTEREST;
        this.PAID_TOTAL = PAID_TOTAL;
        this.PREPAYMENT = PREPAYMENT;
        this.ARREAR = ARREAR;
        this.LB_PRINCIPAL = LB_PRINCIPAL;
        this.LB_INTEREST = LB_INTEREST;
        this.LB_TOTAL = LB_TOTAL;
        this.BRANCH = BRANCH;
        this.SUBGROUPCODE = SUBGROUPCODE;
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

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getFOR_ACID() {
        return FOR_ACID;
    }

    public void setFOR_ACID(String FOR_ACID) {
        this.FOR_ACID = FOR_ACID;
    }

    public String getSAVINGS() {
        return SAVINGS;
    }

    public void setSAVINGS(String SAVINGS) {
        this.SAVINGS = SAVINGS;
    }

    public String getDIS_AMT() {
        return DIS_AMT;
    }

    public void setDIS_AMT(String DIS_AMT) {
        this.DIS_AMT = DIS_AMT;
    }

    public String getLOAN_INTEREST() {
        return LOAN_INTEREST;
    }

    public void setLOAN_INTEREST(String LOAN_INTEREST) {
        this.LOAN_INTEREST = LOAN_INTEREST;
    }

    public String getLOAN_TOTAL() {
        return LOAN_TOTAL;
    }

    public void setLOAN_TOTAL(String LOAN_TOTAL) {
        this.LOAN_TOTAL = LOAN_TOTAL;
    }

    public String getDIS_DATE() {
        return DIS_DATE;
    }

    public void setDIS_DATE(String DIS_DATE) {
        this.DIS_DATE = DIS_DATE;
    }

    public String getDUE_PRINCIPAL() {
        return DUE_PRINCIPAL;
    }

    public void setDUE_PRINCIPAL(String DUE_PRINCIPAL) {
        this.DUE_PRINCIPAL = DUE_PRINCIPAL;
    }

    public String getDUE_INTEREST() {
        return DUE_INTEREST;
    }

    public void setDUE_INTEREST(String DUE_INTEREST) {
        this.DUE_INTEREST = DUE_INTEREST;
    }

    public String getDUE_TOTAL() {
        return DUE_TOTAL;
    }

    public void setDUE_TOTAL(String DUE_TOTAL) {
        this.DUE_TOTAL = DUE_TOTAL;
    }

    public String getPAID_PRINCIPAL() {
        return PAID_PRINCIPAL;
    }

    public void setPAID_PRINCIPAL(String PAID_PRINCIPAL) {
        this.PAID_PRINCIPAL = PAID_PRINCIPAL;
    }

    public String getPAID_INTEREST() {
        return PAID_INTEREST;
    }

    public void setPAID_INTEREST(String PAID_INTEREST) {
        this.PAID_INTEREST = PAID_INTEREST;
    }

    public String getPAID_TOTAL() {
        return PAID_TOTAL;
    }

    public void setPAID_TOTAL(String PAID_TOTAL) {
        this.PAID_TOTAL = PAID_TOTAL;
    }

    public String getPREPAYMENT() {
        return PREPAYMENT;
    }

    public void setPREPAYMENT(String PREPAYMENT) {
        this.PREPAYMENT = PREPAYMENT;
    }

    public String getARREAR() {
        return ARREAR;
    }

    public void setARREAR(String ARREAR) {
        this.ARREAR = ARREAR;
    }

    public String getLB_PRINCIPAL() {
        return LB_PRINCIPAL;
    }

    public void setLB_PRINCIPAL(String LB_PRINCIPAL) {
        this.LB_PRINCIPAL = LB_PRINCIPAL;
    }

    public String getLB_INTEREST() {
        return LB_INTEREST;
    }

    public void setLB_INTEREST(String LB_INTEREST) {
        this.LB_INTEREST = LB_INTEREST;
    }

    public String getLB_TOTAL() {
        return LB_TOTAL;
    }

    public void setLB_TOTAL(String LB_TOTAL) {
        this.LB_TOTAL = LB_TOTAL;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getSUBGROUPCODE() {
        return SUBGROUPCODE;
    }

    public void setSUBGROUPCODE(String SUBGROUPCODE) {
        this.SUBGROUPCODE = SUBGROUPCODE;
    }


}
