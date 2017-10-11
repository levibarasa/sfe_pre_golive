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
public class SALES {
 String  BRANCH_CODE,REGION,BRANCH,CLIENT_CONTACTED,SALES_COMMITMENT,DOCS_SUBMITTED,CLOSED,MARKETED_VALUE,
 CURRENCY_PRODUCT_VALUE,PRODUCT_SOLD,LEAD_SOURCE, CUSTOMER_TYPE,CUSTOMER_ID, HITRATE,RM_CODE, START_DATE, END_DATE;

    public SALES() {
    }

    public SALES(String BRANCH_CODE, String REGION, String BRANCH, String CLIENT_CONTACTED, String SALES_COMMITMENT, String DOCS_SUBMITTED, String CLOSED, String MARKETED_VALUE, String CURRENCY_PRODUCT_VALUE, String PRODUCT_SOLD, String LEAD_SOURCE, String CUSTOMER_TYPE, String CUSTOMER_ID, String HITRATE, String RM_CODE, String START_DATE, String END_DATE) {
        this.BRANCH_CODE = BRANCH_CODE;
        this.REGION = REGION;
        this.BRANCH = BRANCH;
        this.CLIENT_CONTACTED = CLIENT_CONTACTED;
        this.SALES_COMMITMENT = SALES_COMMITMENT;
        this.DOCS_SUBMITTED = DOCS_SUBMITTED;
        this.CLOSED = CLOSED;
        this.MARKETED_VALUE = MARKETED_VALUE;
        this.CURRENCY_PRODUCT_VALUE = CURRENCY_PRODUCT_VALUE;
        this.PRODUCT_SOLD = PRODUCT_SOLD;
        this.LEAD_SOURCE = LEAD_SOURCE;
        this.CUSTOMER_TYPE = CUSTOMER_TYPE;
        this.CUSTOMER_ID = CUSTOMER_ID;
        this.HITRATE = HITRATE;
        this.RM_CODE = RM_CODE;
        this.START_DATE = START_DATE;
        this.END_DATE = END_DATE;
    }

    public String getBRANCH_CODE() {
        return BRANCH_CODE;
    }

    public void setBRANCH_CODE(String BRANCH_CODE) {
        this.BRANCH_CODE = BRANCH_CODE;
    }

    public String getREGION() {
        return REGION;
    }

    public void setREGION(String REGION) {
        this.REGION = REGION;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
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

    public String getMARKETED_VALUE() {
        return MARKETED_VALUE;
    }

    public void setMARKETED_VALUE(String MARKETED_VALUE) {
        this.MARKETED_VALUE = MARKETED_VALUE;
    }

    public String getCURRENCY_PRODUCT_VALUE() {
        return CURRENCY_PRODUCT_VALUE;
    }

    public void setCURRENCY_PRODUCT_VALUE(String CURRENCY_PRODUCT_VALUE) {
        this.CURRENCY_PRODUCT_VALUE = CURRENCY_PRODUCT_VALUE;
    }

    public String getPRODUCT_SOLD() {
        return PRODUCT_SOLD;
    }

    public void setPRODUCT_SOLD(String PRODUCT_SOLD) {
        this.PRODUCT_SOLD = PRODUCT_SOLD;
    }

    public String getLEAD_SOURCE() {
        return LEAD_SOURCE;
    }

    public void setLEAD_SOURCE(String LEAD_SOURCE) {
        this.LEAD_SOURCE = LEAD_SOURCE;
    }

    public String getCUSTOMER_TYPE() {
        return CUSTOMER_TYPE;
    }

    public void setCUSTOMER_TYPE(String CUSTOMER_TYPE) {
        this.CUSTOMER_TYPE = CUSTOMER_TYPE;
    }

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
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
