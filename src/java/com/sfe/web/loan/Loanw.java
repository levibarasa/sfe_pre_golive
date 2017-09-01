package com.orig.gls.web.loan;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Loanw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoLoanMapping(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("lnFunction");
            session.setAttribute("uverified", false);
            session.setAttribute("uadded", false);
            session.setAttribute("udeleted", false);
            session.setAttribute("ucancelled", false);
            session.setAttribute("umodified", false);
            session.setAttribute("fatal", false);
            session.setAttribute("lnFunction", function);
            session.setAttribute("content_page", "loan/mLoan_add.jsp");

        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleModifyTransaction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String group = request.getParameter("subgroupName");
            String subgroup = request.getParameter("subgroup");
            String fromDate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            session.setAttribute("fromDate", fromDate);
            session.setAttribute("toDate", todate);
            session.setAttribute("subgroupName", group);
            session.setAttribute("subgroupCode", subgroup);
            String func = (String) session.getAttribute("lnFunction");
            switch (func) {
                case "DISBURSEMENTS":
                    session.setAttribute("content_page", "loan/Loan_details.jsp");
                    break;
                default:
                    session.setAttribute("content_page", "loan/demands.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
