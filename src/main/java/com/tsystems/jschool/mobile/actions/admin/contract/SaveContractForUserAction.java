package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tsystems.jschool.mobile.controllers.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class SaveContractForUserAction extends Action {

    private ContractService contractService = new ContractServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String user_id = request.getParameter("user_id");
        String phone_number = request.getParameter("phone_number");
        String tariffId = request.getParameter("tariff_id");
        String[] options = request.getParameterValues("options");
        contractService.addContractForUser(user_id, phone_number, tariffId, options);
        request.setAttribute("massage", "Контракт успешно добавлен!");
        return "/admin/info.jsp";
    }
}
