package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class OptionDAOImpl extends GenericDAOImpl<Option> implements OptionDAO {

    private static Logger logger = Logger.getLogger(OptionDAOImpl.class);

    public List<Option> getAnotherOptions(int id, EntityManager entityManager) throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Option.GET_ANOTHER_OPTIONS);
            query.setParameter(1, id);
            return findMany(query);
        } catch (Exception e){
            String message = "Error on get another options for option: " + id;
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public void removeOptionById(int id, EntityManager entityManager) throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Option.REMOVE_BY_ID);
            query.setParameter(1, id);
            query.executeUpdate();
        } catch (Exception e){
            String message = "Error on remove option: " + id + ". Option is not removed!";
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public List<Option> getOptionsByName(String name, EntityManager entityManager)throws MobileDAOException {
        try {
            Query query = entityManager.createNamedQuery(Option.GET_BY_NAME);
            query.setParameter(1, name);
            return findMany(query);
        } catch (Exception e){
            String message = "Error on get option by name: " + name;
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

}
