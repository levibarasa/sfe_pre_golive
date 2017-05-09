package com.orig.gls.web.mext;

import com.orig.common.methods.CommonMethods;
import com.orig.gls.dao.customer.Customer;
import com.orig.gls.dao.mext.MemberReturn;
import com.orig.gls.dao.mext.Mext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MemberExitw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("function");
            session.setAttribute("uverified", false);
            session.setAttribute("uadded", false);
            session.setAttribute("udeleted", false);
            session.setAttribute("umodified", false);
            session.setAttribute("fatal", false);
            session.setAttribute("mextren", function);
            session.setAttribute("mrenfal", false);
            session.setAttribute("mextfal", false);
            session.setAttribute("mextsuc", false);
            session.setAttribute("content_page", "mext/mMembers.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void navigateAfterGroupSelect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        session.setAttribute("mextsuc", false);
        session.setAttribute("mextfal", false);
        session.setAttribute("mrenfal", false);
        session.setAttribute("fatal", false);
        String func = (String) session.getAttribute("mextren");
        String acctName = request.getParameter("custId");
        if ((String) session.getAttribute("uname") != null) {
            switch (func) {
                case "VOLUNTARY EXIT":
                  System.out.println("Cust id being exited " + acctName);
                    session.setAttribute("custId", acctName);
                    
                    session.setAttribute("content_page", "mext/mCuste_c.jsp");
                    break;
                case "DECEASED":
                    System.out.println("Cust id being exited " + acctName);
                    session.setAttribute("custId", acctName);
                    session.setAttribute("content_page", "mext/mCuste_c.jsp");
                    break;
                case "EXPULSION":
                    System.out.println("Cust id being exited " + acctName);
                    session.setAttribute("custId", acctName);
                    session.setAttribute("content_page", "mext/mCuste_c.jsp");
                    break;
                default:
                    session.setAttribute("account", acctName);
                    
                    System.out.println("Cust id being Re-instated " + acctName);
                    session.setAttribute("cfunction", "RE-INSTATEMENT");
                    session.setAttribute("content_page", "mext/mRen.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleExitMember(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String acctName = request.getParameter("accountNo");
            String func = (String) session.getAttribute("mextren");
            String uname = (String) session.getAttribute("uname");
            boolean hasLoan = CommonMethods.getMemberLoans(acctName);
            boolean computeBeforeExit;
           
            
            switch (func) {
                case "VOLUNTARY EXIT":
                    int exitMember = Mext.exitMember(acctName, uname,"V");
                    if (hasLoan) {
                        computeBeforeExit = true;
                        
                        if (exitMember > 0) {
                            Mext.markVoluntaryExit(acctName);
                        }
                    } else {
                        computeBeforeExit = false;
                    }

                    if (computeBeforeExit) {
                        int w = Mext.getSubGrpCode(acctName);
                        Customer.removeGroupMember(w, uname);
                        session.setAttribute("mextsuc", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");

                    } else {
                        session.setAttribute("mextfal", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");

                    }
                    session.setAttribute("content_page", "mext/mMembers.jsp");
                    break;
                case "DECEASED":
                      exitMember = Mext.exitMember(acctName, uname,"D");
                    if (hasLoan) {
                        computeBeforeExit = true;
                        if (exitMember > 0) {
                            Mext.markDeceased(acctName);
                        }
                    } else {
                        computeBeforeExit = false;
                    }
                    if (computeBeforeExit) {
                        int w = Mext.getSubGrpCode(acctName);
                        Customer.removeGroupMember(w, uname);
                        session.setAttribute("mextsuc", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");

                    } else {
                        session.setAttribute("mextfal", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");

                    }
                    session.setAttribute("content_page", "mext/mMembers.jsp");
                    break;

                case "EXPULSION":
                       exitMember = Mext.exitMember(acctName, uname,"E");
                    if (hasLoan) {
                        computeBeforeExit = true;
                        if (exitMember > 0) {
                            Mext.markExpelled(acctName);
                        }
                    } else {
                        computeBeforeExit = false;
                    }
                    if (computeBeforeExit) {
                        int w = Mext.getSubGrpCode(acctName);
                        Customer.removeGroupMember(w, uname);
                        session.setAttribute("mextsuc", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");

                    } else {
                        session.setAttribute("mextfal", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");

                    }
                    session.setAttribute("content_page", "mext/mMembers.jsp");
                    break;
                default:

                    int k = Integer.parseInt(acctName);
                    if (MemberReturn.retainClass(k, 200)) {
                        session.setAttribute("account", acctName);
                        session.setAttribute("cfunction", "RE-INSTATEMENT");
                        session.setAttribute("content_page", "mext/mRen.jsp");
                    } else {
                        session.setAttribute("mrenfal", true);
                        session.setAttribute("content_page", "mext/mMembers.jsp");
                    }
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
