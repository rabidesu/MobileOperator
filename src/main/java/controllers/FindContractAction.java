package controllers;

import entities.Contract;
import entities.User;
import services.API.ContractService;
import services.API.UserService;
import services.Impl.ContractServiceImpl;
import services.Impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        return "/pages/admin/searchContract.jsp";
    }
}
