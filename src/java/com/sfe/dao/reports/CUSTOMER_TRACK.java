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
public class CUSTOMER_TRACK {

    String NAME, CLIENT_CONTACTED, SALES_COMMITMENT, DOCS_SUBMITTED, CLOSED, HIT_RATE, LEAD_RATE, START_DATE, END_DATE;

    public CUSTOMER_TRACK() {
    }

    public CUSTOMER_TRACK(String NAME, String CLIENT_CONTACTED, String SALES_COMMITMENT, String DOCS_SUBMITTED, String CLOSED, String HIT_RATE, String LEAD_RATE, String START_DATE, String END_DATE) {
        this.NAME = NAME;
        this.CLIENT_CONTACTED = CLIENT_CONTACTED;
        this.SALES_COMMITMENT = SALES_COMMITMENT;
        this.DOCS_SUBMITTED = DOCS_SUBMITTED;
        this.CLOSED = CLOSED;
        this.HIT_RATE = HIT_RATE;
        this.LEAD_RATE = LEAD_RATE;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
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

    public String getHIT_RATE() {
        return HIT_RATE;
    }

    public void setHIT_RATE(String HIT_RATE) {
        this.HIT_RATE = HIT_RATE;
    }

    public String getLEAD_RATE() {
        return LEAD_RATE;
    }

    public void setLEAD_RATE(String LEAD_RATE) {
        this.LEAD_RATE = LEAD_RATE;
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
