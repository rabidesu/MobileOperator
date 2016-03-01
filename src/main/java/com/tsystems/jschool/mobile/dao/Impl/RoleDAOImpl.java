package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.RoleDAO;
import com.tsystems.jschool.mobile.dao.API.TariffDAO;
import com.tsystems.jschool.mobile.entities.Role;
import com.tsystems.jschool.mobile.entities.Tariff;
import com.tsystems.jschool.mobile.enumerates.RoleName;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {

    public RoleDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Role> getRoleByName(RoleName name){
        Query query = entityManager.createNamedQuery(Role.GET_BY_NAME);
        query.setParameter(1, name);
        return findMany(query);
    }
}
