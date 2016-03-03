package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public interface UserDAO extends GenericDAO<User> {

    List<User> getUserByPhoneNumber(String number, EntityManager entityManager) throws MobileDAOException;

    List<User> getUserByEmail(String email, EntityManager entityManager) throws MobileDAOException;

    List<User> getUserBySurname(String surname, EntityManager entityManager) throws MobileDAOException;

}
