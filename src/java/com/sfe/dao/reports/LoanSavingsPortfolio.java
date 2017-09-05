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
public class LoanSavingsPortfolio {

    String BRANCH_ID, CREDIT_OFFICER_NAME, DISBURSED_AMOUNT, OS_PRINCIPAL, TOTAL_SAVINGS, END_DATE, START_DATE;

    public LoanSavingsPortfolio(String BRANCH_ID, String CREDIT_OFFICER_NAME, String DISBURSED_AMOUNT, String OS_PRINCIPAL, String TOTAL_SAVINGS, String START_DATE, String END_DATE) {
        this.BRANCH_ID = BRANCH_ID;
        this.CREDIT_OFFICER_NAME = CREDIT_OFFICER_NAME;
        this.DISBURSED_AMOUNT = DISBURSED_AMOUNT;
        this.OS_PRINCIPAL = OS_PRINCIPAL;
        this.TOTAL_SAVINGS = TOTAL_SAVINGS;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getSTART_DATE() {
        return START_DATE;
    }

    public void setSTART_DATE(String START_DATE) {
        this.START_DATE = START_DATE;
    }

    public String getBRANCH_ID() {
        return BRANCH_ID;
    }

    public void setBRANCH_ID(String BRANCH_ID) {
        this.BRANCH_ID = BRANCH_ID;
    }

    public String getCREDIT_OFFICER_NAME() {
        return CREDIT_OFFICER_NAME;
    }

    public void setCREDIT_OFFICER_NAME(String CREDIT_OFFICER_NAME) {
        this.CREDIT_OFFICER_NAME = CREDIT_OFFICER_NAME;
    }

    public String getDISBURSED_AMOUNT() {
        return DISBURSED_AMOUNT;
    }

    public void setDISBURSED_AMOUNT(String DISBURSED_AMOUNT) {
        this.DISBURSED_AMOUNT = DISBURSED_AMOUNT;
    }

    public String getOS_PRINCIPAL() {
        return OS_PRINCIPAL;
    }

    public void setOS_PRINCIPAL(String OS_PRINCIPAL) {
        this.OS_PRINCIPAL = OS_PRINCIPAL;
    }

    public String getTOTAL_SAVINGS() {
        return TOTAL_SAVINGS;
    }

    public void setTOTAL_SAVINGS(String TOTAL_SAVINGS) {
        this.TOTAL_SAVINGS = TOTAL_SAVINGS;
    }

}
