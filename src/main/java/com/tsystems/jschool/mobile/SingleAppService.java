package com.tsystems.jschool.mobile;

import com.tsystems.jschool.mobile.dao.API.*;
import com.tsystems.jschool.mobile.dao.Impl.*;
import com.tsystems.jschool.mobile.dao.JpaUtil;

/**
 * Created by Alexandra on 02.03.2016.
 */
public class SingleAppService {

    private static AppService appService;

    public static synchronized AppService get() {
        if (appService != null) return appService;

        UserDAO userDAO = new UserDAOImpl();
        RoleDAO roleDAO = new RoleDAOImpl();
        OptionDAO optionDAO = new OptionDAOImpl();
        TariffDAO tariffDAO = new TariffDAOImpl();
        ContractDAO contractDAO = new ContractDAOImpl();
        MobileContext context = new MobileContext(userDAO, contractDAO, tariffDAO, optionDAO, roleDAO);

        appService = new AppService(context);

        return appService;
    }

}
