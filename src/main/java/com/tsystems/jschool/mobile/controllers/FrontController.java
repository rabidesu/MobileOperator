package com.tsystems.jschool.mobile.controllers;

import com.tsystems.jschool.mobile.AppService;
import com.tsystems.jschool.mobile.SingleAppService;
import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.actions.ActionFactory;
import com.tsystems.jschool.mobile.exceptions.NoSuchActionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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
        String nextPage = request.getRequestURI().replace(CONTROLLER_PREFIX, "");


        if (!nextPage.endsWith(".jsp")) {
            try {
                Action action = ActionFactory.getAction(request);
                nextPage = action.execute(request, response);
                if (nextPage.equals("index.jsp") || nextPage.equals("404.jsp")) {
                    dispatcher = getServletContext().getRequestDispatcher("/" + nextPage.replace(CONTROLLER_PREFIX, ""));
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + nextPage.replace(CONTROLLER_PREFIX, ""));
                    dispatcher.forward(request, response);
                }
            } catch (NoSuchActionException e){
                dispatcher = getServletContext().getRequestDispatcher("/404.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/" + nextPage);
            dispatcher.forward(request, response);
        }


    }

}
