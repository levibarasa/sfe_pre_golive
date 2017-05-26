package com.orig.gls.web.customer;

import com.orig.gls.dao.customer.Customer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Customerw {

    private static final Log log = LogFactory.getLog("origlogger");
    static int getAccDetails = 0;

    public static void handleGoCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("function");
            session.setAttribute("cfunction", function);
            session.setAttribute("modsuc", false);
            session.setAttribute("delsuc", false);
            session.setAttribute("fatal", false);
            switch (function) {
                case "ADD":
                    session.setAttribute("mapsuc", false);
                    session.setAttribute("mapErr", false);
                    session.setAttribute("subErr", false);
                    session.setAttribute("content_page", "customer/mUnMappedActs.jsp");
                    break;
                case "VERIFY":
                    session.setAttribute("content_page", "customer/mCustMod.jsp");
                    break;
                case "VERIFY MEMBER EXIT":
                    session.setAttribute("content_page", "customer/mCustModExit.jsp");
                    break;
                default:
                    session.setAttribute("content_page", "customer/mOtherActions.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleAddCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String account = request.getParameter("did");
            String smg = request.getParameter("subgroup");
            String actType = request.getParameter("custType");
            if (actType.equals(null)) {
                actType = "1";
            }
            System.out.println("Customer Number " + account);
            System.out.println("Customer Sub group " + smg);
            System.out.println("Customer type " + actType);
            session.setAttribute("mapsuc", false);
            session.setAttribute("mapErr", false);
            session.setAttribute("subErr", false);
            int Max =Customer.getMaxNumberOfGrpMembers(Customer.getGroupId(smg));
             int getNumberOfGrpMembers =Customer.getNumberOfGrpMembers(Customer.getGroupId(smg));
               int getMaxSbGrpMembers =Customer.getMaxSbGrpMembers(Customer.getGroupId(smg));
             int getNumberOfSbGrpMembers =Customer.getNumberOfSbGrpMembers(Customer.getGroupId(smg));
             Max =Max+1;
             getMaxSbGrpMembers=getMaxSbGrpMembers+1;
             getAccDetails = Customer.getAccountDetails(account, smg, "A", actType, (String) session.getAttribute("uname"));
            if (account != null && smg != null && !account.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                
                if ((getAccDetails > 0) && (getNumberOfGrpMembers < Max) && (getNumberOfSbGrpMembers < getMaxSbGrpMembers) ) {
                       Customer.addGroupMember(Customer.getGroupId(smg), (String) session.getAttribute("uname"));
                    System.out.println("Testing for any loop point two ");
                    session.setAttribute("mapsuc", true);
                    
                } else {
                    session.setAttribute("mapErr", true);
                }
                   
            } else {
                session.setAttribute("subErr", true);
            }
            session.setAttribute("content_page", "customer/mUnMappedActs.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
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
