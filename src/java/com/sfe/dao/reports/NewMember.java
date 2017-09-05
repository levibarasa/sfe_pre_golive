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
public class NewMember {

    String ACCT_MGR_USER_ID, GROUP_ID, GROUP_PRODUCT, NEW_MEMBERS, START_DATE, END_DATE;

    public NewMember(String ACCT_MGR_USER_ID, String GROUP_ID, String GROUP_PRODUCT, String NEW_MEMBERS, String START_DATE, String END_DATE) {
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
        this.GROUP_ID = GROUP_ID;
        this.GROUP_PRODUCT = GROUP_PRODUCT;
        this.NEW_MEMBERS = NEW_MEMBERS;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public String getACCT_MGR_USER_ID() {
        return ACCT_MGR_USER_ID;
    }

    public void setACCT_MGR_USER_ID(String ACCT_MGR_USER_ID) {
        this.ACCT_MGR_USER_ID = ACCT_MGR_USER_ID;
    }

    public String getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(String GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getGROUP_PRODUCT() {
        return GROUP_PRODUCT;
    }

    public void setGROUP_PRODUCT(String GROUP_PRODUCT) {
        this.GROUP_PRODUCT = GROUP_PRODUCT;
    }

    public String getNEW_MEMBERS() {
        return NEW_MEMBERS;
    }

    public void setNEW_MEMBERS(String NEW_MEMBERS) {
        this.NEW_MEMBERS = NEW_MEMBERS;
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
