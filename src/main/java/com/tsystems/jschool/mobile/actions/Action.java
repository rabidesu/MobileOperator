package com.tsystems.jschool.mobile.actions;

import com.tsystems.jschool.mobile.AppService;
import com.tsystems.jschool.mobile.SingleAppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexandra on 16.02.2016.
 */
public abstract class Action {

    protected AppService app = SingleAppService.get();

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
