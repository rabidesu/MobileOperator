package com.tsystems.jschool.mobile;

import com.tsystems.jschool.mobile.dao.API.*;
import com.tsystems.jschool.mobile.dao.Impl.UserDAOImpl;

/**
 * Created by Alexandra on 02.03.2016.
 */
public class MobileContext {

    public final UserDAO userDAO;
    public final ContractDAO contractDAO;
    public final TariffDAO tariffDao;
    public final OptionDAO optionDAO;
    public final RoleDAO roleDAO;

    public AppService services;

    public MobileContext(UserDAO userDAO, ContractDAO contractDAO, TariffDAO tariffDao, OptionDAO optionDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.contractDAO = contractDAO;
        this.tariffDao = tariffDao;
        this.optionDAO = optionDAO;
        this.roleDAO = roleDAO;
    }
}
