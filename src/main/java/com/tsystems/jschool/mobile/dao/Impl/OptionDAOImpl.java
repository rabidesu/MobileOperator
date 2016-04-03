package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.OptionDAO;
import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Repository("optionDAO")
public class OptionDAOImpl extends GenericDAOImpl<Option> implements OptionDAO {

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = Logger.getLogger(OptionDAOImpl.class);

    public List<Option> getAnotherOptions(int id) {
        Query query = entityManager.createNamedQuery(Option.GET_ANOTHER_OPTIONS);
        query.setParameter(1, id);
        return findMany(query);
    }

    public List<Option> getAvailableOptions() {
        Query query = entityManager.createNamedQuery(Option.GET_AVAILABLE_OPTIONS);
        return findMany(query);
    }

    public void removeOptionById(int id) {
        Query query = entityManager.createNamedQuery(Option.REMOVE_BY_ID);
        query.setParameter(1, id);
        query.executeUpdate();
    }


    public List<Option> getOptionsByName(String name)throws MobileDAOException {
        Query query = entityManager.createNamedQuery(Option.GET_BY_NAME);
        query.setParameter(1, name);
        return findMany(query);
    }

}
