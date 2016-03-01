package com.tsystems.jschool.mobile.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexandra on 16.02.2016.
 */
public class ActionFactory {

    public static Action getAction(HttpServletRequest request){

        String requestAction = request.getRequestURI().replace("/pages/", "").replace("/", ".");

        try {
            Action action = (Action) Class.forName("com.tsystems.jschool.mobile.actions." + requestAction + "Action").newInstance();
            return action;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

}
