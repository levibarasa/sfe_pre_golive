/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orig.gls.dao.reports;

/**
 *
 * @author Wachira
 */
public class OfficerMonitoring {
    String BRANCH, OFFICER, NO_OF_CASES, PORTFOLIO, PAR_AMOUNT, PAR_PER, NPA, NPA_PER, DISB, NO_OF_CASES_DISB, START_DATE, END_DATE;

public OfficerMonitoring(String BRANCH, String OFFICER, String NO_OF_CASES, String PORTFOLIO, String PAR_AMOUNT, String PAR_PER, String NPA, String NPA_PER, String DISB, String NO_OF_CASES_DISB, String START_DATE, String END_DATE){

    this.BRANCH = BRANCH;
    this.OFFICER = OFFICER;
    this.NO_OF_CASES = NO_OF_CASES;
    this.PORTFOLIO = PORTFOLIO;
    this.PAR_AMOUNT = PAR_AMOUNT;
    this.PAR_PER = PAR_PER;
    this.NPA = NPA;
    this.NPA_PER = NPA_PER;
    this.DISB = DISB;
    this.NO_OF_CASES_DISB = NO_OF_CASES_DISB;
    this.END_DATE = END_DATE;
    this.START_DATE = START_DATE;
}

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getOFFICER() {
        return OFFICER;
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

    public void setOFFICER(String OFFICER) {
        this.OFFICER = OFFICER;
    }

    public String getNO_OF_CASES() {
        return NO_OF_CASES;
    }

    public void setNO_OF_CASES(String NO_OF_CASES) {
        this.NO_OF_CASES = NO_OF_CASES;
    }

    public String getPORTFOLIO() {
        return PORTFOLIO;
    }

    public void setPORTFOLIO(String PORTFOLIO) {
        this.PORTFOLIO = PORTFOLIO;
    }

    public String getPAR_AMOUNT() {
        return PAR_AMOUNT;
    }

    public void setPAR_AMOUNT(String PAR_AMOUNT) {
        this.PAR_AMOUNT = PAR_AMOUNT;
    }

    public String getPAR_PER() {
        return PAR_PER;
    }

    public void setPAR_PER(String PAR_PER) {
        this.PAR_PER = PAR_PER;
    }

    public String getNPA() {
        return NPA;
    }

    public void setNPA(String NPA) {
        this.NPA = NPA;
    }

    public String getNPA_PER() {
        return NPA_PER;
    }

    public void setNPA_PER(String NPA_PER) {
        this.NPA_PER = NPA_PER;
    }

    public String getDISB() {
        return DISB;
    }

    public void setDISB(String DISB) {
        this.DISB = DISB;
    }

    public String getNO_OF_CASES_DISB() {
        return NO_OF_CASES_DISB;
    }

    public void setNO_OF_CASES_DISB(String NO_OF_CASES_DISB) {
        this.NO_OF_CASES_DISB = NO_OF_CASES_DISB;
    }

    
}