package controllers;

import entities.User;

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
        String userRole = "";
        if (session != null){
            user = (User) session.getAttribute("user");
            userRole = (String) session.getAttribute("role");
        }

        if ((session == null || user == null) && (uri.contains("client/") || uri.contains("admin/"))) {
            if (uri.contains("client/")){

//                RequestDispatcher dispatcher = request.getRequestDispatcher("/clientLogin.jsp.go");
//                dispatcher.forward(req, resp);
                response.sendRedirect("/clientLogin.jsp");

            }
            if (uri.contains("admin/")){

//                RequestDispatcher dispatcher = request.getRequestDispatcher("/adminLogin.jsp.go");
//                dispatcher.forward(req, resp);
                response.sendRedirect("/adminLogin.jsp");

            }
        } else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
