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
public class PERFORMANCE {
    String CLIENT_CONTACTED,SALES_COMMITMENT,DOCS_SUBMITTED,CLOSED,HITRATE,RM_CODE,START_DATE,END_DATE;

    public PERFORMANCE() {
    }

    public PERFORMANCE(String CLIENT_CONTACTED, String SALES_COMMITMENT, String DOCS_SUBMITTED, String CLOSED, String HITRATE, String RM_CODE, String START_DATE, String END_DATE) {
        this.CLIENT_CONTACTED = CLIENT_CONTACTED;
        this.SALES_COMMITMENT = SALES_COMMITMENT;
        this.DOCS_SUBMITTED = DOCS_SUBMITTED;
        this.CLOSED = CLOSED;
        this.HITRATE = HITRATE;
        this.RM_CODE = RM_CODE;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public String getCLIENT_CONTACTED() {
        return CLIENT_CONTACTED;
    }

    public void setCLIENT_CONTACTED(String CLIENT_CONTACTED) {
        this.CLIENT_CONTACTED = CLIENT_CONTACTED;
    }

    public String getSALES_COMMITMENT() {
        return SALES_COMMITMENT;
    }

    public void setSALES_COMMITMENT(String SALES_COMMITMENT) {
        this.SALES_COMMITMENT = SALES_COMMITMENT;
    }

    public String getDOCS_SUBMITTED() {
        return DOCS_SUBMITTED;
    }

    public void setDOCS_SUBMITTED(String DOCS_SUBMITTED) {
        this.DOCS_SUBMITTED = DOCS_SUBMITTED;
    }

    public String getCLOSED() {
        return CLOSED;
    }

    public void setCLOSED(String CLOSED) {
        this.CLOSED = CLOSED;
    }

    public String getHITRATE() {
        return HITRATE;
    }

    public void setHITRATE(String HITRATE) {
        this.HITRATE = HITRATE;
    }

    public String getRM_CODE() {
        return RM_CODE;
    }

    public void setRM_CODE(String RM_CODE) {
        this.RM_CODE = RM_CODE;
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
