package com.sfe.dao.reports;

import com.sfe.conn.AdminDb;
import com.sfe.dao.customer.Customer;
import java.util.Collection;
import java.util.ArrayList;

public class BeanFactory {

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
            String rmCode = (String) one.get(0);
            String rmName = Customer.getRmName(rmCode);
            String getClientContacted = getClientContacted(rmCode);
            String getSaleCommited = getSaleCommited(rmCode);
            String getDocSubmitted = getDocSubmitted(rmCode);
            String getClosed = getClosed(rmCode);
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
            collection.add(new CUSTOMER_TRACK(rmName, getClientContacted, getSaleCommited, getDocSubmitted, getClosed, hitRate, leadRate, fromDate, toDate));
        }
        return collection;
    }

    public static String getRate(int contacted, int closed) {
        String rated = "0.0";
        if (contacted > 0) {
            int rate = (closed / contacted) * 100;
            rated = String.valueOf(rate);
        }
        return rated;
    }

    public static String getClientContacted(String rmCode) {
        String sql = "select  count(CLIENT_CONTACTED)CLIENT_CONTACTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and CLIENT_CONTACTED = 'Yes' ";
        return AdminDb.getValue(sql, 1, 1, rmCode);
    }

    public static String getSaleCommited(String rmCode) {
        String sql = "select  count(SALES_COMMITMENT)SALES_COMMITMENT from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and SALES_COMMITMENT = 'Yes' ";
        return AdminDb.getValue(sql, 1, 1, rmCode);
    }

    public static String getDocSubmitted(String rmCode) {
        String sql = "select  count(DOCS_SUBMITTED)DOCS_SUBMITTED from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and DOCS_SUBMITTED = 'Yes' ";
        return AdminDb.getValue(sql, 1, 1, rmCode);
    }

    public static String getClosed(String rmCode) {
        String sql = "select  count(CLOSED)CLOSED  from [dbo].[CUSTOMER_VIEW] where RM_Code2 = ? and CLOSED = 'Yes' ";
        return AdminDb.getValue(sql, 1, 1, rmCode);
    }

}
