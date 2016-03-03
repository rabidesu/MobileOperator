package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.RoleDAO;
import com.tsystems.jschool.mobile.entities.Role;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 26.02.2016.
 */
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {

    private static Logger logger = Logger.getLogger(RoleDAOImpl.class);

    public List<Role> getRoleByName(RoleName name, EntityManager entityManager) throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Role.GET_BY_NAME);
            query.setParameter(1, name);
            return findMany(query);
        } catch (Exception e){
            String message = "Error on get role by role name: " + name.toString();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }
}
