package com.orig.gls.dao.report;

import com.orig.gls.dao.reports.BeanFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Reports extends HttpServlet {

    public static void goReport(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        ServletContext context = request.getServletContext();
        session.setAttribute("dtErr", false);
        if ((String) session.getAttribute("uname") != null) {
            String rtype = request.getParameter("rFunction");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            Date fDate = new Date();
            Date tDate = new Date();
            try {
                fDate = sdf.parse(request.getParameter("fromdate"));
                tDate = sdf.parse(request.getParameter("todate"));
            } catch (ParseException asd) {
                System.out.println(asd.getMessage());
            }
            String subgroup = request.getParameter("subgroup");
            String path = (String) context.getAttribute("reportdir");
            String rname = rtype;
            if (IsDateValid(fDate) || IsDateValid(tDate)) {
                session.setAttribute("dtErr", true);
                session.setAttribute("content_page", "rpt/mHome.jsp");
            } else {
                Map p = new HashMap();
                String jrxmlurl;
                Collection col;
                switch (rtype) {
                    case "DISBURSEMENT":
                        col = BeanFactory.getDisbursementReport(subgroup, fDate, tDate);
                        jrxmlurl = "com/orig/gls/dao/reports/loanDisburse.jrxml";
                        break;
                    case "DEMANDS":
                        col = BeanFactory.getDemandsReport(subgroup, fDate, tDate);
                        jrxmlurl = "com/orig/gls/dao/reports/demandsRaised.jrxml";
                        break;
                    case "REPAYMENT":
                        col = BeanFactory.getRepaymentReport(subgroup, fDate, tDate);
                        jrxmlurl = "com/orig/gls/dao/reports/repayment.jrxml";
                        break;
                    case "REGISTRATION":
                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "A");
                        jrxmlurl = "com/orig/gls/dao/reports/Registration.jrxml";
                        break;
                    case "RE-INSTATED":
                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "R");
                        jrxmlurl = "com/orig/gls/dao/reports/reinstate.jrxml";
                        break;
                    case "EXITED":
                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "D");
                        jrxmlurl = "com/orig/gls/dao/reports/Exited.jrxml";
                        break;
                    default:
                        col = BeanFactory.getDisbursementReport(subgroup, fDate, tDate);
                        jrxmlurl = "com/orig/gls/dao/reports/loanDisburse.jrxml";
                        break;
                }
                GenRpt rf = new GenRpt();
                String pt = path + System.getProperty("file.separator") + rname + ".pdf";
                session.setAttribute("rpath", pt);
                rf.formatPurchaseOrder(jrxmlurl, path, rname, p, col);
                session.setAttribute("rname", rname);
                session.setAttribute("content_page", "rpt/mReport.jsp");
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static boolean IsDateValid(Date dates) {
        return dates.after(new Date());
    }
}
