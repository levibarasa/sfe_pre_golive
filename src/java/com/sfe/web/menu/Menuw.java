package com.sfe.web.menu;

import com.sfe.dao.admin.user.Access;
import com.sfe.dao.menu.Menu;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Menuw {

    private static final Log log = LogFactory.getLog("origlogger");

    public static void handleMenuOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("nonexistencemenu", false);
        session.setAttribute("rolemenunone", false);
        session.setAttribute("gverified", false);
        session.setAttribute("gadded", false);
        session.setAttribute("gdeleted", false);
        session.setAttribute("sgexists", false);
        session.setAttribute("fatal", false);
        session.setAttribute("sgadded", false);
        session.setAttribute("sgverified", false);
        session.setAttribute("sgmodified", false);
        if ((String) session.getAttribute("uname") != null) {
            if (Access.userIsLoggedIn((String) session.getAttribute("uname"))) {
                String menuoption = request.getParameter("menu option").trim();
                if (!menuoption.equalsIgnoreCase("")) {
                    if (Menu.menuExists(menuoption)) {
                        if (Menu.roleHasMenu((String) session.getAttribute("role"), menuoption)) {
                            if (!Menu.roleMenuIsUnverified((String) session.getAttribute("role"), menuoption)) {
                                String url = Menu.mopUrl(menuoption);
                                session.setAttribute("content_page", url);
                            } else {
                                session.setAttribute("rolemenunone", true);
                                session.setAttribute("content_page", "ucontent.jsp");
                            }
                        } else {
                            session.setAttribute("rolemenunone", true);
                            session.setAttribute("content_page", "ucontent.jsp");
                        }
                    } else {
                        session.setAttribute("nonexistencemenu", true);
                        session.setAttribute("content_page", "ucontent.jsp");
                    }
                }
            } else {
                session.setAttribute("content_page", "sessionexp.jsp");
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
