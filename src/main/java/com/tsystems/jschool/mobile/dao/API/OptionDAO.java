package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface OptionDAO extends GenericDAO<Option> {

    List<Option> getAnotherOptions(int id, EntityManager entityManager) throws MobileDAOException;

    void removeOptionById(int id, EntityManager entityManager) throws MobileDAOException;

    List<Option> getOptionsByName(String name, EntityManager entityManager) throws MobileDAOException;

}
