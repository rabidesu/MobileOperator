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

    public List<User> getUserByEmail(String email) {
        Query query = entityManager.createNamedQuery(User.GET_BY_EMAIL);
        query.setParameter(1, email);
        return findMany(query);
    }

    public long getCountUsers() {
        Query query = entityManager.createNamedQuery(User.GET_COUNT);
        return (long) query.getSingleResult();
    }

    public long getCountUserByNumber(String number) {
        Query query = entityManager.createNamedQuery(User.GET_COUNT_BY_PHONE_NUMBER);
        query.setParameter(1, number);
        return (long) query.getSingleResult();
    }

    public long getCountUserByEmail(String email) {
        Query query = entityManager.createNamedQuery(User.GET_COUNT_BY_EMAIL);
        logger.warn(email);
        query.setParameter(1, email);
        return (long) query.getSingleResult();
    }

    public long getCountUserBySurname(String surname) {
        Query query = entityManager.createNamedQuery(User.GET_COUNT_BY_SURNAME);
        query.setParameter(1, surname);
        return (long) query.getSingleResult();
    }

    public List<User> getPageUsersByPhoneNumber(int page, int pageSize, String number) {
        Query query = entityManager.createNamedQuery(User.GET_BY_PHONE_NUMBER);
        query.setParameter(1, number);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<User> getPageUsersByEmail(int page, int pageSize, String email) {
        Query query = entityManager.createNamedQuery(User.GET_BY_EMAIL);
        query.setParameter(1, email);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<User> getPageUsersBySurname(int page, int pageSize, String surname) {
        Query query = entityManager.createNamedQuery(User.GET_BY_SURNAME);
        query.setParameter(1, surname);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public List<User> getPageUsers(int page, int pageSize) {
        Query query = entityManager.createNamedQuery(User.GET_ALL);
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
