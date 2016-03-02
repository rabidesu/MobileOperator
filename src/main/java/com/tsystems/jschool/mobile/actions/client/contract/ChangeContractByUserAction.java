package com.tsystems.jschool.mobile.actions.client.contract;

import com.tsystems.jschool.mobile.controllers.Action;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexandra on 25.02.2016.
 */
public class ChangeContractByUserAction extends Action {

    ContractService contractService = new ContractServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("contract_id");
        String tariff = request.getParameter("tariff_id");
        String block = request.getParameter("block");
        String[] options = request.getParameterValues("options");

        contractService.changeContract(id, tariff, options, block);
        request.setAttribute("massage", "Контракт успешно изменен!");
        return "/admin/info.jsp";
    }
}
