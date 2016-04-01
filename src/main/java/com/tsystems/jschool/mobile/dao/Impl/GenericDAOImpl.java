package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.GenericDAO;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    @Autowired
    private EntityManager entityManager;

    private static Logger logger = Logger.getLogger(GenericDAOImpl.class);

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public void merge(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public List<T> findMany(Query query) {
        return (List<T>)query.getResultList();
    }

    public T findById(Class clazz, int id) {
        return (T) entityManager.find(clazz, id);
    }

    public List<T> findAll(Class clazz) {
        Query query = entityManager.createQuery("from " + clazz.getName());
        return (List<T>) query.getResultList();
    }


}
