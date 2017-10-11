package com.sfe.web.customer;

import com.sfe.dao.customer.Customer; 
import static com.sfe.dao.customer.Customer.getRM_Name;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Customerw {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); 
     
    static int getAccDetails = 0;

    public static void handleAddExistCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String custId = request.getParameter("custId");
         
        if ((String) session.getAttribute("uname") != null) {
            String rmCode = request.getParameter("rmCode");

            Customer.createNewExistingCustomer(custId, rmCode);

            session.setAttribute("content_page", "weeklycalllist.jsp");
            session.setAttribute("uname", rmCode);
            // session.setAttribute("rmCode", rmCode);
            response.sendRedirect("weeklycalllist.jsp");

        } else {
            session.setAttribute("content_page", "weeklycalllist.jsp");
        }
        //  session.setAttribute("content_page", "populatelist.jsp");
        //response.sendRedirect("weeklycalllist.jsp");
    }

    public static void handleDoSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        String custId = request.getParameter("custId");
         
        if ((String) session.getAttribute("uname") != null) {
            String rmCode = request.getParameter("rmCode");

            Customer.createNewCustomer(custId, rmCode, "");

            session.setAttribute("content_page", "weeklycalllist.jsp");
            session.setAttribute("uname", rmCode);
            // session.setAttribute("rmCode", rmCode);
            response.sendRedirect("weeklycalllist.jsp");

        } else {
            session.setAttribute("content_page", "weeklycalllist.jsp");
        }
        //  session.setAttribute("content_page", "populatelist.jsp");
        //response.sendRedirect("weeklycalllist.jsp");
    }

    public static void handleGenerateList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
 
        if ((String) session.getAttribute("uname") != null) {

             String rmCode = request.getParameter("rmCode");
             rmCode = (String) session.getAttribute("uname");
            System.out.println("RmCode :" + rmCode);

            ArrayList list = Customer.fetchDailyList(rmCode);
             System.out.println("List to be generated: "+list.size());
            Customer.createDailyList(list, rmCode);
            Customer.populateDailyList(rmCode);
            session.setAttribute("content_page", "weeklycalllist.jsp");
            session.setAttribute("uname", rmCode);
            session.setAttribute("rmCode", rmCode);
            response.sendRedirect("weeklycalllist.jsp");
        } else {
            session.setAttribute("content_page", "weeklycalllist.jsp");
        }
        //  session.setAttribute("content_page", "populatelist.jsp");
        //response.sendRedirect("weeklycalllist.jsp");
    }
    
    
 
    public static void handleAddNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
 
        if ((String) session.getAttribute("uname") != null) {
        String custId = request.getParameter("custId");
        String today = sdf.format(new Date()); 
            String rmCode = request.getParameter("rmCode");
            String product = request.getParameter("product");
            String leadsrc = request.getParameter("leadsrc");
            Customer.deleteDefaultTrack(custId); 
            Customer.createNewExistingCustomerProduct(custId, rmCode,product,leadsrc);
              session.setAttribute("uname", rmCode);
            session.setAttribute("rmCode", rmCode);
            session.setAttribute("custId", custId); 
            
    
         } else { 
        } 
        response.sendRedirect("addproductclose.jsp");
    }
    public static void handleAddNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        
        if ((String) session.getAttribute("uname") != null) {

            String RmCode = request.getParameter("rmCode");
            System.out.println("RmCode " + RmCode);
            String phone = request.getParameter("phoneNo");
            String email = request.getParameter("emailId");
            String custName = request.getParameter("cust_name");
            int n = Customer.addNewCustomer(custName, email, phone, RmCode);
            int custIdTemp = Customer.getNewTempCustId();
            String Customer_ID = String.valueOf(custIdTemp);
            String Customer_Type =" ";
             String rmName = "";
            if (n > 0) {
                session.setAttribute("custadd", true);
                session.setAttribute("rmCode", RmCode);
                  
                Customer.createNewCustomer(Customer_ID, RmCode, custName);
                rmName = getRM_Name(RmCode);
                 Customer.createCustProd(RmCode, rmName, Customer_ID, custName, Customer_Type);
            }

        } else {
              }
        response.sendRedirect("updatetrackerclose.jsp");
    }

    public static void handleUpdatePrevCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("custracker", false);
        String productvalue = request.getParameter("productvalue");
        String currency = request.getParameter("currency");
        String rmCode = request.getParameter("rmCode");
        String product = request.getParameter("product");
        String marketedProd = request.getParameter("marketedProduct");
        String custId = "";
        String clientcontacted, salescommitment, docsubmitted, closed, comments, scheduledcalldate;
        custId = request.getParameter("custId");
        clientcontacted = request.getParameter("clientcontacted");
        salescommitment = request.getParameter("salescommitment");
        docsubmitted = request.getParameter("docsubmitted");
        closed = request.getParameter("closed");
        comments = request.getParameter("comments");
        scheduledcalldate = request.getParameter("scheduledcalldate");
        String today = sdf.format(new Date());
        ArrayList list = Customer.populateDailyList(rmCode);
        int counter = Customer.getItemCounter(list, custId);
        System.out.println("product :" + product);
        System.out.println("CustId : " + custId); 
        if (comments == "") {
            comments = "None";
        }
        if (scheduledcalldate == "") {
            scheduledcalldate = today;
        }
         
        System.out.println("marketedProd: " + marketedProd);
        System.out.println("scheduledcalldate : " + scheduledcalldate);
        session.setAttribute("uname", rmCode);
        System.out.println(" From UI" + custId + "  " + clientcontacted + "  " + salescommitment + "  " + docsubmitted + "  " + closed + "  " + comments + "  " + scheduledcalldate + "  " + currency + "  " + productvalue + "  " + marketedProd);
        Customer.updatePrevTracker(custId, clientcontacted, salescommitment, docsubmitted, closed, comments, scheduledcalldate, currency, productvalue, marketedProd);
        Customer.sellProducts(product, custId, productvalue, scheduledcalldate);
        Customer.updateProducts(product, custId);

        session.setAttribute("rmCode", rmCode);
        session.setAttribute("fromdate", scheduledcalldate);
        session.setAttribute("todate", scheduledcalldate);
        int unam = (int) session.getAttribute("uname");
        
        if ((String) session.getAttribute("uname") != null) {

            response.sendRedirect("updatetrackerclose.jsp");

            //  session.setAttribute("content_page", "updatetrackerclose.jsp");
        }
        session.setAttribute("content_page", "updatetrackerclose.jsp");
        // response.sendRedirect("populatelist.jsp");
    }

    public static void handleUpdateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("custracker", false);
         
        if ((String) session.getAttribute("uname") != null) {
            boolean updated = false;
            String productvalue = request.getParameter("productvalue");
            String currency = request.getParameter("currency");
            String rmCode = request.getParameter("rmCode");
            String product = request.getParameter("product");
            String productsold = request.getParameter("productsold");  
            String leadsrc = request.getParameter("leadsrc");
            String trackid = request.getParameter("trackid");
            String custId = "";
            String today = sdf.format(new Date());
            String clientcontacted, salescommitment, docsubmitted, closed, comments, scheduledcalldate;
            custId = request.getParameter("custId");
            System.out.println("custId :" + custId);
            System.out.println("product :" + product); 
            clientcontacted = request.getParameter("clientcontacted");
            salescommitment = request.getParameter("salescommitment");
            docsubmitted = request.getParameter("docsubmitted");
            closed = request.getParameter("closed");
            comments = request.getParameter("comments");
            scheduledcalldate = request.getParameter("scheduledcalldate");
            System.out.println("clientcontacted :" + clientcontacted);
            if (comments == "") {
                comments = "None";
            }  

            ArrayList list = Customer.populateDailyList(rmCode);
            int counter = Customer.getItemCounter(list, custId);
            System.out.println("CustId : " + custId);
            System.out.println(" From UI :" + custId + "  " + clientcontacted + "  " + salescommitment + "  " + docsubmitted + "  " + closed + "  " + comments + "  " + scheduledcalldate + "  " + currency + "  " + productvalue + "  " + productsold + "  " +leadsrc + "  " +trackid);
            Customer.updateTracker(custId, clientcontacted, salescommitment, docsubmitted, closed, comments, scheduledcalldate, currency, productvalue, product,leadsrc,trackid,productsold);
          if(productsold.equalsIgnoreCase("Yes")){
            Customer.sellProducts(product, custId, productvalue, scheduledcalldate);
            Customer.updateProducts(product, custId);
                }
            session.setAttribute("rmCode", rmCode);
            response.sendRedirect("updatetrackerclose.jsp");
            session.setAttribute("custracker", true);
            session.setAttribute("content_page", "updatetrackerclose.jsp");

        }
        // session.setAttribute("content_page", "populatelist.jsp");
        // response.sendRedirect("populatelist.jsp");
    }

    public static void handleMaintainCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("cverified", false);
        session.setAttribute("cadded", false);
        session.setAttribute("cdeleted", false);
        session.setAttribute("ccancelled", false);
        session.setAttribute("cmodified", false);
        session.setAttribute("modsuc", false);
        session.setAttribute("delsuc", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String accountNo = request.getParameter("did");
            String function = (String) session.getAttribute("cfunction");
            switch (function) {//
                case "VERIFY":
                    Customer.verifyAccountDetails(accountNo, (String) session.getAttribute("uname"));
                    Customer.addAuditCustomerTrail(accountNo);
                    session.setAttribute("cverified", true);
                    session.setAttribute("content_page", "customer/mCustMod.jsp");
                    break;
                case "VERIFY MEMBER EXIT":
                    Customer.verifyAccountDetails(accountNo, (String) session.getAttribute("uname"));
                    Customer.addAuditCustomerTrail(accountNo);
                    session.setAttribute("cmexitverified", true);
                    session.setAttribute("content_page", "customer/mCustModExit.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("account", accountNo);
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
                case "EXIT MEMBER":
                    session.setAttribute("account", accountNo);
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("account", accountNo);
                    session.setAttribute("content_page", "customer/mCustomer_b.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    // Modify all customer account mappings by logical delete or modify
    public static void handleModifyCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("modsuc", false);
        session.setAttribute("delsuc", false);
        session.setAttribute("fatal", false);

        if ((String) session.getAttribute("uname") != null) {

            String account = request.getParameter("accountNo");
            String function = (String) session.getAttribute("cfunction");
            String smg = request.getParameter(account + "subgroup");
            String actType = request.getParameter(account + "custType");
            String memberStatus = Customer.getMemberStatus(account);
            if (actType.isEmpty()) {
                actType = "1";
            }
            System.out.println("Function selected " + function);
            System.out.println("Account selected " + account);
            System.out.println("smg selected " + smg);
            System.out.println("actType selected " + actType);

            switch (function) {
                case "MODIFY":
                    getAccDetails = Customer.getAccountDetails(account, smg, "M", actType, (String) session.getAttribute("uname"));
                    if (account != null && smg != null && !account.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                        if (getAccDetails > 0) {
                            Customer.addAuditCustomerReinstateTrail(account);
                            session.setAttribute("modsuc", true);
                        } else {
                            session.setAttribute("fatal", true);
                        }
                    } else {
                        session.setAttribute("fatal", true);
                    }
                    session.setAttribute("content_page", "customer/mOtherActions.jsp");
                    break;
                default:
                    getAccDetails = Customer.getAccountDetails(account, smg, "A", actType, (String) session.getAttribute("uname"));
                    System.out.println("Doing member Re-Instatement .... ");
                    if (account != null && smg != null && !account.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                        if (getAccDetails > 0) {
                            session.setAttribute("modsuc", true);
                            session.setAttribute("content_page", "customer/mMembers.jsp");
                        } else {
                            session.setAttribute("content_page", "mext/mCuste_a.jsp");
                            session.setAttribute("fatal", true);
                        }

                    } else {
                        session.setAttribute("content_page", "mext/mCuste_a.jsp");
                        session.setAttribute("fatal", true);
                    }
                    session.setAttribute("content_page", "mext/mCuste_a.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
    // 
}
