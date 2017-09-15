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
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static int getNewTempCustId() {
        String sql = " Select max(Temporary_ID) from New_Customers";
        String str = AdminDb.getValue(sql, 1, 0, "");
        int id = Integer.parseInt(str);
        return id;
    }

    public static String getCustName(String custId) {
        String sql = "select Name from [dbo].[Cust_Demo_1] where Customer_ID = ?";
        return AdminDb.getValue(sql, 1, 1, custId);
    }

    public static ArrayList getReminders(String rmCode) {
        String today = sdf1.format(new Date());
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Customer_Type,email_ID,Occupation,Client_Contacted,Sales_Commitment,Docs_Submitted,Closed,Comments,Filled_Week  from [dbo].[CUSTOMER_VIEW] where  Filled_Week between  ?  and '5017-09-01'   and RM_Code2 = ?";
        String in = today + "," + rmCode;
        return AdminDb.execArrayLists(sql, 2, in, 12);
    }

    public static ArrayList getNewCustomer(String rmCode) {
        String today = sdf.format(new Date());
        String sql = "select   Temporary_ID, Customer_Name, Contact_Number, Email_ID, RM_Code,Customer_Type, Client_Contacted, Sales_Commitment, Docs_Submitted, Closed, Comments, Filled_Week  from [dbo].[NEW_CUSTOMER_VIEW] where Current_Week = ?  and  RM_Code = ?";
        String in = today + "," + rmCode;
        return AdminDb.execArrayLists(sql, 2, in, 12);
    }

    public static ArrayList getDailyList(String rmCode) {
        ArrayList newCust = getNewCustomer(rmCode);
        ArrayList daily = populateDailyList(rmCode);
        ArrayList full = new ArrayList();
        ArrayList full1 = new ArrayList();
        ArrayList fullArr = new ArrayList();
        for (int i = 0; i < daily.size(); i++) {
            full = (ArrayList) daily.get(i);
            fullArr.add(full);
        }

        for (int k = 0; k < newCust.size(); k++) {
            full1 = (ArrayList) newCust.get(k);
            fullArr.add(full1);
        }

        return fullArr;
    }

    public static ArrayList populateDailyList(String rmCode) {
        String today = sdf.format(new Date());
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Customer_Type, email_ID,Occupation,Client_Contacted,Sales_Commitment, Docs_Submitted,Closed,Comments,Filled_Week  from [dbo].[CUSTOMER_VIEW] where Current_Week = ?  and RM_Code2 = ?";
        String in = today + "," + rmCode;
        return AdminDb.execArrayLists(sql, 2, in, 12);
    }

    public static ArrayList populateCustomerListByName(String rmCode, String name) {
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Customer_Type,email_ID,Occupation "
                + "   from [dbo].[Cust_Demo_1] where "
                + "Name like '%" + name + "%' and RM_Code2 = ?";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 6);
    }

    public static ArrayList populateCustomerListById(String rmCode, String custId) {
        String sql = "select Customer_ID,Name,Permanent_phonenumber,Customer_Type,email_ID,Occupation "
                + "   from [dbo].[Cust_Demo_1] where "
                + "Customer_ID ='" + custId + "'    and RM_Code2 = ?";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 6);
    }

    public static ArrayList populatePreviousList(String rmCode, String fromDate, String toDate) {
        String sql = "select  distinct Customer_ID,Name,Permanent_phonenumber,Customer_Type,email_ID,Occupation,Client_Contacted,Sales_Commitment,"
                + " Docs_Submitted,Closed,Comments,Filled_Week  from [dbo].[CUSTOMER_VIEW] where "
                + "Filled_Week between ?  and ?   and RM_Code2 = ?";
        String in = fromDate + "," + toDate + "," + rmCode;
        return AdminDb.execArrayLists(sql, 3, in, 12);
    }

    public static String getRmCode(String rmCode) {
        String sql = "select Branch from [dbo].[RM_Codelist] where RM_Code1 = ?";
        return AdminDb.getValue(sql, 1, 1, rmCode);
    }

    public static String getDate(String custId) {
        String sql = "select distinct Current_Week from [dbo].[CUSTOMER_VIEW]  where Customer_Id = ?";
        return AdminDb.getValue(sql, 1, 1, custId);
    }

    public static int getItemCounter(ArrayList list, String custId) {
        int count = 0;
        boolean found;
        for (int k = 0; k < list.size(); k++) {
            ArrayList one = (ArrayList) list.get(k);
            count++;
            found = one.get(0).equals(custId);
            if (found) {
                break;
            }
        }
        return count;
    }

    public static ArrayList fetchDailyList(String rmCode) {
        String sql = "select Customer_ID, RM_Code2,BM_Code,Regional_Manager_Code,Customer_Type,BM_Code1 from  [dbo].[Cust_Demo_1]  where RM_Code2 = ?  and Customer_ID not in (select Customer_ID from [dbo].[Customer_Track])";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 6);
    }

    public static String getCustomerByName(String name) {
        String sql = "select Name  from [dbo].[Cust_Demo_1]  where Name like '%" + name + "%'";
        return AdminDb.getValue(sql, 1, 0, "");
    }

    public static String getCustomerByID(String CustId) {
        String sql = "select Name  from [dbo].[Cust_Demo_1]  where Name like '%" + CustId + "%'";
        return AdminDb.getValue(sql, 1, 0, "");
    }

    public static ArrayList getOtherCustProducts(String custId) {
        String AGENCY_BANKING, BUSINESS_TRANSACTION, CALL_DEPOSIT, COLLECTION_SCHEME,
                FCY_TRANSACTION, FLEXI_DEPOSIT, HOUSING_LOAN, HP_LOAN, IMS, INSURANCE_PREMIUM,
                LOCKER_SECURITY, NGO, ONLINE_SAVERS, OVERDRAFT, PERSONAL_TRANSACTION, TERM_DEPOSIT,
                TERM_LOAN, TRADE_FINANCE, YOUNG_SAVERS, BANK_ASSURANCE, CREDIT_CARDS, PREPAID_CARDS, INTERNET_BANKING;
        String sql = "select AGENCY_BANKING,BUSINESS_TRANSACTION,CALL_DEPOSIT,COLLECTION_SCHEME,FCY_TRANSACTION,FLEXI_DEPOSIT,HOUSING_LOAN,HP_LOAN,IMS,INSURANCE_PREMIUM,LOCKER_SECURITY,NGO,ONLINE_SAVERS,OVERDRAFT,PERSONAL_TRANSACTION,TERM_DEPOSIT,TERM_LOAN,TRADE_FINANCE,YOUNG_SAVERS,BANK_ASSURANCE,CREDIT_CARDS,PREPAID_CARDS,INTERNET_BANKING from dbo.Customer_Product1 where CUST_ID =" + custId;
        ArrayList list = AdminDb.execArrayLists(sql, 0, "", 23);

        ArrayList current = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ArrayList one = (ArrayList) list.get(i);
            AGENCY_BANKING = (String) one.get(0);
            BUSINESS_TRANSACTION = (String) one.get(1);
            CALL_DEPOSIT = (String) one.get(2);
            COLLECTION_SCHEME = (String) one.get(3);
            FCY_TRANSACTION = (String) one.get(4);
            FLEXI_DEPOSIT = (String) one.get(5);
            HOUSING_LOAN = (String) one.get(6);
            HP_LOAN = (String) one.get(7);
            IMS = (String) one.get(8);
            INSURANCE_PREMIUM = (String) one.get(9);
            LOCKER_SECURITY = (String) one.get(10);
            NGO = (String) one.get(11);
            ONLINE_SAVERS = (String) one.get(12);
            OVERDRAFT = (String) one.get(13);
            PERSONAL_TRANSACTION = (String) one.get(14);
            TERM_DEPOSIT = (String) one.get(15);
            TERM_LOAN = (String) one.get(16);
            TRADE_FINANCE = (String) one.get(17);
            YOUNG_SAVERS = (String) one.get(18);
            BANK_ASSURANCE = (String) one.get(19);
            CREDIT_CARDS = (String) one.get(20);
            PREPAID_CARDS = (String) one.get(21);
            INTERNET_BANKING = (String) one.get(22);
            if (AGENCY_BANKING.equalsIgnoreCase("1")) {
                current.add("AGENCY_BANKING");
            }
            if (!BUSINESS_TRANSACTION.equalsIgnoreCase("1")) {
                current.add("BUSINESS_TRANSACTION");
            }
            if (!CALL_DEPOSIT.equalsIgnoreCase("1")) {
                current.add("CALL_DEPOSIT");
            }
            if (!COLLECTION_SCHEME.equalsIgnoreCase("1")) {
                current.add("COLLECTION_SCHEME");
            }
            if (!FCY_TRANSACTION.equalsIgnoreCase("1")) {
                current.add("FCY_TRANSACTION");
            }
            if (!FLEXI_DEPOSIT.equalsIgnoreCase("1")) {
                current.add("FLEXI_DEPOSIT");
            }
            if (!HOUSING_LOAN.equalsIgnoreCase("1")) {
                current.add("HOUSING_LOAN");
            }
            if (!HP_LOAN.equalsIgnoreCase("1")) {
                current.add("HP_LOAN");
            }
            if (!IMS.equalsIgnoreCase("1")) {
                current.add("IMS");
            }
            if (!INSURANCE_PREMIUM.equalsIgnoreCase("1")) {
                current.add("INSURANCE_PREMIUM");
            }
            if (!LOCKER_SECURITY.equalsIgnoreCase("1")) {
                current.add("LOCKER_SECURITY");
            }
            if (!NGO.equalsIgnoreCase("1")) {
                current.add("NGO");
            }
            if (!ONLINE_SAVERS.equalsIgnoreCase("1")) {
                current.add("ONLINE_SAVERS");
            }
            if (!OVERDRAFT.equalsIgnoreCase("1")) {
                current.add("OVERDRAFT");
            }
            if (!PERSONAL_TRANSACTION.equalsIgnoreCase("1")) {
                current.add("PERSONAL_TRANSACTION");
            }
            if (!TERM_DEPOSIT.equalsIgnoreCase("1")) {
                current.add("TERM_DEPOSIT");
            }
            if (!TERM_LOAN.equalsIgnoreCase("1")) {
                current.add("TERM_LOAN");
            }
            if (!TRADE_FINANCE.equalsIgnoreCase("1")) {
                current.add("TRADE_FINANCE");
            }
            if (!YOUNG_SAVERS.equalsIgnoreCase("1")) {
                current.add("YOUNG_SAVERS");
            }
            if (!BANK_ASSURANCE.equalsIgnoreCase("1")) {
                current.add("BANK_ASSURANCE");
            }
            if (!CREDIT_CARDS.equalsIgnoreCase("1")) {
                current.add("CREDIT_CARDS");
            }
            if (!PREPAID_CARDS.equalsIgnoreCase("1")) {
                current.add("PREPAID_CARDS");
            }
            if (!INTERNET_BANKING.equalsIgnoreCase("1")) {
                current.add("INTERNET_BANKING");
            }
        }
        return current;
    }

    public static ArrayList getCompleteList(String rmCode) {
        String sql = "select Customer_ID, Name, Permanent_phonenumber, Age, Marital_Status, City, Customer_Type, Permanent_Address,Occupation, Years_with_Bank, email_ID,AGENCY_BANKING, BUSINESS_TRANSACTION,CALL_DEPOSIT, COLLECTION_SCHEME, FCY_TRANSACTION, FLEXI_DEPOSIT, HOUSING_LOAN, HP_LOAN, IMS, INSURANCE_PREMIUM,LOCKER_SECURITY, NGO, ONLINE_SAVERS, OVERDRAFT, PERSONAL_TRANSACTION, TERM_DEPOSIT, TERM_LOAN, TRADE_FINANCE,YOUNG_SAVERS, BANK_ASSURANCE, CREDIT_CARDS, PREPAID_CARDS, INTERNET_BANKING from  [dbo].[CUSTOMER_PRODUCTS_VIEW]   where RM_Code =  ?";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 34);
    }

    public static ArrayList getCurrentProducts(String custId) {
        String AGENCY_BANKING, BUSINESS_TRANSACTION, CALL_DEPOSIT, COLLECTION_SCHEME,
                FCY_TRANSACTION, FLEXI_DEPOSIT, HOUSING_LOAN, HP_LOAN, IMS, INSURANCE_PREMIUM,
                LOCKER_SECURITY, NGO, ONLINE_SAVERS, OVERDRAFT, PERSONAL_TRANSACTION, TERM_DEPOSIT,
                TERM_LOAN, TRADE_FINANCE, YOUNG_SAVERS, BANK_ASSURANCE, CREDIT_CARDS, PREPAID_CARDS, INTERNET_BANKING;
        String sql = "select AGENCY_BANKING,BUSINESS_TRANSACTION,CALL_DEPOSIT,COLLECTION_SCHEME,FCY_TRANSACTION,FLEXI_DEPOSIT,HOUSING_LOAN,HP_LOAN,IMS,INSURANCE_PREMIUM,LOCKER_SECURITY,NGO,ONLINE_SAVERS,OVERDRAFT,PERSONAL_TRANSACTION,TERM_DEPOSIT,TERM_LOAN,TRADE_FINANCE,YOUNG_SAVERS,BANK_ASSURANCE,CREDIT_CARDS,PREPAID_CARDS,INTERNET_BANKING from dbo.Customer_Product1 where CUST_ID =" + custId;
        ArrayList list = AdminDb.execArrayLists(sql, 0, "", 23);

        ArrayList current = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ArrayList one = (ArrayList) list.get(i);
            AGENCY_BANKING = (String) one.get(0);
            BUSINESS_TRANSACTION = (String) one.get(1);
            CALL_DEPOSIT = (String) one.get(2);
            COLLECTION_SCHEME = (String) one.get(3);
            FCY_TRANSACTION = (String) one.get(4);
            FLEXI_DEPOSIT = (String) one.get(5);
            HOUSING_LOAN = (String) one.get(6);
            HP_LOAN = (String) one.get(7);
            IMS = (String) one.get(8);
            INSURANCE_PREMIUM = (String) one.get(9);
            LOCKER_SECURITY = (String) one.get(10);
            NGO = (String) one.get(11);
            ONLINE_SAVERS = (String) one.get(12);
            OVERDRAFT = (String) one.get(13);
            PERSONAL_TRANSACTION = (String) one.get(14);
            TERM_DEPOSIT = (String) one.get(15);
            TERM_LOAN = (String) one.get(16);
            TRADE_FINANCE = (String) one.get(17);
            YOUNG_SAVERS = (String) one.get(18);
            BANK_ASSURANCE = (String) one.get(19);
            CREDIT_CARDS = (String) one.get(20);
            PREPAID_CARDS = (String) one.get(21);
            INTERNET_BANKING = (String) one.get(22);
            if (AGENCY_BANKING.equalsIgnoreCase("1")) {
                current.add("AGENCY_BANKING");
            }
            if (BUSINESS_TRANSACTION.equalsIgnoreCase("1")) {
                current.add("BUSINESS_TRANSACTION");
            }
            if (CALL_DEPOSIT.equalsIgnoreCase("1")) {
                current.add("CALL_DEPOSIT");
            }
            if (COLLECTION_SCHEME.equalsIgnoreCase("1")) {
                current.add("COLLECTION_SCHEME");
            }
            if (FCY_TRANSACTION.equalsIgnoreCase("1")) {
                current.add("FCY_TRANSACTION");
            }
            if (FLEXI_DEPOSIT.equalsIgnoreCase("1")) {
                current.add("FLEXI_DEPOSIT");
            }
            if (HOUSING_LOAN.equalsIgnoreCase("1")) {
                current.add("HOUSING_LOAN");
            }
            if (HP_LOAN.equalsIgnoreCase("1")) {
                current.add("HP_LOAN");
            }
            if (IMS.equalsIgnoreCase("1")) {
                current.add("IMS");
            }
            if (INSURANCE_PREMIUM.equalsIgnoreCase("1")) {
                current.add("INSURANCE_PREMIUM");
            }
            if (LOCKER_SECURITY.equalsIgnoreCase("1")) {
                current.add("LOCKER_SECURITY");
            }
            if (NGO.equalsIgnoreCase("1")) {
                current.add("NGO");
            }
            if (ONLINE_SAVERS.equalsIgnoreCase("1")) {
                current.add("ONLINE_SAVERS");
            }
            if (OVERDRAFT.equalsIgnoreCase("1")) {
                current.add("OVERDRAFT");
            }
            if (PERSONAL_TRANSACTION.equalsIgnoreCase("1")) {
                current.add("PERSONAL_TRANSACTION");
            }
            if (TERM_DEPOSIT.equalsIgnoreCase("1")) {
                current.add("TERM_DEPOSIT");
            }
            if (TERM_LOAN.equalsIgnoreCase("1")) {
                current.add("TERM_LOAN");
            }
            if (TRADE_FINANCE.equalsIgnoreCase("1")) {
                current.add("TRADE_FINANCE");
            }
            if (YOUNG_SAVERS.equalsIgnoreCase("1")) {
                current.add("YOUNG_SAVERS");
            }
            if (BANK_ASSURANCE.equalsIgnoreCase("1")) {
                current.add("BANK_ASSURANCE");
            }
            if (CREDIT_CARDS.equalsIgnoreCase("1")) {
                current.add("CREDIT_CARDS");
            }
            if (PREPAID_CARDS.equalsIgnoreCase("1")) {
                current.add("PREPAID_CARDS");
            }
            if (INTERNET_BANKING.equalsIgnoreCase("1")) {
                current.add("INTERNET_BANKING");
            }
        }
        return current;
    }

    public static ArrayList getRMDetails(String rmCode) {
        String sql = "select distinct RM_Code,BM_Code1,Regional_Manager_Code,BM_Code1 FROM [dbo].[Cust_Demo_1] where RM_Code = ?";
        String in = rmCode;
        return AdminDb.execArrayLists(sql, 1, in, 4);
    }

    public static String getCategory(String Product) {
        String sql = "select Category from [dbo].[Product_type] where Product ==?";
        return AdminDb.getValue(sql, 1, 1, Product);
    }

    public static ArrayList getCustRmDetails(String Customer_ID) {
        String sql = "select distinct BM_Code,Regional_Manager_Code,Customer_Type FROM [dbo].[Cust_Demo_1] where Customer_ID = ?";
        String in = Customer_ID;
        return AdminDb.execArrayLists(sql, 1, in, 3);
    }

    public static void sellProducts(String list[], String Customer_ID) {
        if (list.length > 0) {
            String Product, Products_Category, Average_Value ;
           String BM_Code ="";
           String Regional_Manager_Code ="";
            String Customer_Type ="";
            ArrayList lst = getCustRmDetails(Customer_ID);
            for (int k = 0; k < lst.size(); k++) {

                ArrayList one = (ArrayList) lst.get(k);
                BM_Code = (String) one.get(0);
                Regional_Manager_Code = (String) one.get(1);
                Customer_Type = (String) one.get(2);

            }
            for (int i = 0; i < list.length; i++) {
                Product = list[i];
                Products_Category = getCategory(Product);
                Average_Value = "0";

                String sql = "insert into [dbo].[Products_Sold] (Customer_ID,Date_Sold,Product,"
                        + "Products_Category,Week_Sold,Average_Value,BM_Code,Regional_Manager_Code,"
                        + "Customer_Type) values(?,try_convert(varchar(11),getdate(),103),?,?"
                        + ",try_convert(varchar(11),getdate(),103),?,?,?,?)";

                String in = Customer_ID + "," + Product + "," + Products_Category + "," + Average_Value + "," + BM_Code + ","
                        + " " + Regional_Manager_Code + "," + Customer_Type;
                AdminDb.dbWork(sql, 7, in);
            }
        }
    }

    public static void updateProducts(String list[], String custId) {
        if (list.length > 0) {
            for (int i = 0; i < list.length; i++) {
                String product = list[i];
                String sql = "update  [dbo].[Customer_Product1] set ? ='1' where CUST_ID = ?";
                String in = product + "," + custId;
                AdminDb.dbWork(sql, 2, in);
            }
        }
    }

    public static void createNewCustomer(String Customer_ID, String rmCode) {
        ArrayList list = getRMDetails(rmCode);
        String RM_Code = "";
        String BM_Code = "";
        String Regional_Manager_Code = "";
        String BM_Code_Sustainability = "";

        String Customer_Type = "";
        for (int i = 0; i < list.size(); i++) {
            ArrayList one = (ArrayList) list.get(0);
            if (i == 10) {
                break;
            }
            RM_Code = (String) one.get(0);
            BM_Code = (String) one.get(1);
            Regional_Manager_Code = (String) one.get(2);
            BM_Code_Sustainability = (String) one.get(3);

        }
        Customer_Type = "BUSINESS BANKING";
        String branch = getRmCode(rmCode);
        String product_value = "23978";
        String sql = "insert into [dbo].[Customer_Tracking] "
                + "(Customer_ID , Filled_Date, Filled_Week,Client_Contacted,Sales_Commitment,"
                + "Docs_Submitted,Closed,Comments,Product_Sold,"
                + "RM_Code,Current_Week,BM_Code,"
                + "Regional_Manager_Code,Branch,Product_value,Customer_Type,BM_Code_Sustainability,"
                + "RegM_code_Sustainability ) values(?,try_convert(varchar(11),getdate(),103),"
                + "try_convert(varchar(11),getdate(),103),'No','No','No','No','None','None',"
                + "?,try_convert(varchar(11),getdate(),103),?,?,?,?,?,?,? )";

        String in = Customer_ID + "," + RM_Code + "," + BM_Code + "," + Regional_Manager_Code + "," + branch + ","
                + " " + product_value + "," + Customer_Type + "," + BM_Code_Sustainability + "," + RM_Code;
        AdminDb.dbWork(sql, 9, in);

    }

    public static void createTrack(String Customer_ID, String RM_Code) {
        String sql = "insert into [dbo].[Customer_Track] (Customer_ID,RM_Code,Filled_Date,Filled_Week)"
                + " values(?,?,try_convert(varchar(11),getdate(),103),try_convert(varchar(11),getdate(),103))";
        String in = Customer_ID + "," + RM_Code;
        AdminDb.dbWork(sql, 2, in);
    }

    public static void createDailyList(ArrayList list, String rmCode) {
        list = fetchDailyList(rmCode);

        int size = list.size();
        if (size > 0) {
            String Customer_ID, RM_Code, BM_Code, Regional_Manager_Code, Customer_Type, BM_Code_Sustainability;
            for (int i = 0; i < 10; i++) {
                ArrayList one = (ArrayList) list.get(i);

                Customer_ID = (String) one.get(0);
                RM_Code = (String) one.get(1);
                BM_Code = (String) one.get(2);
                Regional_Manager_Code = (String) one.get(3);
                Customer_Type = (String) one.get(4);
                BM_Code_Sustainability = (String) one.get(5);
                String product_value = "23978";
                String branch = getRmCode(rmCode);
                createTrack(Customer_ID, rmCode);
                String sql = "insert into [dbo].[Customer_Tracking] "
                        + "(Customer_ID , Filled_Date, Filled_Week,Client_Contacted,Sales_Commitment,"
                        + "Docs_Submitted,Closed,Comments,Product_Sold,"
                        + "RM_Code,Current_Week,BM_Code,"
                        + "Regional_Manager_Code,Branch,Product_value,Customer_Type,BM_Code_Sustainability,"
                        + "RegM_code_Sustainability ) values(?,try_convert(varchar(11),getdate(),103),"
                        + "try_convert(varchar(11),getdate(),103),'No','No','No','No','None','None',"
                        + "?,try_convert(varchar(11),getdate(),103),?,?,?,?,?,?,?)";

                String in = Customer_ID + "," + RM_Code + "," + BM_Code + "," + Regional_Manager_Code + "," + branch + ","
                        + " " + product_value + "," + Customer_Type + "," + BM_Code_Sustainability + "," + RM_Code;
                AdminDb.dbWork(sql, 9, in);
            }
        } else {

        }

    }

    public static String dateConverter(String date) {
        String yyyy = date.substring(0, 4);
        String MM = date.substring(5, 7);
        String dd = date.substring(8, 10);
        String newDate = dd + "/" + MM + "/" + yyyy;
        return newDate;
    }

    public static ArrayList getList() {
        String sql = "select Customer_ID,Client_Contacted ,Sales_Commitment,Docs_Submitted,Closed,Comments,Current_Week from [dbo].[Customer_Tracking] where Current_Week = '05/09/2017'  and RM_Code ='242'";
        return AdminDb.execArrayLists(sql, 0, "", 7);
    }

    public static void updateTracker(String custId, String clientcontacted, String salescommitment, String docsubmitted, String closed, String comments, String scheduledcalldate) {
        String today = sdf.format(new Date());
        String todays = "";
        String scheduleddate = "";
        String stroke = scheduledcalldate.substring(2, 3);
        String stroketoday = today.substring(2, 3);
        if (stroke.compareTo("/") != 0) {
            scheduleddate = dateConverter(scheduledcalldate);
        } else {
            scheduleddate = scheduledcalldate;
        }

        if (stroketoday.compareTo("/") != 0) {
            todays = dateConverter(today);
        } else {
            todays = today;
        }

        System.out.println("scheduleddate: " + scheduleddate);
        String sql = "update [dbo].[Customer_Tracking] set Client_Contacted=?,Sales_Commitment=?, Docs_Submitted=?,Closed=?,Comments=?,Filled_Week = ? "
                + "where Customer_ID =? and Filled_Week = ?";
        String in = clientcontacted + "," + salescommitment + "," + docsubmitted + "," + closed + "," + comments + "," + " " + scheduleddate + "," + custId + "," + todays;
        AdminDb.dbWork(sql, 8, in);
    }

    public static void updatePrevTracker(String custId, String clientcontacted, String salescommitment, String docsubmitted, String closed, String comments, String scheduledcalldate) {
        String scheduleddate = "";
        String stroke = scheduledcalldate.substring(2, 3);
        if (stroke.compareTo("/") != 0) {
            scheduleddate = dateConverter(scheduledcalldate);
        } else {
            scheduleddate = scheduledcalldate;
        }
        System.out.println("scheduleddate: " + scheduleddate);
        String sql = "update [dbo].[Customer_Tracking] set Client_Contacted=?,Sales_Commitment=?, Docs_Submitted=?,Closed=?,Comments=?,Filled_Week = ? "
                + "where Customer_ID =? and Filled_Week = ?";
        String in = clientcontacted + "," + salescommitment + "," + docsubmitted + "," + closed + "," + comments + "," + " " + scheduleddate + "," + custId + "," + scheduleddate;
        AdminDb.dbWork(sql, 8, in);
    }

    public static int addNewCustomer(String customerName, String email, String phone, String RmCode) {
        String sql = "insert into [dbo].[New_Customers] (Customer_Name,Contact_Number,"
                + "Email_ID,RM_Code,RM_Code1,Week) values(?,?,?,?,?,try_convert(datetime, GETDATE(), 121))";
        String in = customerName + "," + phone + "," + email + "," + RmCode + "," + RmCode;
        int n = AdminDb.dbWork(sql, 5, in);
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

//     public static   main(String[] args) { 
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
//   public static voi  main(String[] args) {
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
