package com.tsystems.jschool.mobile.dao.API;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 19.02.2016.
 */
public interface GenericDAO<T> {

    void save(T entity);

    void merge(T entity);

    void delete(T entity);

    List<T> findMany(Query query);

    List<T> findAll(Class clazz);

    T findById(Class clazz, int id);

}
