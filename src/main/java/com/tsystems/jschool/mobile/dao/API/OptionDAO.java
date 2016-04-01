package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.Option;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface OptionDAO extends GenericDAO<Option> {

    List<Option> getAnotherOptions(int id) throws MobileDAOException;

    void removeOptionById(int id) throws MobileDAOException;

    List<Option> getOptionsByName(String name) throws MobileDAOException;

    public List<Option> getAvailableOptions();

}
