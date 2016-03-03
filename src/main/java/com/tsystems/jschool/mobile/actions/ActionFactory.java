package com.tsystems.jschool.mobile.actions;

import com.tsystems.jschool.mobile.actions.Action;
import com.tsystems.jschool.mobile.actions.LogoutAction;
import com.tsystems.jschool.mobile.actions.admin.LoginAdminAction;
import com.tsystems.jschool.mobile.actions.admin.client.AllClientsAction;
import com.tsystems.jschool.mobile.actions.admin.client.FindClientAction;
import com.tsystems.jschool.mobile.actions.admin.client.FindClientProfileAction;
import com.tsystems.jschool.mobile.actions.admin.contract.*;
import com.tsystems.jschool.mobile.actions.admin.option.*;
import com.tsystems.jschool.mobile.actions.admin.tariff.*;
import com.tsystems.jschool.mobile.actions.client.LoginClientAction;
import com.tsystems.jschool.mobile.actions.client.contract.ChangeContractByClientAction;
import com.tsystems.jschool.mobile.actions.client.contract.EditContractAction;
import com.tsystems.jschool.mobile.actions.client.tariff.AddTariffToContractAction;
import com.tsystems.jschool.mobile.actions.client.tariff.ViewTariffAction;
import com.tsystems.jschool.mobile.exceptions.NoSuchActionException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexandra on 16.02.2016.
 */
public class ActionFactory {

    public static final String CONTROLLER_PREFIX = "/pages/";
    private final static Logger logger = Logger.getLogger(ActionFactory.class);
    private static final Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("admin/client/AllClient", new AllClientsAction());
        actions.put("admin/client/FindClient", new FindClientAction());
        actions.put("admin/client/FindClientProfile", new FindClientProfileAction());
        actions.put("admin/contract/ChangeContract", new ChangeContractAction());
        actions.put("admin/contract/EditContractByAdmin", new EditContractByAdminAction());
        actions.put("admin/contract/FindContract", new FindContractAction());
        actions.put("admin/contract/NewContract", new NewContractAction());
        actions.put("admin/contract/SaveContract", new SaveContractAction());
        actions.put("admin/contract/SaveContractForUser", new SaveContractForUserAction());
        actions.put("admin/option/ChangeOption", new ChangeOptionAction());
        actions.put("admin/option/EditOption", new EditOptionAction());
        actions.put("admin/option/NewOption", new NewOptionAction());
        actions.put("admin/option/RemoveOption", new RemoveOptionAction());
        actions.put("admin/option/SaveOption", new SaveOptionAction());
        actions.put("admin/option/FindOption", new FindOptionAction());
        actions.put("admin/tariff/ChangeTariff", new ChangeTariffAction());
        actions.put("admin/tariff/EditTariff", new EditTariffAction());
        actions.put("admin/tariff/FindTariff", new FindTariffAction());
        actions.put("admin/tariff/NewTariff", new NewTariffAction());
        actions.put("admin/tariff/RemoveTariff", new RemoveTariffAction());
        actions.put("admin/tariff/SaveTariff", new SaveTariffAction());
        actions.put("admin/LoginAdmin", new LoginAdminAction());
        actions.put("Logout", new LogoutAction());
        actions.put("client/LoginClient", new LoginClientAction());
        actions.put("client/contract/EditContract", new EditContractAction());
        actions.put("client/tariff/ViewTariff", new ViewTariffAction());
        actions.put("client/tariff/AddTariffToContract", new AddTariffToContractAction());
        actions.put("client/contract/ChangeContractByClient", new ChangeContractByClientAction());
    }

    public static Action getAction(HttpServletRequest request) throws NoSuchActionException {

        String requestAction = request.getRequestURI().replace(CONTROLLER_PREFIX, "");
        Action action = actions.get(requestAction);
        if (action != null) {
            return action;
        } else {
            logger.error("Not found action: " + requestAction);
            throw new NoSuchActionException();
        }

    }

}
