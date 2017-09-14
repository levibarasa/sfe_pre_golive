package com.sfe.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class origSessionFilter implements Filter {

    private ArrayList<String> urlList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String url = request.getServletPath();
        boolean allowedRequest = false;
        if (urlList.contains(url)) {
            allowedRequest = true;
        }
        if (!allowedRequest) {
            HttpSession session = request.getSession(false);
            if (null == session) {
                RequestDispatcher reqd = request.getRequestDispatcher("login.jsp");
                    reqd.forward(request, response);

            }
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

}
