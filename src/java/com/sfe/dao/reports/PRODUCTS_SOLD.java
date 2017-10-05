/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfe.dao.reports;

/**
 *
 * @author Levi
 */
public class PRODUCTS_SOLD {

    String CUSTID, PROD, PRODCAT, AVEVAL, BMCOD, RMGCOD, CUSTYP, DATSOLD, WKSOLD, START_DATE, END_DATE;

    public PRODUCTS_SOLD() {
    }

    public PRODUCTS_SOLD(String CUSTID, String PROD, String PRODCAT, String AVEVAL, String BMCOD, String RMGCOD, String CUSTYP, String DATSOLD, String WKSOLD, String START_DATE, String END_DATE) {
        this.CUSTID = CUSTID;
        this.PROD = PROD;
        this.PRODCAT = PRODCAT;
        this.AVEVAL = AVEVAL;
        this.BMCOD = BMCOD;
        this.RMGCOD = RMGCOD;
        this.CUSTYP = CUSTYP;
        this.DATSOLD = DATSOLD;
        this.WKSOLD = WKSOLD;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public String getCUSTID() {
        return CUSTID;
    }

    public void setCUSTID(String CUSTID) {
        this.CUSTID = CUSTID;
    }

    public String getPROD() {
        return PROD;
    }

    public void setPROD(String PROD) {
        this.PROD = PROD;
    }

    public String getPRODCAT() {
        return PRODCAT;
    }

    public void setPRODCAT(String PRODCAT) {
        this.PRODCAT = PRODCAT;
    }

    public String getAVEVAL() {
        return AVEVAL;
    }

    public void setAVEVAL(String AVEVAL) {
        this.AVEVAL = AVEVAL;
    }

    public String getBMCOD() {
        return BMCOD;
    }

    public void setBMCOD(String BMCOD) {
        this.BMCOD = BMCOD;
    }

    public String getRMGCOD() {
        return RMGCOD;
    }

    public void setRMGCOD(String RMGCOD) {
        this.RMGCOD = RMGCOD;
    }

    public String getCUSTYP() {
        return CUSTYP;
    }

    public void setCUSTYP(String CUSTYP) {
        this.CUSTYP = CUSTYP;
    }

    public String getDATSOLD() {
        return DATSOLD;
    }

    public void setDATSOLD(String DATSOLD) {
        this.DATSOLD = DATSOLD;
    }

    public String getWKSOLD() {
        return WKSOLD;
    }

    public void setWKSOLD(String WKSOLD) {
        this.WKSOLD = WKSOLD;
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
