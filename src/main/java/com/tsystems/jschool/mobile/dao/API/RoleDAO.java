package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Role;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface RoleDAO extends GenericDAO<Role> {

    List<Role> getRoleByName(RoleName name, EntityManager entityManager) throws MobileDAOException;

}
