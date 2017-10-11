package com.sfe.dao.report;

import com.sfe.dao.customer.Customer;
import com.sfe.dao.reports.BeanFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        if ( (String) session.getAttribute("uname") != null) {
            String rtype = request.getParameter("rFunction");
            rtype = rtype.trim();
            String rtpfmt = request.getParameter("rtpfmt");
            String region = request.getParameter("region");
            String branch = request.getParameter("branch");
            String segment = request.getParameter("segment");
               
            String fDate = request.getParameter("fromdate");
            String tDate = request.getParameter("todate");

            String rmCode = request.getParameter("rmName");
            int rmCod = Integer.parseInt(rmCode);
            rmCode = String.valueOf(rmCod);
            System.out.println("rmCode: " + rmCode);
            String path = (String) context.getAttribute("reportdir");
            String rname = rtype;
            Map p = new HashMap();
            String jrxmlurl = "";
            Collection col = new ArrayList();
            System.out.println("Function is: " + rname);
            System.out.println("From Date:" + fDate);
            System.out.println("To Date:" + tDate);
            rtype = rtype.trim();
 
            switch (rtype) {
                case "SalesPipeline": 
                    if(region.equalsIgnoreCase("ALL") && branch.equalsIgnoreCase("ALL") && segment.equalsIgnoreCase("ALL")){
                    col = BeanFactory.getRMSaleByAllRegions(fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                    } else if(branch.equalsIgnoreCase("ALL") && segment.equalsIgnoreCase("ALL")){
                    col = BeanFactory.getRMSaleByRegion(region, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                    }else if(segment.equalsIgnoreCase("ALL")){
                    col = BeanFactory.getRMSaleByBranch(branch, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                    }else{
                    col = BeanFactory.getRMSales(rmCode, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                      }
                       break;
                case "RM-SalesPipeline":
                    if(region.equalsIgnoreCase("ALL") && branch.equalsIgnoreCase("ALL") && segment.equalsIgnoreCase("ALL")){
                    col = BeanFactory.getRMSaleByAllRegions(fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                    } else if(branch.equalsIgnoreCase("ALL") && segment.equalsIgnoreCase("ALL")){
                    col = BeanFactory.getRMSaleByRegion(region, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                    }else if(segment.equalsIgnoreCase("ALL")){
                    col = BeanFactory.getRMSaleByBranch(branch, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                    }else{
                    col = BeanFactory.getRMSales(rmCode, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/Sales.jrxml";
                      }
                 break;
                 case "Performance Dashboard":
                     col = BeanFactory.getSalesPerformance(rmCode, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/performancedash.jrxml";
                 break;
                default:

                    col = BeanFactory.getSalesPipeline(rmCode, fDate, tDate);
                    jrxmlurl = "com/sfe/dao/reports/salespipeline.jrxml";
                    break;

            }
            rtpfmt = rtpfmt.trim();
            GenRpt rf = new GenRpt();
            String format = "";
            switch (rtpfmt) {
                case "PDF":
                    format = ".pdf";
                    String pt = path + System.getProperty("file.separator") + rname + format;
                    session.setAttribute("rpath", pt);
                    break;
                default:
                    format = ".xlsx";
                    pt = path + System.getProperty("file.separator") + rname + ".xlsx";
                    session.setAttribute("rpath", pt);
                    break;
            }
            String rformat = rtpfmt;
            session.setAttribute("rformat", rtpfmt);

            rf.formatPurchaseOrder(jrxmlurl, path, rname, p, col, format);

            session.setAttribute("rname", rname);
            session.setAttribute("content_page", "mReport.jsp");
            response.sendRedirect("mReport.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        //response.sendRedirect("crptDisplay.jsp");
        // response.sendRedirect("index.jsp");
    }

    public static String dateNConverter(String date) {
        String yyyy = date.substring(0, 4);
        String MM = date.substring(5, 7);
        String dd = date.substring(8, 10);
        String newDate = dd + "/" + MM + "/" + yyyy;
        return newDate;
    }

    public static String dateConverter(String date) {
        String yyyy = date.substring(7, 11);
        String MM = "";
        String month = date.substring(3, 6);

        if (month.equalsIgnoreCase("JAN")) {
            MM = "01";
        } else if (month.equalsIgnoreCase("FEB")) {
            MM = "02";
        } else if (month.equalsIgnoreCase("MAR")) {
            MM = "03";
        } else if (month.equalsIgnoreCase("APR")) {
            MM = "04";
        } else if (month.equalsIgnoreCase("MAY")) {
            MM = "05";
        } else if (month.equalsIgnoreCase("JUN")) {
            MM = "06";
        } else if (month.equalsIgnoreCase("JUL")) {
            MM = "07";
        } else if (month.equalsIgnoreCase("AUG")) {
            MM = "08";
        } else if (month.equalsIgnoreCase("SEP")) {
            MM = "09";
        } else if (month.equalsIgnoreCase("OCT")) {
            MM = "10";
        } else if (month.equalsIgnoreCase("NOV")) {
            MM = "11";
        } else if (month.equalsIgnoreCase("DEC")) {
            MM = "12";
        }
        String dd = date.substring(0, 2);
        String newDate = dd + "/" + MM + "/" + yyyy;
        return newDate;
    }

}
