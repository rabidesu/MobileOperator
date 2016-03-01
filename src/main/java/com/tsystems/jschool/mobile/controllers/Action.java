package com.tsystems.jschool.mobile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexandra on 16.02.2016.
 */
public abstract class Action {

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
