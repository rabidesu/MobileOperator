package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.exceptions.MobileDAOException;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public interface GenericDAO<T>{

    void save(T entity) throws MobileDAOException;

    void merge(T entity) throws MobileDAOException;

    void delete(T entity) throws MobileDAOException;

    List<T> findMany(Query query) throws MobileDAOException;

    List<T> findAll(Class clazz) throws MobileDAOException;

    T findById(Class clazz, int id) throws MobileDAOException;

}
