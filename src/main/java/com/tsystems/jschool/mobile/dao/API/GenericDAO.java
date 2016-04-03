package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public interface GenericDAO<T>{

    void save(T entity);

    void merge(T entity);

    void delete(T entity);

    List<T> findMany(Query query);

    List<T> findAll(Class clazz);

    T findById(Class clazz, int id);

}
