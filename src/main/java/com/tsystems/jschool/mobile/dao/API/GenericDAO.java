package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public interface GenericDAO<T> {

    void save(T entity, EntityManager entityManager) throws MobileDAOException;

    void merge(T entity, EntityManager entityManager) throws MobileDAOException;

    void delete(T entity, EntityManager entityManager) throws MobileDAOException;

    List<T> findMany(Query query) throws MobileDAOException;

    List<T> findAll(Class clazz, EntityManager entityManager) throws MobileDAOException;

    T findById(Class clazz, int id, EntityManager entityManager) throws MobileDAOException;

}
