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
public class RetentionRate {

    String ACCT_MGR_USER_ID, ACTIVE_MEMBERS, REIN, EXITS, RETEN_RATE, START_DATE, END_DATE;

    public RetentionRate(String ACCT_MGR_USER_ID, String ACTIVE_MEMBERS, String REIN, String EXITS, String RETEN_RATE, String START_DATE, String END_DATE) {
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
        this.ACTIVE_MEMBERS = ACTIVE_MEMBERS;
        this.REIN = REIN;
        this.EXITS = EXITS;
        this.RETEN_RATE = RETEN_RATE;
        this.END_DATE = END_DATE;
        this.START_DATE = START_DATE;
    }

    public String getACCT_MGR_USER_ID() {
        return ACCT_MGR_USER_ID;
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

    public void setACCT_MGR_USER_ID(String ACCT_MGR_USER_ID) {
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
    }

    public String getACTIVE_MEMBERS() {
        return ACTIVE_MEMBERS;
    }

    public void setACTIVE_MEMBERS(String ACTIVE_MEMBERS) {
        this.ACTIVE_MEMBERS = ACTIVE_MEMBERS;
    }

    public String getREIN() {
        return REIN;
    }

    public void setREIN(String REIN) {
        this.REIN = REIN;
    }

    public String getEXITS() {
        return EXITS;
    }

    public void setEXITS(String EXITS) {
        this.EXITS = EXITS;
    }

    public String getRETEN_RATE() {
        return RETEN_RATE;
    }

    public void setRETEN_RATE(String RETEN_RATE) {
        this.RETEN_RATE = RETEN_RATE;
    }
}
