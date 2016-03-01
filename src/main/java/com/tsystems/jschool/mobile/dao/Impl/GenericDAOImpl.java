package com.tsystems.jschool.mobile.dao.Impl;

import com.tsystems.jschool.mobile.dao.API.GenericDAO;
import com.tsystems.jschool.mobile.dao.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 19.02.2016.
 */
public  class GenericDAOImpl<T> implements GenericDAO<T> {

    protected EntityManager entityManager;

    public GenericDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

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
        List<T> list;
        list = (List<T>) query.getResultList();
        return list;
    }

    public T findById(Class clazz, int id) {
        T entity = null;
        entity = (T) entityManager.find(clazz, id);
        return entity;
    }

    public List<T> findAll(Class clazz) {

        List<T> list = null;
        Query query = entityManager.createQuery("from " + clazz.getName());
        list = (List<T>) query.getResultList();
        return list;
    }


}
