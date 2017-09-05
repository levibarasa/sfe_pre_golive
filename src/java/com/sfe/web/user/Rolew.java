package com.sfe.web.user;

import com.sfe.dao.admin.role.Role;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Rolew {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleGoRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("dupwc", false);
        session.setAttribute("wcadded", false);
        session.setAttribute("wcver", false);
        session.setAttribute("modsuc", false);
        session.setAttribute("delsuc", false);
        session.setAttribute("wdnexist", false);
        session.setAttribute("fatal", false);
        String funtion = request.getParameter("function");
        if ((String) session.getAttribute("uname") != null) {
            session.setAttribute("roleAct", funtion);
            if (funtion.equalsIgnoreCase("ADD")) {
                session.setAttribute("content_page", "role/mAddRole.jsp");
            } else if (funtion.equalsIgnoreCase("MAP ROLE")) {
                session.setAttribute("content_page", "role/mapRole.jsp");
            } else {
                session.setAttribute("content_page", "role/mWorkclass_b.jsp");
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("dupwc", false);
        session.setAttribute("wcadded", false);
        session.setAttribute("wcver", false);
        session.setAttribute("modsuc", false);
        session.setAttribute("delsuc", false);
        session.setAttribute("wdnexist", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String function = (String) session.getAttribute("roleAct");
            String uname = (String) session.getAttribute("uname");
            switch (function) {
                case "ADD":
                    String rolename = request.getParameter("rolename");
                    if (!Role.roleExists(rolename)) {
                        int h;
                        h = Role.addRole(rolename, "Y", "N", uname, new Date(), uname, new Date(), "01");
                        if (h > 0) {
                            Role.addRoleMod(h, rolename, "Y", "N", uname, new Date(), uname, new Date(), "01");
                            session.setAttribute("wcadded", true);
                            session.setAttribute("content_page", "role/mAddRole.jsp");
                        } else {
                            session.setAttribute("fatal", true);
                            session.setAttribute("content_page", "role/mAddRole.jsp");
                        }
                    } else {
                        session.setAttribute("dupwc", true);
                        session.setAttribute("content_page", "role/mAddRole.jsp");
                    }
                    break;
                case "VERIFY":
                    String role = request.getParameter("did");
                    int userId = Role.getRoleId(role);
                    if (Role.roleExistsMod(role)) {
                        Role.verifyRoleDetails(userId);
                        Role.deleteModRole(userId);
                        session.setAttribute("wcver", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    } else {
                        session.setAttribute("wdnexist", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    }
                    break;
                case "MODIFY":
                    session.setAttribute("rlId", String.valueOf(Role.getRoleId(request.getParameter("did"))));
                    session.setAttribute("rlnm", request.getParameter("did"));
                    session.setAttribute("content_page", "role/mRoleDetails.jsp");
//                    }
                    break;
                case "DELETE":
                    session.setAttribute("rlId", String.valueOf(Role.getRoleId(request.getParameter("did"))));
                    session.setAttribute("rlnm", request.getParameter("did"));
                    session.setAttribute("content_page", "role/mRoleDetails.jsp");
                    break;
                case "INQUIRE":
                    session.setAttribute("rlnm", request.getParameter("did"));
                    session.setAttribute("rlId", String.valueOf(Role.getRoleId(request.getParameter("did"))));
                    session.setAttribute("content_page", "role/mRoleDetails.jsp");
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleModifyRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("dupwc", false);
        session.setAttribute("wcadded", false);
        session.setAttribute("wcver", false);
        session.setAttribute("modsuc", false);
        session.setAttribute("delsuc", false);
        session.setAttribute("wdnexist", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("func");
            String uname = (String) session.getAttribute("uname");
            String rolename = request.getParameter("rolename");
            String roleId = request.getParameter("roleid");
            int h = Integer.parseInt(roleId.trim());
            switch (function) {
                case "MODIFY":
                    if (!Role.roleExistsMod(rolename)) {
                        Role.addRoleMod(h, rolename, "Y", "N", uname, new Date(), uname, new Date(), "01");
                        session.setAttribute("modsuc", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    } else {
                        session.setAttribute("dupwc", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    }
                    break;
                case "DELETE":
                    if (!Role.roleExistsMod(rolename)) {
                        Role.addRoleMod(h, rolename, "C", "Y", uname, new Date(), uname, new Date(), "01");
                        session.setAttribute("delsuc", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    } else {
                        session.setAttribute("dupwc", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    }
                    break;
                case "MAP ROLE":
                    if (!Role.roleExistsMod(rolename)) {
                        String fruits[] = request.getParameterValues("menuOpt");
                        if (fruits != null) {
                            for (int i = 0; i < fruits.length; i++) {
                                System.out.println(fruits[i]);
                            }
                        }
                        Role.addRoleMod(h, rolename, "C", "Y", uname, new Date(), uname, new Date(), "01");
                        session.setAttribute("delsuc", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    } else {
                        session.setAttribute("dupwc", true);
                        session.setAttribute("content_page", "role/mWorkclass_b.jsp");
                    }
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMapRole(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("dupwc", false);
        session.setAttribute("wcadded", false);
        session.setAttribute("wcver", false);
        session.setAttribute("modsuc", false);
        session.setAttribute("delsuc", false);
        session.setAttribute("wdnexist", false);
        session.setAttribute("fatal", false);
        if ((String) session.getAttribute("uname") != null) {
            String role = request.getParameter("role");
            String fruits[] = request.getParameterValues("menuOpt");
            System.out.println("Selected role " + role);
            if (fruits != null) {
                for (int i = 0; i < fruits.length; i++) {
                    System.out.println(fruits[i]);
                }
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
