package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.actions.Action;

public class ChangeContractAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("contract_id");
        String tariff = request.getParameter("tariff_id");
        String block = request.getParameter("block");
        String[] options = request.getParameterValues("options");

        try {
            app.contractService.changeContract(id, tariff, options, block);
            request.setAttribute("massage", "Контракт успешно изменен!");
            return "/admin/info.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Не удалось сохранить изменения! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
