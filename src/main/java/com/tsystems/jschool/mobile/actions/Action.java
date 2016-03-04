package com.tsystems.jschool.mobile.actions;

import com.tsystems.jschool.mobile.AppService;
import com.tsystems.jschool.mobile.SingleAppService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {

    protected AppService app = SingleAppService.get();

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
