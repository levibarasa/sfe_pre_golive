//package com.sfe.web.user;
//
//import com.sfe.dao.admin.user.Access;
//import com.sfe.dao.admin.user.User;
//import com.sfe.security.Encode;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Locale;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import com.sfe.dao.admin.user.LdapLogin;
//
//public class AccesswLDAP {
//
//    private static final Log log = LogFactory.getLog("origlogger");
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//
//    public static void handleLogin(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        
//        String username = request.getParameter("username");
//        String password = request.getParameter("Password");
//        String employeeID = "";
//        LdapLogin ldapExaminer = new LdapLogin();
//        String loggedInUser = ldapExaminer.authUser(username, password, ldapExaminer.getLdapContext());
//        System.out.println("Logged in User: " + loggedInUser); 
//        String testUser1 = "soumya.rao";
//        String testUser = "nicholast";  
//          session.setAttribute("loggedInUser", loggedInUser);
////                if (loggedInUser != null && conPage == null) {
////          employeeID = Access.getRMCodeByWindowsUserName(loggedInUser);
////            session.setAttribute("uname", employeeID);
////            session.setAttribute("rmCode", employeeID);
////            conPage ="index.jsp";
////            session.setAttribute("content_page",conPage);
////                      }
//        int userId;
//        SimpleDateFormat in = new SimpleDateFormat("dd-MMM-yyyy");
//        String branch, region;
//        branch = "Head Office";
//        region = "Head Office";   
//        if (!loggedInUser.equalsIgnoreCase("") || !loggedInUser.equalsIgnoreCase(null)) {
//              employeeID = Access.getRMCodeByWindowsUserName(loggedInUser);
//
//            userId = Integer.parseInt(employeeID);
//            Date currdate = new Date();
//            String roleid = "";
//            String roleName = "";
//            System.out.println("RmCode: " + employeeID);
//            String employeeCode, employeeName, accessLevel;
//            System.out.println("employeeID: "+employeeID); 
//            session.setAttribute("uname", employeeID);
//            session.setAttribute("rmCode", employeeID);
//            Access.MarkloginUser(employeeID, new Date());
//            ArrayList ar = User.getUserDetails(String.valueOf(userId));
//            System.out.println(ar);
//            for (int i = 0; i < ar.size(); i++) {
//                ArrayList one = (ArrayList) ar.get(i);
//                employeeCode = (String) one.get(0);
//                employeeName = (String) one.get(1);
//                accessLevel = (String) one.get(2);
//                Access.loginUser(employeeCode, employeeName, accessLevel);
//                branch = User.getBranch(employeeID);
//                region = User.getRegion(employeeID);
//
//                session.setAttribute("name", employeeName);
//                session.setAttribute("code", employeeCode);
//                session.setAttribute("branch", branch);
//                session.setAttribute("region", region);
//                session.setAttribute("accessLevel", accessLevel);
//                session.setAttribute("userloggedin", true);
//                String conPage = (String) session.getAttribute("content_page");
//                 
//                  System.out.println("conPage ndaani: "+conPage);
//                if (accessLevel.equalsIgnoreCase("1") && conPage == null) {
//                    response.sendRedirect("indexAdmin.jsp");
//                    System.out.println("conPage => "+conPage);
//                    if(conPage == null){
//                    session.setAttribute("content_page","indexAdmin.jsp");
//                    }
//                } else if (accessLevel.equalsIgnoreCase("2") && conPage == null) {
//                    response.sendRedirect("index.jsp");
//                    System.out.println("conPage => "+conPage);
//                    if(conPage == null){
//                    session.setAttribute("content_page","index_content.jsp");
//                    } 
//                } else if (accessLevel.equalsIgnoreCase("3") && conPage == null) {
//                    response.sendRedirect("indexNationalHead.jsp");
//                    System.out.println("conPage => "+conPage);
//                     if(conPage == null){
//                    session.setAttribute("content_page","indexNationalHead.jsp");
//                    } 
//                      } else if(conPage == null){
//                    session.setAttribute("content_page", "login.jsp");
//                    response.sendRedirect("login.jsp");
//                }
//            }
//
//        } else {
//            session.setAttribute("userdnexists", true);
//            response.sendRedirect("login.jsp");
//        }
//    }
//
//    public static void handleLogout(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        if (session.getAttribute("uname") != null) {
//            Access.logoutUser((String) session.getAttribute("uname"));
//            //session.setAttribute("content_page", "login.jsp");
//            response.sendRedirect("login.jsp");
//            session.invalidate();
//        } else {
//            //session.setAttribute("content_page", "sessionexp.jsp");
//            response.sendRedirect("login.jsp");
//        }
//    }
//
//    public static void handleSac(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        String function = request.getParameter("rFunction");
//
//        session.setAttribute("sacsuc", false);
//        if (session.getAttribute("uname") != null) {
//
//            String uname = request.getParameter("username");
//            switch (function) {
//                case "DELETE":
//                    Access.logoutUser(uname);
//                    session.setAttribute("sacsuc", true);
//                    break;
//                case "UNLOCK":
//                    Access.markCorrectLoginAttempt(uname);
//                    session.setAttribute("sacsuc", true);
//                    break;
//                default:
//                    Access.logoutUser(uname);
//                    session.setAttribute("sacsuc", true);
//                    break;
//            }
//
//            session.setAttribute("content_page", "sac/mSac.jsp");
//        } else {
//            session.setAttribute("content_page", "sessionexp.jsp");
//        }
//        response.sendRedirect("index.jsp");
//    }
//
//    public static void handleLoginNav(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        session.setAttribute("content_page", "login.jsp");
//        response.sendRedirect("login.jsp");
//    }
//
//    public static void handleHome(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        session.setAttribute("content_page", "ucontent.jsp");
//        response.sendRedirect("index.jsp");
//    }
//
//    public static void goSac(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String function = request.getParameter("rFunction");
//        if (session.getAttribute("uname") != null) {
//            session.setAttribute("rFunc", function);
//            switch (function) {
//                case "DELETE":
//                    session.setAttribute("content_page", "sac/mSacDo.jsp");
//                    break;
//                case "UNLOCK":
//                    session.setAttribute("content_page", "sac/mUnlDo.jsp");
//                    break;
//                default:
//                    session.setAttribute("content_page", "sac/mSac.jsp");
//                    break;
//            }
//        } else {
//            session.setAttribute("content_page", "sessionexp.jsp");
//        }
//        response.sendRedirect("index.jsp");
//    }
//}
