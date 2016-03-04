package com.tsystems.jschool.mobile.actions.admin.contract;

import com.tsystems.jschool.mobile.entities.Contract;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import com.tsystems.jschool.mobile.actions.Action;

public class EditContractByAdminAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String contractId = request.getParameter("entity_id");

        try {
            Contract contract = app.contractService.getContractById(contractId);
            List<Tariff> tariffs = app.tariffService.getAllTariffs();
            List<Option> options = app.optionService.getAllOptions();

            request.setAttribute("options", options);
            request.setAttribute("tariffs", tariffs);
            request.setAttribute("contract", contract);

            return "/admin/contract/editContract.jsp";
        } catch (MobileServiceException e){
            request.setAttribute("massage", "Редактирование контракта невозможно! (" + e.getMessage() + ")");
            return "/admin/info.jsp";
        }
    }
}
