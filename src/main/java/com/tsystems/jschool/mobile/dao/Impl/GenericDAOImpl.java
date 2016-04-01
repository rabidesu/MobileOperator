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

    public void save(T entity) throws MobileDAOException {
        try {
            entityManager.persist(entity);
        } catch (Exception e){
            String message = "Error on save entity: " + entity.getClass().getSimpleName();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public void merge(T entity) throws MobileDAOException {
        try {
            entityManager.merge(entity);
        } catch (Exception e){
            String message = "Error on merge entity: " + entity.getClass().getSimpleName();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public void delete(T entity) throws MobileDAOException {
        try {
            entityManager.remove(entity);
        } catch (Exception e){
            String message = "Error on delete entity: " + entity.getClass().getSimpleName();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public List<T> findMany(Query query) throws MobileDAOException {
        try {
            List<T> entities;
            entities = (List<T>) query.getResultList();
            return entities;
        } catch (Exception e){
            String message = "Error on execute query: " + query.toString();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public T findById(Class clazz, int id) throws MobileDAOException {
        try {
            T entity = null;
            entity = (T) entityManager.find(clazz, id);
            return entity;
        } catch (Exception e){
            String message = "Error on find entity: " + clazz.getSimpleName() + " by id: " + id;
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }

    public List<T> findAll(Class clazz) throws MobileDAOException {
        try {
            List<T> entities = null;
            Query query = entityManager.createQuery("from " + clazz.getName());
            entities = (List<T>) query.getResultList();
            return entities;
        } catch (Exception e){
            String message = "Error on load entities: " + clazz.getSimpleName();
            logger.error(message);
            throw new MobileDAOException(message, e);
        }
    }


}
