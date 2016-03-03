package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;
import com.tsystems.jschool.mobile.services.API.ContractService;
import com.tsystems.jschool.mobile.services.Impl.ContractServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;
/**
 * Created by Alexandra on 22.02.2016.
 */
public class FindContractAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchText = request.getParameter("search_text");
        List<Contract> contracts;
        try {
            if (searchText != null) {
                contracts = app.contractService.findContractByNumber(searchText);
            } else {
                contracts = app.contractService.getAllContracts();
            }
            request.setAttribute("listContracts", contracts);
            return "/pages/admin/contract/searchContract.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Поиск контракта невозможен! (" + e.getCause().getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
