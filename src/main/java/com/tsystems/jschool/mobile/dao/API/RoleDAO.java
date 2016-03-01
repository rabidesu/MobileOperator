package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Role;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.enumerates.RoleName;

import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public interface RoleDAO extends GenericDAO<Role> {

    List<Role> getRoleByName(RoleName name);

}
