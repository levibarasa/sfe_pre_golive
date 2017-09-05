package com.sfe.dao.customer;

import com.sfe.conn.AdminDb;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Customer {
private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    public static int getNewTempCustId() {
        String sql = "Select max(Serial_Number) from Temporary_ID_Table";
        String str = AdminDb.getValue(sql, 1, 0, "");
        int id = Integer.parseInt(str);
        id = id + 1;
        return id;
    }

  public static ArrayList populateDailyList(String rmCode) {
       String today = sdf.format(new Date());
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Customer_Type, email_ID,Occupation,Client_Contacted,Sales_Commitment, Docs_Submitted,Closed,Comments  from [dbo].[CUSTOMER_VIEW] where Current_Week = ?  and RM_Code2 = ?";
        String in = today + "," +rmCode;
        return AdminDb.execArrayLists(sql, 2, in, 11);
    }
    
    public static String getRmCode(String rmCode) {
        String sql = "select Branch from [dbo].[RM_Codelist] where RM_Code1 = ?";
        return AdminDb.getValue(sql, 1, 1, rmCode);
    }
    public static String getDate(String custId) {
        String sql = "select distinct Current_Week from [dbo].[CUSTOMER_VIEW]  where Customer_Id = ?";
        return AdminDb.getValue(sql, 1, 1, custId);
    }

    public static ArrayList fetchDailyList(String rmCode) {
        String sql = "select Customer_ID, RM_Code2,BM_Code,Regional_Manager_Code,Customer_Type,BM_Code1 from  [dbo].[Cust_Demo_1]  where RM_Code2 = ?";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 6);
    }

    public static void main(String[] args) {
   String today = sdf.format(new Date());
        String rmCode ="242"; 
    // ArrayList list = fetchDailyList(rmCode);
    //  createDailyList(list, rmCode); 
    System.out.println(populateDailyList(rmCode).size() );
        System.out.println(populateDailyList(rmCode) );
    
    }
    public static void createDailyList(ArrayList list, String rmCode) {
        list = fetchDailyList(rmCode);
        String Customer_ID, RM_Code, BM_Code, Regional_Manager_Code, Customer_Type, BM_Code_Sustainability;
        for (int i = 0; i < 10; i++) {
            ArrayList one = (ArrayList) list.get(i);
            Customer_ID = (String) one.get(0);
            RM_Code = (String) one.get(1);
            BM_Code = (String) one.get(2);
            Regional_Manager_Code = (String) one.get(3);
            Customer_Type = (String) one.get(4);
            BM_Code_Sustainability = (String) one.get(5);
 
            String branch = getRmCode(rmCode);
            String product_value = "23978";
            String sql = "insert into [dbo].[Customer_Tracking] "
                    + "(Customer_ID , Filled_Date, Filled_Week,Client_Contacted,Sales_Commitment,"
                    + "Docs_Submitted,Closed,Comments,Product_Sold,"
                    + "RM_Code,Current_Week,BM_Code,"
                    + "Regional_Manager_Code,Branch,Product_value,Customer_Type,BM_Code_Sustainability,"
                    + "RegM_code_Sustainability) values(?,try_convert(varchar(11),getdate(),103),"
                    + "try_convert(varchar(11),getdate(),103),'No','No','No','No','None','None',"
                    + "?,try_convert(varchar(11),getdate(),103),?,?,?,?,?,?,?)";
            
            String in = Customer_ID + "," + RM_Code + "," + BM_Code + "," + Regional_Manager_Code + "," + branch + ","
                    + " " + product_value + "," + Customer_Type + "," + BM_Code_Sustainability + "," + RM_Code;
            AdminDb.dbWork(sql, 9, in);
        }
    }

    public static void updateTracker(ArrayList list) {
        String custId, clientcontacted, salescommitment, docsubmitted, closed, comments, scheduledcalldate;
        for (int i = 0; i < list.size(); i++) {
            ArrayList one = (ArrayList) list.get(i);
            custId = (String) one.get(0);
            clientcontacted = (String) one.get(1);
            salescommitment = (String) one.get(2);
            docsubmitted = (String) one.get(3);
            closed = (String) one.get(4);
            comments = (String) one.get(5);
            scheduledcalldate = (String) one.get(6);

            String sql = "update [dbo].[Customer_Tracking] set Client_Contacted=?,Sales_Commitment=?, Docs_Submitted=?,Closed=?,Comments=?,Current_Week = try_convert(datetime, ?, 121) "
                    + "where Customer_ID =?";
            String in = clientcontacted + "," + salescommitment + "," + docsubmitted + "," + closed + "," + comments + "," + " " + scheduledcalldate + "," + custId;
            AdminDb.dbWork(sql, 7, in);
        }
    }

    public static int addNewCustomer(String customerName, String email, String phone, String RmCode) {
        int tempId = getNewTempCustId();
        String sql = "insert into [dbo].[New_Customers] (Temporary_ID,Customer_Name,Contact_Number,"
                + "Email_ID,RM_Code,RM_Code1,Week) values(?,?,?,?,?,?,try_convert(datetime, GETDATE(), 121))";
        String in = tempId + "," + customerName + "," + phone + "," + email + "," + RmCode + "," + RmCode;
        int n = AdminDb.dbWork(sql, 6, in);
        return n;
    }

    public static ArrayList getCustomers(String rmCode) {
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Customer_Type,Occupation,email_ID  from [dbo].[Cust_Demo_1]  where RM_Code2 = ?  and Customer_ID  in  (select Customer_ID from [dbo].[Customer_Tracking] )";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 6);
    }

    public static ArrayList getCustomerTracker(String custId) {
        String sql = "select Client_Contacted , Sales_Commitment , Docs_Submitted , Closed , Comments , Current_Week from [dbo].[Customer_Tracking] where Customer_ID = ?";
        String in = custId;
        return AdminDb.execArrayLists(sql, 1, in, 6);
    }

    public static ArrayList getCustomerInfo(String custId) {
        String sql = "select distinct Customer_ID,Name,Permanent_phonenumber,Age,Marital_Status,City,Customer_Type,Permanent_Address,Occupation,Years_With_Bank,email_ID from CUSTOMER_VIEW where Customer_ID = ?";
        String in = custId;
        return AdminDb.execArrayLists(sql, 1, in, 11);
    }

    public static ArrayList getCustomerSoldProds(String custId) {
        String sql = "select Product FROM [dbo].[Products_Sold] where Customer_ID = ?";
        String in = custId;
        return AdminDb.execArrayLists(sql, 1, in, 1);
    }

    public static ArrayList getCustomerOtherProds(String custId) {
        String sql = "select distinct Product FROM [dbo].[Products_Sold] where Customer_ID <> ?";
        String in = custId;
        return AdminDb.execArrayLists(sql, 1, in, 1);
    }

    public static int getProductHolding(String custId) {
        String sql = "select count(Product) from   [dbo].[Products_Sold] where Customer_ID  = ?";
        String str = AdminDb.getValue(sql, 1, 1, custId);
        return Integer.parseInt(str);
    }

    public static ArrayList getPreviousContact(String custId) {
        String sql = "select  Date FROM [dbo].[Products_Sold] where Customer_ID = ?";
        String in = custId;
        return AdminDb.execArrayLists(sql, 1, in, 1);
    }

//    public static void main(String[] args) {
//        String rmCode = "242";
//        String days = "20"; //request.getParameter("days");
//        int noOfdays = Integer.parseInt(days);
//        ArrayList ar = Customer.getPreviousWeeklyCallList(rmCode, 1, noOfdays);
//        for (int i = 0; i < ar.size(); i++) {
//            ArrayList one = (ArrayList) ar.get(i);
//            String custID, name, phone, custType, occupation, emailId, age, Marital_status, address, yrs, city;
//            custID = (String) one.get(0);
//            name = (String) one.get(1);
//            phone = (String) one.get(2);
//            age = (String) one.get(3);
//            Marital_status = (String) one.get(4);
//            city = (String) one.get(5);
//            custType = (String) one.get(6);
//            address = (String) one.get(7);
//            occupation = (String) one.get(8);
//            yrs = (String) one.get(9);
//            emailId = (String) one.get(10);
//            System.out.println(custID + " " + name + " " + occupation);
//        }
//    }
    public static ArrayList getPreviousWeeklyCallList(String rmCode, int beginBackDay, int endBackDay) {
        String end = getPreviousDate(beginBackDay);
        String start = getPreviousDate(endBackDay);
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Age,Marital_Status,City,Customer_Type,Permanent_Address,Occupation,Years_With_Bank,email_ID from CUSTOMER_VIEW  where RM_Code2 = ? and current_week  between try_convert(datetime,?,121)  and try_convert(datetime, ?, 121) ";
        String in = rmCode + "," + start + "," + end;
        return AdminDb.execArrayLists(sql, 3, in, 11);
    }

    public static String getPreviousDate(int days) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.minusDays(days);
        return then.format(format).toString();
    }

//     public static void main(String[] args) { 
//
//       ArrayList ar = getCustomers("242");
//       for (int i=0; i<ar.size();i++){
//            ArrayList one = (ArrayList) ar.get(i);
//            String custID,name,phone,custType,occupation;
//            custID = (String) one.get(0);
//            name = (String) one.get(1);
//            phone =(String) one.get(2);
//            custType =(String) one.get(3);
//            occupation =(String) one.get(4);
//            
//        System.out.println(custID+" "+name+" "+phone+" "+custType+" "+occupation);
//       }
//
//    }
    public static ArrayList getUnMappedAccounts() {
        String sql = "select DISTINCT cust_id, acct_name, sol_id from general_acct_mast_table where sub_group_code is null and mapped_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "N", 3);
    }

    public static ArrayList getAccounts() {
        String sql = "select DISTINCT cust_id, acct_name  from general_acct_mast_table where sub_group_code is  null and mapped_flg = ?";
        return AdminDb.execArrayLists(sql, 1, "N", 2);
    }

    public static int getNumberOfSbGrpMembers(int groupId) {
        String s = "select no_of_members from sub_grp_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static int getMaxSbGrpMembers(int groupId) {
        String s = "select no_of_members from sub_grp_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    // Get unmapped account details using cust no
    public static ArrayList getUnMappedAccountDetails(String foracid) {
        String sql = "select cust_id, acct_name, sol_id from general_acct_mast_table where sub_group_code is null and mapped_flg = ? and schm_code <>? and cust_id = ?";
        String in = "N,SBGRP," + foracid;
        return AdminDb.execArrayLists(sql, 3, in, 3);
    }

    // Repayment Account Number Mapping
    public static ArrayList getUnMappedRepayAcnt() {

        String sql = "select cust_id, acct_name, sol_id from general_acct_mast_table where mapped_flg = ? and schm_code = ?";
        String in = "N,SBGRP";
        return AdminDb.execArrayLists(sql, 2, in, 3);
    }

    public static int getNumberOfGrpMembers(int groupId) {
        String s = "select no_of_members from groups_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static int getMaxNumberOfGrpMembers(int groupId) {
        String s = "select MAX_ALLOWED_MEMBERS from groups_table where group_id=?";
        int k = 0;
        try {
            String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
            k = Integer.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public static void addGroupMember(int groupId, String username) {
        int k = getNumberOfGrpMembers(groupId);
        String sql = "update groups_table set no_of_members = ?, lchg_user_id =? where group_id=?";
        String in = k + 1 + "," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    public static void removeGroupMember(int groupId, String username) {
        String s = "select no_of_members from groups_table where group_id=?";
        String str = AdminDb.getValue(s, 1, 1, String.valueOf(groupId));
        int k = 0;
        try {
            k = Integer.valueOf(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String sql = "update groups_table set no_of_members = ?, lchg_user_id =? where group_id=?";
        String in = k - 1 + "," + username + "," + groupId;
        AdminDb.dbWork(sql, 3, in);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getUnMappedAccountDetailsMod(String foracid) {
        String sql = "select k.cust_id, k.acct_name, k.sol_id, k.sub_group_code,w.sub_group_name  from general_acct_mast_table_mod k, sub_grp_table w where cust_id = ? and w.sub_group_code=k.sub_group_code";
        return AdminDb.execArrayLists(sql, 1, foracid, 5);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllUnMappedAccountMod(String uname) {
        String sql = "select cust_id, foracid, acct_name, acct_opn_date, sol_id,schm_type,schm_code ,sub_group_code, last_oper  from general_acct_mast_table_mod  where LAST_OPER not like 'V' and  LAST_OPER not like 'D' and LAST_OPER not like 'E' AND rcre_user_id <>?";
        return AdminDb.execArrayLists(sql, 1, uname, 9);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllUnMappedExitAccountMod(String uname) {
        String sql = "select DISTINCT cust_id, foracid, acct_name, acct_opn_date, sol_id,schm_type,schm_code ,sub_group_code, last_oper  from general_acct_mast_table_mod  where LAST_OPER like 'V' OR   LAST_OPER like 'D' OR LAST_OPER like 'E' AND rcre_user_id <>?";
        return AdminDb.execArrayLists(sql, 1, uname, 9);
    }

    // Get unmapped account details using account no from Mod
    public static ArrayList getAllVerfiedAccounts() {
        String sql = "SELECT DISTINCT cust_id, acct_name, sol_id, sub_group_code from general_acct_mast_table where mapped_flg = ? and member_status=? and sub_group_code is not null";
        String in = "Y,A";
        return AdminDb.execArrayLists(sql, 2, in, 4);
    }

    public static String getCustIdByName(String name) {
        String sql = " select cust_id from GENERAL_ACCT_MAST_TABLE where ACCT_NAME=?";
        return AdminDb.getValue(sql, 1, 1, name);
    }

    public static String getMemberStatus(String accnt) {
        String sql = "select member_status from  general_acct_mast_table where CUST_ID= ?";
        return AdminDb.getValue(sql, 1, 1, accnt);
    }
//   public static void main(String[] args) {
//        System.out.println(getAccountDetails("000037026", "WES2010", "A","1", "MARK"));
//    }

    // get data to insert into mod table
    public static int getAccountDetails(String forr, String subGroupId, String lastOper, String creditRate, String uname) {
        String sql = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OWNERSHIP,"
                + "BANK_ID,CLR_BAL_AMT,CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,"
                + "LIEN_AMT,RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,RCRE_TIME,"
                + "ACCT_OPN_DATE from general_acct_mast_table where cust_id = ?";
        String str = AdminDb.getValue(sql, 20, 1, forr);
        String[] args = str.split("\\s*,\\s*");
        String s = "insert into general_acct_mast_table_mod (ACID,ACCT_CRNCY_CODE,"
                + "ACCT_MGR_USER_ID,ACCT_NAME,ACCT_OWNERSHIP,BANK_ID,CLR_BAL_AMT,"
                + "CUST_ID,DEL_FLG,DRWNG_POWER,ENTITY_CRE_FLG,FORACID,LIEN_AMT,"
                + "RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE,SOL_ID,RCRE_TIME,"
                + "ACCT_OPN_DATE,LAST_MODIFIED_DATE,LCHG_TIME,LCHG_USER_ID, SUB_GROUP_CODE,"
                + "LAST_OPER,CREDIT_RATING) values"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,try_convert(date, ?, 111) ,"
                + "try_convert(date, ?, 111),try_convert(date, getDate(), 111) ,"
                + "try_convert(date, getDate(), 111),?,?,?,?)";
        String in = "";

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        for (int w = 0; w < 20; w++) {
            in = in + args[w] + ",";
        }

        String adds = uname + "," + subGroupId + "," + lastOper + "," + creditRate;
        in = in + adds;
        int n = AdminDb.dbWork(s, 24, in);
        if (n > 0) {
            markAccountAsMappedinGam(forr, lastOper);
        }
        return n;
    }

    public static int getSolId(String bankId) {
        String sql = "select sol_id from  service_outlet_table where bank_id= ?";
        String r = AdminDb.getValue(sql, 1, 1, bankId);
        int k = Integer.parseInt(r);
        return k;
    }

    public static int addAuditCustomerTrail(String acid) {
        String data = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,"
                + "ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER,"
                + " ENTITY_CRE_FLG, FORACID, LCHG_USER_ID, LIEN_AMT, RCRE_USER_ID,"
                + "SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID ,"
                + "SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, "
                + "CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,"
                + "ACCT_OPN_DATE from GENERAL_ACCT_MAST_TABLE where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(data, 1, acid, 27);
        //System.out.println(""+ar);
        int h = 0;
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String in = one.get(0) + "," + one.get(1) + "," + one.get(2) + ","
                    + one.get(3) + "," + one.get(4) + "," + one.get(5) + ","
                    + one.get(6) + "," + one.get(7) + "," + one.get(8) + ","
                    + one.get(9) + "," + one.get(10) + "," + one.get(11) + ","
                    + one.get(12) + "," + one.get(13) + "," + one.get(14) + ","
                    + one.get(15) + "," + one.get(16) + "," + one.get(17) + ","
                    + one.get(18) + "," + one.get(19) + "," + one.get(20)
                    + "," + one.get(21) + "," + one.get(22) + ","
                    + one.get(23) + "," + one.get(24)
                    + "," + one.get(25) + "," + one.get(26);

            String sql = "insert into GENERAL_ACCT_MAST_AUDIT_TABLE("
                    + "ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,"
                    + "ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER,"
                    + " ENTITY_CRE_FLG, FORACID, LCHG_USER_ID,"
                    + " LIEN_AMT, RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID "
                    + ",SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, "
                    + "CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,"
                    + "ACCT_OPN_DATE) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "try_convert(date, ?, 111),try_convert(date, ?, 111) ,try_convert(date, ?, 111) "
                    + ",try_convert(date, ?, 111))";
            h = AdminDb.dbWork(sql, 27, in);
        }
        return h;
    }

    public static int addAuditCustomerReinstateTrail(String acid) {
        String data = "select ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,"//0-3
                + "ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER,"//4-9
                + " ENTITY_CRE_FLG, FORACID, LCHG_USER_ID, LIEN_AMT, RCRE_USER_ID,"//10-14
                + "SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID ,"//15-18
                + "SUB_GROUP_CODE, MAPPED_FLG,  "//19-20
                + "CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,"//21-24
                + "ACCT_OPN_DATE from GENERAL_ACCT_MAST_TABLE where cust_id = ?";//25
        ArrayList ar = AdminDb.execArrayLists(data, 1, acid, 26);
        //System.out.println(""+ar);
        int h = 0;
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String in = one.get(0) + "," + one.get(1) + "," + one.get(2) + ","
                    + one.get(3) + "," + one.get(4) + "," + one.get(5) + ","
                    + one.get(6) + "," + one.get(7) + "," + one.get(8) + ","
                    + one.get(9) + "," + one.get(10) + "," + one.get(11) + ","
                    + one.get(12) + "," + one.get(13) + "," + one.get(14) + ","
                    + one.get(15) + "," + one.get(16) + "," + one.get(17) + ","
                    + one.get(18) + "," + one.get(19) + "," + one.get(20)
                    + "," + "R" + "," + one.get(21) + ","
                    + one.get(22) + "," + one.get(23)
                    + "," + one.get(24) + "," + one.get(25);

            String sql = "insert into GENERAL_ACCT_MAST_AUDIT_TABLE("
                    + "ACID,ACCT_CRNCY_CODE,ACCT_MGR_USER_ID, ACCT_NAME,"
                    + "ACCT_OWNERSHIP,BANK_ID, CLR_BAL_AMT,CUST_ID, DEL_FLG,DRWNG_POWER,"
                    + " ENTITY_CRE_FLG, FORACID, LCHG_USER_ID,"
                    + " LIEN_AMT, RCRE_USER_ID,SANCT_LIM,SCHM_CODE,SCHM_TYPE, SOL_ID "
                    + ",SUB_GROUP_CODE, MAPPED_FLG, MEMBER_STATUS, "
                    + "CREDIT_RATING,LAST_MODIFIED_DATE,LCHG_TIME,RCRE_TIME,"
                    + "ACCT_OPN_DATE) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "try_convert(date, ?, 111),try_convert(date, ?, 111) ,try_convert(date, ?, 111) "
                    + ",try_convert(date, ?, 111))";
            h = AdminDb.dbWork(sql, 27, in);
        }
        return h;
    }

    public static String getLastGamModOper(String foracid) {
        String sql = "select last_oper from general_acct_mast_table_mod where foracid = ?";
        return AdminDb.getValue(sql, 1, 1, foracid);
    }

    // Update GAM  Mapped flag AND WAIT FOR APPROVAL
    public static void markAccountAsMappedinGam(String foracid, String memberStatus) {
        String sql = "update general_acct_mast_table set mapped_flg = ?, member_status = ? where cust_id = ?";
        String in = "Y," + memberStatus + "," + foracid;
        AdminDb.dbWork(sql, 3, in);
    }

    // Get subgroup name
    public static String getSubGroupName(String subGroupId) {
        String sql = "select sub_group_name from sub_grp_table where sub_group_code = ?";
        return AdminDb.getValue(sql, 1, 1, subGroupId);
    }

//--
    // Get getlinkedAccounts
    public static String getlinkedAccounts(String subGroupId, String custId) {
        String sql = "select foracid from general_acct_mast_table where schm_code like ? and cust_id = ?";
        String in = subGroupId + "," + custId;
        return AdminDb.getValue(sql, 1, 2, in);
    }

    // Verify and delete from mod table
    public static void verifyAccountDetails(String foracid, String uname) {
        String sql = "select sub_group_code, credit_rating from general_acct_mast_table_mod where cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 2);
        for (int w = 0; w < ar.size(); w++) {
            ArrayList one = (ArrayList) ar.get(w);
            String sub = (String) one.get(0);
            String act = (String) one.get(1);
            completeVerification(foracid, sub, act, uname);
            deleteAfterVerification(foracid);
        }
    }

//    public static void main(String[] args) {
//
//        String custId = "";
//        String acctName, savingsAcnt, oprAcnt, loanAcnt, solId, groupCode, groupName, subgroupCode, subgroupName, creditRating, member_status;
//        acctName = savingsAcnt = loanAcnt = oprAcnt = solId = groupCode = groupName = subgroupCode = subgroupName = creditRating = member_status = "";
//        ArrayList all = getAccountDetails("000037022");
//        int size = all.size();
//
//        for (int i = 0; i < size; i++) {
//            ArrayList one = (ArrayList) all.get(i);
//            custId = (String) one.get(0);
//            acctName = (String) one.get(1);
//            savingsAcnt = (String) one.get(2);
//            loanAcnt = (String) one.get(3);
//            oprAcnt = (String) one.get(4);
//            solId = (String) one.get(5);
//            groupCode = (String) one.get(6);
//            subgroupCode = (String) one.get(7);
//            groupName = (String) one.get(8);
//            subgroupName = (String) one.get(9);
//            creditRating = (String) one.get(10);
//            member_status = (String) one.get(11);
//
//            System.out.println("CustId: " + custId + "\n Acc Name: " + acctName + "\n Sav Acc: " + savingsAcnt
//                    + "\n Loan Acc: " + loanAcnt + "\n Loan Acc: " + oprAcnt + "\n Sol Id: " + solId + "\n Group Code: " + groupCode + "\n SubGroup Code: "
//                    + subgroupCode + "\n Group Name: " + groupName + "\n SubGroup Name: " + subgroupName
//                    + "\n CreditRating Name: " + creditRating + "\n Member Status: " + member_status);
//        }
//    }
    // Current Account Details
    public static ArrayList getAccountDetails(String foracid) {
        String sql = "select acct_name, sol_id, sub_group_code, credit_rating,member_status from general_acct_mast_table where  cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 5);

        ArrayList arr = new ArrayList();
        String operAcc = "001";
        String loanAcc = "001";
        String saveAcc = "001";
        String groupName = " ";
        String groupCode = " ";
        String subGroupName = " ";
        String member_status = "";
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            //loan acc ... GLSGR,GLSIN
            loanAcc = getlinkedAccounts("GLS%", foracid);
            operAcc = getlinkedAccounts("SBGCO", foracid);
            saveAcc = getlinkedAccounts("SBGLS", foracid);

            String accName = (String) hs.get(0);
            String solId = (String) hs.get(1);
            String subGroupCode = (String) hs.get(2);
            String creditRating = (String) hs.get(3);
            member_status = (String) hs.get(4);
            groupName = getGroupName(subGroupCode);
            groupCode = getGroupCode(subGroupCode);
            subGroupName = getSubGroupName(subGroupCode);

            ArrayList one = new ArrayList();
            if (operAcc.isEmpty()) {
                operAcc = "001";
            }
            if (loanAcc.isEmpty()) {
                loanAcc = "001";
            }
            if (saveAcc.isEmpty()) {
                saveAcc = "001";
            }
            one.add(foracid);
            one.add(accName);
            one.add(loanAcc);
            one.add(saveAcc);
            one.add(operAcc);
            one.add(solId);
            one.add(groupCode);
            one.add(subGroupCode);
            one.add(groupName);
            one.add(subGroupName);//5
            one.add(creditRating);
            one.add(member_status);
            arr.add(one);
        }
        return arr;
    }

    public static ArrayList getVoluntaryExitAccountDetails(String foracid) {
        String sql = "select acct_name, sol_id, sub_group_code, credit_rating,member_status from general_acct_mast_table where member_status='V' and  cust_id = ?";
        ArrayList ar = AdminDb.execArrayLists(sql, 1, foracid, 5);

        ArrayList arr = new ArrayList();
        String operAcc = "001";
        String loanAcc = "001";
        String saveAcc = "001";
        String groupName = " ";
        String groupCode = " ";
        String subGroupName = " ";
        String member_status = "";
        for (int h = 0; h < ar.size(); h++) {
            ArrayList hs = (ArrayList) ar.get(h);
            //loan acc ... GLSGR,GLSIN
            loanAcc = getlinkedAccounts("GLS%", foracid);
            operAcc = getlinkedAccounts("SBGCO", foracid);
            saveAcc = getlinkedAccounts("SBGLS", foracid);

            String accName = (String) hs.get(0);
            String solId = (String) hs.get(1);
            String subGroupCode = (String) hs.get(2);
            String creditRating = (String) hs.get(3);
            member_status = (String) hs.get(4);
            groupName = getGroupName(subGroupCode);
            groupCode = getGroupCode(subGroupCode);
            subGroupName = getSubGroupName(subGroupCode);

            ArrayList one = new ArrayList();
            if (operAcc.isEmpty()) {
                operAcc = "001";
            }
            if (loanAcc.isEmpty()) {
                loanAcc = "001";
            }
            if (saveAcc.isEmpty()) {
                saveAcc = "001";
            }
            one.add(foracid);
            one.add(accName);
            one.add(loanAcc);
            one.add(saveAcc);
            one.add(operAcc);
            one.add(solId);
            one.add(groupCode);
            one.add(subGroupCode);
            one.add(groupName);
            one.add(subGroupName);//5
            one.add(creditRating);
            one.add(member_status);
            arr.add(one);
        }
        return arr;
    }

    public static int getGroupId(String subGrCode) {
        String sql = "select group_id from sub_grp_table where sub_group_code= ?";
        String r = AdminDb.getValue(sql, 1, 1, subGrCode);
        int k = Integer.parseInt(r);
        return k;
    }

    public static String getGroupName(String subGrCode) {
        String grpId = String.valueOf(getGroupId(subGrCode));
        String sql = "select group_name from groups_table where group_id= ?";
        return AdminDb.getValue(sql, 1, 1, grpId);
    }

    public static String getGroupCode(String subGrCode) {
        String grpId = String.valueOf(getGroupId(subGrCode));
        String sql = "select group_code from groups_table where group_id= ?";
        return AdminDb.getValue(sql, 1, 1, grpId);
    }

    // Update GAM with verified details
    public static void completeVerification(String foracid, String subGroupId, String actType, String uname) {
        String sql = "update general_acct_mast_table set  credit_rating = ?, sub_group_code = ?, "
                + "lchg_user_id = ?, lchg_time = try_convert(date, getDate(), 111), "
                + "last_modified_date = try_convert(date, getDate(), 111) where cust_id = ?";
        String in = actType + "," + subGroupId + "," + uname + "," + foracid;
        AdminDb.dbWork(sql, 4, in);
    }

    // delete mod record
    public static void deleteAfterVerification(String foracid) {
        String sql = "delete from general_acct_mast_table_mod where cust_id = ?";
        AdminDb.dbWork(sql, 1, foracid);
    }

    private static String lastOperFlg(int custId) {
        String sql = "select last_oper from general_acct_mast_table_mod where cust_id = ?";
        return AdminDb.getValue(sql, 1, 1, String.valueOf(custId));
    }

}
