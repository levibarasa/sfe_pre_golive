package com.orig.gls.web.user;

import com.orig.gls.dao.admin.user.Access;
import com.orig.gls.dao.admin.user.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Userw {

    public static void handleGoUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("function");
            session.setAttribute("uverified", false);
            session.setAttribute("uadded", false);
            session.setAttribute("udeleted", false);
            session.setAttribute("ucancelled", false);
            session.setAttribute("umodified", false);
            session.setAttribute("fatal", false);
            switch (function) {
                case "ADD":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mUsers.jsp");
                    break;
                case "VERIFY":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mUnVerified.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mOtherAct.jsp");
                    break;
                case "DELETE":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mOtherAct.jsp");
                    break;

                case "INQUIRE":
                    session.setAttribute("ufunction", function);
                    session.setAttribute("content_page", "user/mOtherAct.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("uverified", false);
        session.setAttribute("uadded", false);
        session.setAttribute("udeleted", false);
        session.setAttribute("ucancelled", false);
        session.setAttribute("umodified", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String userName = request.getParameter("username");
            System.out.println("username is " + userName);
            String roleId = request.getParameter("workclass");
            String userPw = request.getParameter("password");
            String conPass = request.getParameter("confpassword");
            String solId = request.getParameter("branch");
            String function = (String) session.getAttribute("ufunction");
            String userid = request.getParameter("did");
            String uname = (String) session.getAttribute("uname");
            switch (function) {
                case "ADD":
                    if (!User.userExists(userName)) {
                        if (userPw.equals(conPass)) {
                            if (User.addUserDetails(userName, roleId, userPw, 0, "12", 0, "Y", 0, uname, solId) > 0) {
                                User.deleteAfterReg(userName);
                                session.setAttribute("uadded", true);
                                session.setAttribute("content_page", "user/mUsers.jsp");
                            } else {
                                session.setAttribute("fatal", true);
                                session.setAttribute("content_page", "user/mUsers.jsp");
                            }
                        } else {
                            session.setAttribute("fatal", true);
                            session.setAttribute("content_page", "user/mUsers.jsp");
                        }
                    } else {
                        session.setAttribute("uexists", true);
                        session.setAttribute("content_page", "user/mUsers.jsp");
                    }
                    break;
                case "VERIFY":
                    int userId = Integer.parseInt(userid);
                    String lastOper = User.lastOper(userid);
                    switch (lastOper) {
                        case "A":
                            User.markUserUnverified(userId, "A");
                            User.verifyUser(userId);
                            session.setAttribute("uverified", true);
                            break;
                        case "D":
                            User.markUserUnverified(userId, "D");
                            User.verifyUser(userId);
                            session.setAttribute("uverified", true);
                            break;
                        case "M":
                            User.modifyUser(userId, (String) session.getAttribute("uname"));
                            User.markUserUnverified(userId, "A");
                            User.verifyUser(userId);
                            session.setAttribute("uverified", true);
                            break;
                    }
                    session.setAttribute("content_page", "user/mUnVerified.jsp");
                    break;
                case "MODIFY":
                    session.setAttribute("userid", userid);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
//                    }
                    break;
                case "DELETE":
                    session.setAttribute("userid", userid);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("userid", userid);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
                    break;
                case "CANCEL":
                    session.setAttribute("userid", userid);
                    userId = User.getUserId(userName);
                    User.verifyUser(userId);
                    session.setAttribute("ucancelled", true);
                    session.setAttribute("content_page", "user/mUser_b.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleModifyUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("uverified", false);
        session.setAttribute("uadded", false);
        session.setAttribute("udeleted", false);
        session.setAttribute("ucancelled", false);
        session.setAttribute("umodified", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String userName = request.getParameter("uname");
            String roleId = request.getParameter("roleid");
            String userid = (String) session.getAttribute("userid");
            int user = Integer.parseInt(userid);

            String function = (String) session.getAttribute("ufunction");
            System.out.println("user id " + userid);
            String uname = (String) session.getAttribute("uname");
            switch (function) {
                case "MODIFY":
                    if (User.addUserModDetails(user, userName.trim(), roleId, "DummyPass", 0, "12", 0, "Y", 0, "M", uname, "Modify")) {
                        User.markUserUnverified(user, "M");
                        session.setAttribute("umodified", true);
                    } else {
                        session.setAttribute("fatal", true);
                    }
                    break;
                case "DELETE":
                    if (User.addUserModDetails(user, userName, roleId, "DummyPass", 0, "12", 0, "Y", 0, "D", uname, "Delete")) {
                        User.markUserUnverified(user, "D");
                        session.setAttribute("udeleted", true);
                    } else {
                        session.setAttribute("fatal", true);
                    }
                    break;
            }
            session.removeAttribute("userid");
            session.setAttribute("content_page", "user/mOtherAct.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        session.removeAttribute("udeleted");
        session.removeAttribute("umodified");
        response.sendRedirect("index.jsp");
    }

    public static void changePwd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String uname = (String) session.getAttribute("uname");
        String userPw = request.getParameter("Password");
        String conPass = request.getParameter("Password1");
        if (userPw.equals(conPass)) {
            User.changePassword(uname, userPw);
            Access.logoutUser((String) session.getAttribute("uname"));
            session.setAttribute("content_page", "login.jsp");
            response.sendRedirect("login.jsp");
        }
    }

    public static void navChangePss(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String uname = (String) session.getAttribute("uname");
        if (uname != null) {
            session.setAttribute("content_page", "user/chCreds.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
