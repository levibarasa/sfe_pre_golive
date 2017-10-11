package com.sfe.dao.reports;

import com.sfe.conn.AdminDb;
import com.sfe.dao.customer.Customer;
import java.util.Collection;
import java.util.ArrayList;

public class BeanFactory {
    
   public static Collection getSalesPerformance(String rmCode, String fromDate, String toDate) {
        String sql = "select  count(*)cont  from [dbo].[CUSTOMER_VIEW]  where RM_Code2 = ?   and FILLED_WEEK  between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 1);
        ArrayList collection = new ArrayList();
        String rmName = "null";
        String getClientContacted = "0";
        String getSaleCommited = "0";
        String getDocSubmitted = "0";
        String getClosed = "0";
        rmName = Customer.getRmName(rmCode);
        getClientContacted = getClientContacted(rmCode);
        getDocSubmitted = getDocSubmitted(rmCode);
        getSaleCommited = getSaleCommited(rmCode);
        getClosed = getClosed(rmCode);
        int contacted = Integer.parseInt(getClientContacted);
        int closed = Integer.parseInt(getClosed);
        String hitRate = "0";
        hitRate = getRate(contacted, closed);
        String leadRate = "0";
        leadRate = getRate(contacted, closed);

        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new PERFORMANCE( getClientContacted, getSaleCommited, getDocSubmitted, getClosed, hitRate, rmCode, fromDate, toDate));
        }
        return collection;
    }
    
      public static Collection getRMSaleByAllRegions( String fromDate, String toDate) {
       String rmCode ="";
       String BRANCH_CODE ="0";
       String REGION ="";
       String BRANCH ="";
       String custId ="0";
       String CLIENT_CONTACTED = "0";
       String SALES_COMMITMENT = "0";
       String DOCS_SUBMITTED = "0";
       String CLOSED = "0";
       String HITRATE = "0"; 
        ArrayList col = new ArrayList();
       ArrayList collection = new ArrayList();
       ArrayList rms =Customer.getRmsByAllRegions();
       
       for( int c = 0; c < rms.size(); c++){
           ArrayList two = (ArrayList) rms.get(c);
           rmCode = (String) two.get(0);
        BRANCH_CODE = getRMBranchCode(rmCode);
        REGION = getRMRegion(rmCode);
        BRANCH =getRMBranch(rmCode);
        String CLIENT_CONTACT = "0";
        String CLOSE = "0";
        CLIENT_CONTACT = getClientContact(rmCode);
        CLOSE = getClose(rmCode);
        HITRATE =  getRate(Integer.parseInt(CLIENT_CONTACT), Integer.parseInt(CLOSE));
        
        String sql = "select distinct Marketed_Value, Currency, Product_value,Product_Sold,Lead_Source, Customer_Type,RM_Code, Filled_Week,Customer_ID  from [dbo].[Customer_Tracking] where RM_Code = ? and Client_Contacted ='Yes' and Marketed_Value <> 'D' and Filled_Week between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
          col = AdminDb.execArrayLists(sql, 3, in, 9);
            }
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            custId = (String) one.get(8);
        CLIENT_CONTACTED = getClientContacted(rmCode,custId);
        DOCS_SUBMITTED = getDocSubmitted(rmCode,custId);
        SALES_COMMITMENT = getSaleCommited(rmCode,custId);
        CLOSED = getClosed(rmCode,custId);
            collection.add(new  SALES(BRANCH_CODE,REGION,BRANCH,CLIENT_CONTACTED,SALES_COMMITMENT,DOCS_SUBMITTED,CLOSED,(String) one.get(0), 
        (String) one.get(1) +". "+(String) one.get(2),(String) one.get(3),(String) one.get(4), (String) one.get(5), (String) one.get(8), HITRATE,rmCode, fromDate, toDate  
         ));
        }
        
       
        return collection;

    }
      
     public static Collection getRMSaleByRegion(String region, String fromDate, String toDate) {
       String rmCode ="";
       String BRANCH_CODE ="0";
       String REGION ="";
       String BRANCH ="";
       String custId ="0";
       String CLIENT_CONTACTED = "0";
       String SALES_COMMITMENT = "0";
       String DOCS_SUBMITTED = "0";
       String CLOSED = "0";
       String HITRATE = "0"; 
       ArrayList col = new ArrayList();
       ArrayList collection = new ArrayList();
       ArrayList rms =Customer.getRmsByRegion(region);
       
       for( int c = 0; c < rms.size(); c++){
           ArrayList two = (ArrayList) rms.get(c);
           rmCode = (String) two.get(0);
        BRANCH_CODE = getRMBranchCode(rmCode);
        REGION = getRMRegion(rmCode);
        BRANCH =getRMBranch(rmCode);
        String CLIENT_CONTACT = "0";
        String CLOSE = "0";
        CLIENT_CONTACT = getClientContact(rmCode);
        CLOSE = getClose(rmCode);
        HITRATE =  getRate(Integer.parseInt(CLIENT_CONTACT), Integer.parseInt(CLOSE));
        
        String sql = "select distinct Marketed_Value, Currency, Product_value,Product_Sold,Lead_Source, Customer_Type,RM_Code, Filled_Week,Customer_ID  from [dbo].[Customer_Tracking] where RM_Code = ? and Client_Contacted ='Yes' and Marketed_Value <> 'D' and Filled_Week between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
          col = AdminDb.execArrayLists(sql, 3, in, 9);
            }
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            custId = (String) one.get(8);
        CLIENT_CONTACTED = getClientContacted(rmCode,custId);
        DOCS_SUBMITTED = getDocSubmitted(rmCode,custId);
        SALES_COMMITMENT = getSaleCommited(rmCode,custId);
        CLOSED = getClosed(rmCode,custId);
            collection.add(new  SALES(BRANCH_CODE,REGION,BRANCH,CLIENT_CONTACTED,SALES_COMMITMENT,DOCS_SUBMITTED,CLOSED,(String) one.get(0), 
        (String) one.get(1) +". "+(String) one.get(2),(String) one.get(3),(String) one.get(4), (String) one.get(5), (String) one.get(8), HITRATE,rmCode, fromDate, toDate  
         ));
        }
       
        return collection;

    }
     public static Collection getRMSaleByBranch(String branch, String fromDate, String toDate) {
       String rmCode ="";
       String BRANCH_CODE ="0";
       String REGION ="";
       String BRANCH ="";
       String custId ="0";
       String CLIENT_CONTACTED = "0";
       String SALES_COMMITMENT = "0";
       String DOCS_SUBMITTED = "0";
       String CLOSED = "0";
       String HITRATE = "0";  
        ArrayList col =  new ArrayList();
       ArrayList collection = new ArrayList();
       ArrayList rms = Customer.getRmsByBranch(branch);
       for( int c = 0; c < rms.size(); c++){
           ArrayList two = (ArrayList) rms.get(c);
           rmCode = (String) two.get(0);
        BRANCH_CODE = getRMBranchCode(rmCode);
        REGION = getRMRegion(rmCode);
        BRANCH =getRMBranch(rmCode);
        String CLIENT_CONTACT = "0";
        String CLOSE = "0";
        CLIENT_CONTACT = getClientContact(rmCode);
        CLOSE = getClose(rmCode);
        HITRATE =  getRate(Integer.parseInt(CLIENT_CONTACT), Integer.parseInt(CLOSE));
        
        String sql = "select distinct Marketed_Value, Currency, Product_value,Product_Sold,Lead_Source, Customer_Type,RM_Code, Filled_Week,Customer_ID  from [dbo].[Customer_Tracking] where RM_Code = ? and Client_Contacted ='Yes' and Marketed_Value <> 'D' and Filled_Week between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
          col = AdminDb.execArrayLists(sql, 3, in, 9); 
          }
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            custId = (String) one.get(8);
        CLIENT_CONTACTED = getClientContacted(rmCode,custId);
        DOCS_SUBMITTED = getDocSubmitted(rmCode,custId);
        SALES_COMMITMENT = getSaleCommited(rmCode,custId);
        CLOSED = getClosed(rmCode,custId);
            collection.add(new  SALES(BRANCH_CODE,REGION,BRANCH,CLIENT_CONTACTED,SALES_COMMITMENT,DOCS_SUBMITTED,CLOSED,(String) one.get(0), 
        (String) one.get(1) +". "+(String) one.get(2),(String) one.get(3),(String) one.get(4), (String) one.get(5), (String) one.get(8), HITRATE,rmCode, fromDate, toDate  
         ));
        
       }
        return collection;

    }
     
    public static Collection getRMSales(String rmCode, String fromDate, String toDate) {
       String BRANCH_CODE ="0";
       String REGION ="";
       String BRANCH ="";
       String custId ="0";
       String CLIENT_CONTACTED = "0";
       String SALES_COMMITMENT = "0";
       String DOCS_SUBMITTED = "0";
       String CLOSED = "0";
       String HITRATE = "0";  
        BRANCH_CODE = getRMBranchCode(rmCode);
        REGION = getRMRegion(rmCode);
        BRANCH =getRMBranch(rmCode);
        String CLIENT_CONTACT = "0";
        String CLOSE = "0";
        CLIENT_CONTACT = getClientContact(rmCode);
        CLOSE = getClose(rmCode);
        HITRATE =  getRate(Integer.parseInt(CLIENT_CONTACT), Integer.parseInt(CLOSE));
        
        String sql = "select distinct Marketed_Value, Currency, Product_value,Product_Sold,Lead_Source, Customer_Type,RM_Code, Filled_Week,Customer_ID  from [dbo].[Customer_Tracking] where RM_Code = ? and Client_Contacted ='Yes' and Marketed_Value <> 'D' and Filled_Week between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 9);
        ArrayList collection = new ArrayList();
         
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            custId = (String) one.get(8);
        CLIENT_CONTACTED = getClientContacted(rmCode,custId);
        DOCS_SUBMITTED = getDocSubmitted(rmCode,custId);
        SALES_COMMITMENT = getSaleCommited(rmCode,custId);
        CLOSED = getClosed(rmCode,custId);
            collection.add(new  SALES(BRANCH_CODE,REGION,BRANCH,CLIENT_CONTACTED,SALES_COMMITMENT,DOCS_SUBMITTED,CLOSED,(String) one.get(0), 
        (String) one.get(1) +". "+(String) one.get(2),(String) one.get(3),(String) one.get(4), (String) one.get(5), (String) one.get(8), HITRATE,rmCode, fromDate, toDate  
         ));
        }
        return collection;

    }
      
    public static Collection getRMSalesPipeline(String rmCode, String fromDate, String toDate) {
       
        String sql = "select Customer_ID,Product,Products_Category,Average_Value,BM_Code,Regional_Manager_Code,Customer_Type,Date_Sold,Week_Sold from [dbo].[Products_Sold] where RM_Code = ? and Date_Sold between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 9);
        ArrayList collection = new ArrayList();

        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new PRODUCTS_SOLD((String) one.get(0), (String) one.get(1), (String) one.get(2), (String) one.get(3), (String) one.get(4), (String) one.get(5), (String) one.get(6), (String) one.get(7), (String) one.get(8), fromDate, toDate));
        }
        return collection;

    }

//     public static void main(String[] args) {
//         System.out.println(getRMSalesPipeline("242","15/09/2017", "17/09/2017"));
//    }
    public static Collection getSalesPipelineAll(String fromDate, String toDate) {
        String sql = "select RM_Code2 from [dbo].[CUSTOMER_VIEW]  where  FILLED_WEEK  between ? and ?";
        String in = fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 2, in, 1);
        ArrayList collection = new ArrayList();
        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            int rmCode = (int) one.get(0);
            String rmName = Customer.getRmName(String.valueOf(rmCode));
            String getClientContacted ="";// getClientContacted(rmCode);
            String getSaleCommited ="";// getSaleCommited(rmCode);
            String getDocSubmitted = "";// getDocSubmitted(rmCode);
            String getClosed = "";// getClosed(rmCode);
            int contacted = Integer.parseInt(getClientContacted);
            int closed = Integer.parseInt(getClosed);
            String hitRate = getRate(contacted, closed);
            String leadRate = getRate(contacted, closed);
            collection.add(new CUSTOMER_TRACK(rmName, getClientContacted, getSaleCommited, getDocSubmitted, getClosed, hitRate, leadRate, fromDate, toDate));
        }
        return collection;
    }

    public static Collection getSalesPipeline(String rmCode, String fromDate, String toDate) {
        String sql = "select  count(*)cont  from [dbo].[CUSTOMER_VIEW]  where RM_Code2 = ?   and FILLED_WEEK  between ? and ?";
        String in = rmCode + "," + fromDate + "," + toDate;
        ArrayList col = AdminDb.execArrayLists(sql, 3, in, 1);
        ArrayList collection = new ArrayList();
        String rmName = "null";
        String getClientContacted = "0";
        String getSaleCommited = "0";
        String getDocSubmitted = "0";
        String getClosed = "0";
        rmName = Customer.getRmName(rmCode);
//        getClientContacted = getClientContacted(rmCode);
//        getDocSubmitted = getDocSubmitted(rmCode);
//        getSaleCommited = getSaleCommited(rmCode);
//        getClosed = getClosed(rmCode);
        int contacted = Integer.parseInt(getClientContacted);
        int closed = Integer.parseInt(getClosed);
        String hitRate = "0";
        hitRate = getRate(contacted, closed);
        String leadRate = "0";
        leadRate = getRate(contacted, closed);

        for (int w = 0; w < col.size(); w++) {
            ArrayList one = (ArrayList) col.get(w);
            collection.add(new CUSTOMER_TRACK(rmName, getClientContacted, getSaleCommited, getDocSubmitted, getClosed, hitRate, leadRate, fromDate, toDate));
        }
        return collection;
    }

    public static String getRate(int contacted, int closed) {
        String rated = "0.0"; 
        if(contacted > 0){
            int rate = (closed * 100 / contacted);
            rated = String.valueOf(rate);
        }
         return rated;
    }
public static String getClientContact(String rmCode ) {
        String sql = "select  count(CLIENT_CONTACTED)CLIENT_CONTACTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and CLIENT_CONTACTED = 'Yes' ";
        String in = rmCode;
        return AdminDb.getValue(sql, 1, 1, in);
    }
 public static String getClose(String rmCode) {
        String sql = "select  count(CLOSED)CLOSED  from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and CLOSED = 'Yes' ";
        String in = rmCode ;
        return AdminDb.getValue(sql, 1, 1,in );
    }
    public static String getClientContacted(String rmCode ,String custId) {
        String sql = "select  count(CLIENT_CONTACTED)CLIENT_CONTACTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and Customer_ID =?  and CLIENT_CONTACTED = 'Yes' ";
        String in = rmCode  + "," + custId;
        return AdminDb.getValue(sql, 1, 2, in);
    }

    public static String getSaleCommited(String rmCode ,String custId) {
        String sql = "select  count(SALES_COMMITMENT)SALES_COMMITMENT from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and Customer_ID =? and SALES_COMMITMENT = 'Yes' ";
       String in = rmCode  + "," + custId;
        return AdminDb.getValue(sql, 1, 2, in);
    }

    public static String getDocSubmitted(String rmCode ,String custId) {
        String sql = "select  count(DOCS_SUBMITTED)DOCS_SUBMITTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and Customer_ID =? and DOCS_SUBMITTED = 'Yes' ";
        String in = rmCode  + "," + custId;
        return AdminDb.getValue(sql, 1, 2, in);
    }

    public static String getClosed(String rmCode ,String custId) {
        String sql = "select  count(CLOSED)CLOSED  from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and Customer_ID =? and CLOSED = 'Yes' ";
        String in = rmCode  + "," + custId;
        return AdminDb.getValue(sql, 1, 2,in );
    }
    public static String getClientContacted(String rmCode  ) {
        String sql = "select  count(CLIENT_CONTACTED)CLIENT_CONTACTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ?    and CLIENT_CONTACTED = 'Yes' ";
        String in = rmCode;
        return AdminDb.getValue(sql, 1, 1, in);
    }

    public static String getSaleCommited(String rmCode ) {
        String sql = "select  count(SALES_COMMITMENT)SALES_COMMITMENT from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ?  and SALES_COMMITMENT = 'Yes' ";
       String in = rmCode ;
        return AdminDb.getValue(sql, 1, 1, in);
    }

    public static String getDocSubmitted(String rmCode ) {
        String sql = "select  count(DOCS_SUBMITTED)DOCS_SUBMITTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ?  and DOCS_SUBMITTED = 'Yes' ";
        String in = rmCode ;
        return AdminDb.getValue(sql, 1,1, in);
    }

    public static String getClosed(String rmCode ) {
        String sql = "select  count(CLOSED)CLOSED  from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ?  and CLOSED = 'Yes' ";
        String in = rmCode ;
        return AdminDb.getValue(sql, 1, 1,in );
    }
     public static String getRMBranchCode(String rmCode) {
        String sql = "select distinct [Branch Code] from [dbo].[RM_Codelist] where RM_Code1 = ?";
        String str = AdminDb.getValue(sql, 1, 1, rmCode); 
        return str;
    } 
     public static String getRMBranch(String rmCode) {
        String sql = "select distinct Branch from [dbo].[RM_Codelist] where RM_Code1 = ?";
        String str = AdminDb.getValue(sql, 1, 1, rmCode); 
        return str;
    } 
     public static String getRMRegion(String rmCode) {
        String sql = "select distinct Region from [dbo].[RM_Codelist] where RM_Code1 = ?";
        String str = AdminDb.getValue(sql, 1, 1, rmCode); 
        return str;
    } 

}
