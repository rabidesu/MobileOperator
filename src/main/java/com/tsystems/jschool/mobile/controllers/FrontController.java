package com.tsystems.jschool.mobile.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandra on 12.02.2016.
 */
public class FrontController extends HttpServlet {

    public static final String PAGES_DIR = "/WEB-INF/pages/";
    public static final String CONTROLLER_PREFIX = "pages/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher;
        String uri = request.getPathInfo();

        String nextPage = request.getRequestURI().replace(CONTROLLER_PREFIX, "");
        System.out.println(nextPage);

        if (!nextPage.endsWith(".jsp")) {
            Action action = ActionFactory.getAction(request);
            nextPage = action.execute(request, response);
            if (nextPage.equals("index.jsp")){
                dispatcher = getServletContext().getRequestDispatcher("/" + nextPage.replace(CONTROLLER_PREFIX, ""));
                dispatcher.forward(request, response);
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + nextPage.replace(CONTROLLER_PREFIX, ""));
                dispatcher.forward(request, response);
            }
        } else {
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + nextPage);
            dispatcher.forward(request, response);
        }


    }

}
