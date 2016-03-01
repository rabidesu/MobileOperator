package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.UserDAO;
import com.tsystems.jschool.mobile.dao.JpaUtil;
import com.tsystems.jschool.mobile.entities.User;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    public UserDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<User> getUserByEmailPassword(String email, String password) {

        Query query = entityManager.createNamedQuery(User.GET_BY_EMAIL_PASSWD);
        query.setParameter(1, email);
        query.setParameter(2, password);
        return findMany(query);
    }

    public List<User> getUserByPhoneNumber(String number) {
        Query query = entityManager.createNamedQuery(User.GET_BY_PHONE_NUMBER);
        query.setParameter(1, number);
        return findMany(query);
    }

    public List<User> getUserByEmail(String email) {
        Query query = entityManager.createNamedQuery(User.GET_BY_EMAIL);
        query.setParameter(1, email);
        return findMany(query);
    }

    public List<User> getUserBySurname(String surname) {
        Query query = entityManager.createNamedQuery(User.GET_BY_SURNAME);
        query.setParameter(1, surname);
        return findMany(query);
    }

}
