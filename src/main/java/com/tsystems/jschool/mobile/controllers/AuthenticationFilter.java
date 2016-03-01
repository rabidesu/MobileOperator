package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Alexandra on 12.02.2016.
 */
public class AuthenticationFilter implements Filter {

    public static final String CONTROLLER_PREFIX = "/pages";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        User user = null;
        RoleName userRole = null;
        if (session != null){
            user = (User) session.getAttribute("user");
        }
        if (user != null){
            userRole = user.getRole().getRoleName();
        }

        if (uri.contains("client/") && (session == null || user == null || userRole == RoleName.ADMIN)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/clientLogin.jsp");
            dispatcher.forward(req, resp);
        } else if (uri.contains("admin/") && (session == null || user == null || userRole == RoleName.CLIENT)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/adminLogin.jsp");
            dispatcher.forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }

//        if ((session == null || user == null) && (uri.contains("client/") || uri.contains("admin/"))) {
//            if (uri.contains("client/")){
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/clientLogin.jsp");
//                dispatcher.forward(req, resp);
////                response.sendRedirect("/clientLogin.jsp");
//
//            }
//            if (uri.contains("admin/")){
//
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/adminLogin.jsp");
//                dispatcher.forward(req, resp);
////                response.sendRedirect("/adminLogin.jsp");
//
//            }
//        } else {
//            chain.doFilter(req, resp);
//        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
