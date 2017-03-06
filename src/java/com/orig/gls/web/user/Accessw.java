package com.orig.gls.web.user;

import com.orig.gls.dao.admin.role.Role;
import com.orig.gls.dao.admin.user.Access;
import com.orig.gls.dao.admin.user.User;
import com.orig.gls.security.Encode;
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

public class Accessw {

    private static final Log log = LogFactory.getLog("origlogger");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("userdnexists", false);
        session.setAttribute("usernotverified", false);
        session.setAttribute("userlocked", false);
        session.setAttribute("pwdexpired", false);
        session.setAttribute("acctexpired", false);
        session.setAttribute("usrdisabled", false);
        session.setAttribute("userlogged", false);
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");
        int userId;
        if (User.userExists(username)) {
            userId = User.getUserId(username);
            ArrayList user = User.getUserDetails(String.valueOf(userId));

            Encode enc = new Encode(Encode.generateUserKey(username, password), Encode.generateUserIV(username, password));
            Date currdate = new Date();
            int logattempts = 0;
            Date pwdexpyDate = new Date();
            Date acctexpyDate = new Date();
            Date disabledFromDate = new Date();
            Date disableUptoDate = new Date();
            String newUsrFlg = "";
            String userStatus = "";
            String roleid = "";
            String roleName = "";
            try {
                for (int k = 0; k < user.size(); k++) {
                    ArrayList one = (ArrayList) user.get(k);
                    logattempts = Integer.parseInt((String) one.get(8));
                    String pwdDate = (String) one.get(6);
                    String actDate = (String) one.get(7);
                    String disFDate = (String) one.get(4);
                    String disTDate = (String) one.get(5);
                    pwdexpyDate = sdf.parse(pwdDate.substring(0, 10));
                    acctexpyDate = sdf.parse(actDate.substring(0, 10));
                    disabledFromDate = sdf.parse(disFDate.substring(0, 10));
                    disableUptoDate = sdf.parse(disTDate.substring(0, 10));
                    newUsrFlg = (String) one.get(2);
                    roleid = (String) one.get(1);
                    userStatus = (String) one.get(3);
                    String solid = (String) one.get(9);
                    session.setAttribute("solId", solid);
                }
            } catch (Exception e) {
                log.debug(e);
            }
            System.out.println(disabledFromDate);
            roleName = Role.getRoleDesc(roleid);
            if (!User.userExistsInMod(username)) {
                if (logattempts <= 3) {
                    session.setAttribute("uname", username.toUpperCase());
                    if (!currdate.after(pwdexpyDate)) {
                        if (!currdate.after(acctexpyDate)) {
                            if ((disabledFromDate.before(currdate) && disableUptoDate.before(currdate)) || (disabledFromDate.after(currdate) && disableUptoDate.after(currdate))) {
                                if (!Access.userIsLoggedIn(username)) {
                                    if (Access.userExists(username, enc.encrypt(password))) {
                                        session.setAttribute("uname", username.toUpperCase());
                                        if (userStatus.equalsIgnoreCase("A")) {
                                            if (!newUsrFlg.equalsIgnoreCase("Y")) {
                                                session.setAttribute("role", roleName);
                                                Access.loginUser(username, "000", new Date(), session.getId());
                                                Access.enableUser(userId);
                                                Access.markCorrectLoginAttempt(username);
                                                session.setAttribute("content_page", "ucontent.jsp");
                                                response.sendRedirect("index.jsp");
                                            } else {
                                                session.setAttribute("content_page", "user/chCreds.jsp");
                                                response.sendRedirect("index.jsp");
                                            }
                                        } else {
                                            session.setAttribute("userdnexists", true);
                                            session.setAttribute("content_page", "login.jsp");
                                            response.sendRedirect("login.jsp");
                                        }
                                    } else {
                                        session.setAttribute("userdnexists", true);
                                        Access.markWrongLoginAttempt(username);
                                        session.setAttribute("content_page", "login.jsp");
                                        response.sendRedirect("login.jsp");
                                    }
                                } else {
                                    session.setAttribute("userlogged", true);
                                    session.setAttribute("content_page", "login.jsp");
                                    response.sendRedirect("login.jsp");
                                }
                            } else {
                                session.setAttribute("usrdisabled", true);
                                session.setAttribute("content_page", "login.jsp");
                                response.sendRedirect("login.jsp");
                            }

                        } else {
                            session.setAttribute("acctexpired", true);
                            session.setAttribute("content_page", "login.jsp");
                            response.sendRedirect("login.jsp");
                        }
                    } else {
                        session.setAttribute("pwdexpired", true);
                        session.setAttribute("content_page", "user/chCreds.jsp");
                        response.sendRedirect("index.jsp");
                    }

                } else {
                    session.setAttribute("userlocked", true);
                    session.setAttribute("content_page", "login.jsp");
                    response.sendRedirect("login.jsp");
                }

            } else {
                session.setAttribute("usernotverified", true);
                session.setAttribute("content_page", "login.jsp");
                response.sendRedirect("login.jsp");
            }

        } else {
            session.setAttribute("userdnexists", true);
            session.setAttribute("content_page", "login.jsp");
            response.sendRedirect("login.jsp");
        }
    }

    public static void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("uname") != null) {
            Access.logoutUser((String) session.getAttribute("uname"));
            session.setAttribute("content_page", "login.jsp");
            response.sendRedirect("login.jsp");
            session.invalidate();
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
            response.sendRedirect("index.jsp");
        }
    }

    public static void handleSac(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String function = request.getParameter("rFunction");

        session.setAttribute("sacsuc", false);
        if (session.getAttribute("uname") != null) {

            String uname = request.getParameter("username");
            switch (function) {
                case "DELETE":
                    Access.logoutUser(uname);
                    session.setAttribute("sacsuc", true);
                    break;
                case "UNLOCK":
                    Access.markCorrectLoginAttempt(uname);
                    session.setAttribute("sacsuc", true);
                    break;
                default:
                    Access.logoutUser(uname);
                    session.setAttribute("sacsuc", true);
                    break;
            }

            session.setAttribute("content_page", "sac/mSac.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleLoginNav(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("content_page", "login.jsp");
        response.sendRedirect("login.jsp");
    }

    public static void handleHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("content_page", "ucontent.jsp");
        response.sendRedirect("index.jsp");
    }

    public static void goSac(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String function = request.getParameter("rFunction");
        if (session.getAttribute("uname") != null) {
            session.setAttribute("rFunc", function);
            switch (function) {
                case "DELETE":
                    session.setAttribute("content_page", "sac/mSacDo.jsp");
                    break;
                case "UNLOCK":
                    session.setAttribute("content_page", "sac/mUnlDo.jsp");
                    break;
                default:
                    session.setAttribute("content_page", "sac/mSac.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
