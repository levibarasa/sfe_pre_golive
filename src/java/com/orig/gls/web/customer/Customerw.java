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
            System.out.println("Customer Number " + account);
            System.out.println("Customer Sub group " + smg);
            System.out.println("Customer type " + actType);
            session.setAttribute("mapsuc", false);
            session.setAttribute("mapErr", false);
            session.setAttribute("subErr", false);
            if (account != null && smg != null && !account.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                if (Customer.getAccountDetails(account, smg, "A",actType, (String) session.getAttribute("uname"))) {
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
            switch (function) {
                case "VERIFY":
                    Customer.verifyAccountDetails(accountNo,(String) session.getAttribute("uname"));
                    Customer.addAuditCustomerTrail(accountNo);
                    session.setAttribute("cverified", true);
                    session.setAttribute("content_page", "customer/mCustMod.jsp");
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
            System.out.println("Function selected " + function);
            String smg = request.getParameter(account + "subgroup");
            String actType = request.getParameter(account + "custType");
            switch (function) {
                case "MODIFY":
                    if (account != null && smg != null && !account.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                        if (Customer.getAccountDetails(account, smg, "M", actType, (String) session.getAttribute("uname"))) {
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
                    System.out.println("Doing member exit .... ");
                    if (account != null && smg != null && !account.equalsIgnoreCase("") && !smg.equalsIgnoreCase("")) {
                        if (Customer.getAccountDetails(account, smg, "R", actType, (String) session.getAttribute("uname"))) {
                            session.setAttribute("modsuc", true);
                        } else {
                            session.setAttribute("fatal", true);
                        }

                    } else {
                        session.setAttribute("fatal", true);
                    }
                    session.setAttribute("content_page", "mext/mRen.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
    // 
}
