//package com.sfe.dao.report;
//
//import com.sfe.dao.reports.BeanFactory;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class Reports extends HttpServlet {
//
//    public static void handleGoReport(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        session.setAttribute("dtErr", false);
//        if ((String) session.getAttribute("uname") != null) {
//            String function = request.getParameter("rFunction");
//            String rtpfmt = request.getParameter("rtpfmt");
//            function = function.trim();
//
//            switch (function) {
//                case "GROUP-LOAN-STATUS":
//                    session.setAttribute("content_page", "rpt/mHomegrp.jsp");
//                    session.setAttribute("rFunction", function);
//                    session.setAttribute("rtpfmt", rtpfmt);
//                    break;
//
//                default:
//                    session.setAttribute("content_page", "rpt/rptHome.jsp");
//                    session.setAttribute("rFunction", function);
//                    session.setAttribute("rtpfmt", rtpfmt);
//                    break;
//            }
//        } else {
//            session.setAttribute("content_page", "sessionexp.jsp");
//        }
//        response.sendRedirect("index.jsp");
//    }
//
//    public static void goReport(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        ServletContext context = request.getServletContext();
//        session.setAttribute("dtErr", false);
//        if ((String) session.getAttribute("uname") != null) {
//            String rtype = request.getParameter("rFunction");
//            rtype = rtype.trim();
//
//            if (rtype.equalsIgnoreCase("GROUP-LOAN-STATUS")) {
//                session.setAttribute("content_page", "rpt/mHomegrp.jsp");
//            } else {
//                session.setAttribute("content_page", "rpt/rptHome.jsp");
//            }
//
//            String rtpfmt = request.getParameter("rtpfmt");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//            SimpleDateFormat in = new SimpleDateFormat("dd-MMM-yyyy");
//
//            String fDate = request.getParameter("fromdate");
//            String tDate = request.getParameter("todate");
//
//            try {
//                Date f_Date = in.parse(fDate);
//                Date t_Date = in.parse(tDate);
//
//                fDate = sdf.format(f_Date);
//                tDate = sdf.format(t_Date);
//
//            } catch (ParseException asd) {
//                System.out.println(asd.getMessage());
//            }
//            System.out.println("From Date:" + fDate);
//            System.out.println("To Date:" + tDate);
//            String subgroup = request.getParameter("subgroup");
//            String groupcode = request.getParameter("groupcode");
//
//            String path = (String) context.getAttribute("reportdir");
//            String rname = rtype;
//            if (IsDateValid(fDate) || IsDateValid(tDate)) {
//                session.setAttribute("dtErr", true);
//                session.setAttribute("content_page", "rpt/mHome.jsp");
//            } else {
//                Map p = new HashMap();
//                String jrxmlurl = "";
//                Collection col = new ArrayList();
//                System.out.println("Function is: " + rname);
//                rtype = rtype.trim();
//                switch (rtype) {
//                    case "NEW-MEMBER":
//                        col = BeanFactory.getNewMemberReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/newclientregister.jrxml";
//                        break;
//
//                    case "DISBURSEMENT":
//                        col = BeanFactory.getDisbursementReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/disbursedloanregister.jrxml";
//                        break;
//
//                    case "DEMANDS":
//                        col = BeanFactory.getDemandsReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/demandsRaised.jrxml";
//                        break;
//
//                    case "REPAYMENT":
//                        col = BeanFactory.getRepaymentReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/repayment.jrxml";
//                        break;
//
//                    case "REGISTRATION":
//                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "A");
//                        jrxmlurl = "com/orig/gls/dao/reports/Registration.jrxml";
//                        break;
//
//                    case "RE-INSTATED":
//                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "R");
//                        jrxmlurl = "com/orig/gls/dao/reports/reinstate.jrxml";
//                        break;
//
//                    case "EXITED":
//                        String[] status = {"V", "D", "E"};
//                        for (String s : status) {
//                            col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, s);
//                            jrxmlurl = "com/orig/gls/dao/reports/Exited.jrxml";
//                        }
//                        break;
//
//                    case "PROJECTIONS":
//                        col = BeanFactory.getProjectionReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/grouploanprojection.jrxml";
//                        break;
//
//                    case "OFFICER-MONITORING":
//                        col = BeanFactory.getOfficerMonitoringReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/officermonitoring.jrxml";
//                        break;
//
//                    case "LOAN-AND-SAVING-PORTFOLIO":
//                        col = BeanFactory.getLoanSavingsReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/loanandsavingportfolio.jrxml";
//                        break;
//
//                    case "WRITTEN-OFF":
//                        col = BeanFactory.getWriteOffReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/writtenoffloans.jrxml";
//                        break;
//
//                    case "PORTFOLIO-STATUS ":
//                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "D");
//                        jrxmlurl = "com/orig/gls/dao/reports/portfoliostatus.jrxml";
//                        break;
//
//                    case "RETENTION-RATES":
//                        col = BeanFactory.getRetentionReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/retensionrate.jrxml";
//                        break;
//
////                    case "DISBURSED-LOAN-REGISTER":
////                        col = BeanFactory.getDisbursementReport(subgroup, fDate, tDate);
////                        jrxmlurl = "com/orig/gls/dao/reports/loanDisburse.jrxml";
////                        break;
//                    case "COMPULSARY-SAVING-WITHDRAWAL":
//                        col = BeanFactory.getCompulsarySavingWithdrawalReport(subgroup, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/compulsarysavingwithdrawal.jrxml";
//                        break;
//
//                    case "GROUP-LOAN-STATUS":
//                        col = BeanFactory.getGroupLoanStatusReport(groupcode, fDate, tDate);
//                        jrxmlurl = "com/orig/gls/dao/reports/grouploanstatus.jrxml";
//                        break;
//
//                    default:
//                        col = BeanFactory.getCustomerReport(subgroup, fDate, tDate, "A");
//                        jrxmlurl = "com/orig/gls/dao/reports/Registration.jrxml";
//                        break;
//
//                }
//                rtpfmt = rtpfmt.trim();
//                GenRpt rf = new GenRpt();
//                String format = "";
//                switch (rtpfmt) {
//                    case "PDF":
//                        format = ".pdf";
//                        String pt = path + System.getProperty("file.separator") + rname + format;
//                        session.setAttribute("rpath", pt);
//                        break;
//                    default:
//                        format = ".xlsx";
//                        pt = path + System.getProperty("file.separator") + rname + ".xlsx";
//                        session.setAttribute("rpath", pt);
//                        break;
//                }
//                String rformat = rtpfmt;
//                session.setAttribute("rformat", rtpfmt);
//                rf.formatPurchaseOrder(jrxmlurl, path, rname, p, col, format);
//                session.setAttribute("rname", rname);
//                session.setAttribute("content_page", "rpt/mReport.jsp");
//            }
//        } else {
//            session.setAttribute("content_page", "sessionexp.jsp");
//        }
//        response.sendRedirect("index.jsp");
//    }
//
//    public static boolean IsDateValid(String dates) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        Date selectDate = new Date();
//        try {
//            selectDate = sdf.parse(dates);
//        } catch (ParseException asd) {
//            System.out.println(asd.getMessage());
//        }
//        return selectDate.after(new Date());
//    }
//}
