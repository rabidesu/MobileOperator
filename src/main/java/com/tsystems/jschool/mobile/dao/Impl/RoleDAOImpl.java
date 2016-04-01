package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.RoleDAO;
import com.tsystems.jschool.mobile.entities.Role;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("roleDAO")
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = Logger.getLogger(RoleDAOImpl.class);

    public Role getRoleByName(RoleName name) {
        try {
            Query query = entityManager.createNamedQuery(Role.GET_BY_NAME);
            query.setParameter(1, name);

            return findMany(query).get(0);
        } catch (Exception e){
            String message = "Error on get role by role name: " + name.toString();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }
}
