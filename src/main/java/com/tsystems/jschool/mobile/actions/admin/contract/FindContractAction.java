package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.controllers.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindContractAction extends Action {

    ContractService contractService = new ContractServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        List<Contract> contracts = null;
        if (searchText != null){
            contracts = contractService.findContractByNumber(searchText);
        } else {
            contracts = contractService.getAllContracts();
        }
        request.setAttribute("listContracts", contracts);

        if (contracts.isEmpty()) {
            request.setAttribute("message", "Нет ни одного контракта, удовлетворяющего условиям поиска");
        } else {
            request.setAttribute("message", null);
        }
        return "/pages/admin/contract/searchContract.jsp";
    }
}
