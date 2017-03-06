package com.orig.gls.web.tran;

import com.orig.gls.dao.tran.Transact;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Transactionw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("tfunction");
            session.setAttribute("uverified", false);
            session.setAttribute("uadded", false);
            session.setAttribute("udeleted", false);
            session.setAttribute("ucancelled", false);
            session.setAttribute("umodified", false);
            session.setAttribute("fatal", false);
            switch (function) {
                case "ADD":
                    session.setAttribute("tfunction", function);
                    session.setAttribute("content_page", "tran/mTran_add.jsp");
                    break;
                case "VERIFY":
                    session.setAttribute("tfunction", function);
                    session.setAttribute("content_page", "tran/tVerify.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void getTranDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String function = (String) session.getAttribute("tfunction");
        if ((String) session.getAttribute("uname") != null) {
            String subgroup = request.getParameter("subgroup");
            String subgroupName = request.getParameter("subgroupName");
            String fromDate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            session.setAttribute("fromDate", fromDate);
            session.setAttribute("toDate", todate);
            session.setAttribute("subgroupCode", subgroup);
            session.setAttribute("subgroupName", subgroupName);
            switch (function) {
                case "ADD":
                    session.setAttribute("content_page", "tran/Tran_details.jsp");
                    break;
                case "VERIFY":
                    session.setAttribute("content_page", "tran/tranVerify.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("content_page", "tran/Tran_details.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("content_page", "tran/Tran_details.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void enterTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("uverified", false);
        session.setAttribute("uadded", false);
        session.setAttribute("udeleted", false);
        session.setAttribute("ucancelled", false);
        session.setAttribute("umodified", false);
        session.setAttribute("fatal", false);
        String uname = (String) session.getAttribute("uname");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        if ((String) session.getAttribute("uname") != null) {
            try {
                String amt = request.getParameter("amt");
                String actr = request.getParameter("actr");
                String dat = sdf.format(new Date());
                Date fDate = sdf.parse(dat);
                BigDecimal amts = new BigDecimal(amt);
                int lastId = Transact.getLastInsertId();
                String bankTranId = "PBUG" + lastId;
                String subgroup = request.getParameter("subgroup");
                String acnt = Transact.getDebitAccountNumber(subgroup);
                int k = Transact.addTranDetails(amts, fDate, acnt, "GLS LOAN REPAYMENT", uname, fDate, uname, fDate, "N", "N", "D", bankTranId, subgroup);
                if (k > 0) {
                    int n = Transact.addTranDetails(amts, fDate, actr, "GLS LOAN REPAYMENT", uname, fDate, uname, fDate, "N", "N", "C", bankTranId, subgroup);
                    if (n > 0) {
                        String acid = Transact.getAcid(actr);
                        Transact.deleteAfterVerification(acid);
                        session.setAttribute("tadded", true);
                    } else {
                        session.setAttribute("tfailed", true);
                    }
                }
                session.setAttribute("content_page", "tran/Tran_details.jsp");
            } catch (ParseException ex) {
                session.setAttribute("fatal", true);
                session.setAttribute("content_page", "tran/Tran_details.jsp");
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void postTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("uadded", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String subgroupCode = request.getParameter("subgroup");
            System.out.println("Sub group for posting ... " + subgroupCode);
            Transact.postTransacctions(subgroupCode);
            // session.setAttribute("content_page", "tran/tranVerify.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
