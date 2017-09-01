package com.orig.gls.web.category;

import com.orig.gls.dao.bank.Bank;
import com.orig.gls.dao.categories.Category;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Categoryw {

    public static void handleGoCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if ((String) session.getAttribute("uname") != null) {
            session.setAttribute("catfunc", request.getParameter("function"));
            session.setAttribute("categorytype", request.getParameter("categorytype"));
            session.setAttribute("content_page", "categories/mCategories_b.jsp");
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }

    public static void handleMaintainCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("catadded", false);
        session.setAttribute("catexists", false);
        if ((String) session.getAttribute("uname") != null) {
            String function = request.getParameter("function");
            String categorytype = request.getParameter("categorytype");
            String categorycode = request.getParameter("categorycode");
            String categoryvalue = request.getParameter("categoryvalue");
            switch (function) {
                case "ADD":
                    if (!Category.categoryExists(categorycode, categorytype)) {
                        Category.addCategories(Bank.getBankId(), categorycode, categorytype, categoryvalue, (String) session.getAttribute("uname"), (String) session.getAttribute("uname"));
                        session.setAttribute("catadded", true);
                        session.setAttribute("content_page", "categories/mCategories_a.jsp");
                    } else {
                        session.setAttribute("catexists", true);
                        session.setAttribute("content_page", "categories/mCategories_b.jsp");
                    }
                    break;
            }
        } else {
            session.setAttribute("content_page", "sessionexp.jsp");
        }
        response.sendRedirect("index.jsp");
    }
}
