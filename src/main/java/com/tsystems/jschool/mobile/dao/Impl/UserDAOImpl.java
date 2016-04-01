package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.UserDAO;
import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = Logger.getLogger(UserDAOImpl.class);

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
