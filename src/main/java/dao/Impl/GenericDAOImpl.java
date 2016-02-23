package dao.Impl;

import dao.API.GenericDAO;
import dao.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 19.02.2016.
 */
public  class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private EntityManager entityManager;

    public GenericDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager(){
        return entityManager;
    }

    public void save(T entity) {
       // EntityManager em = this.getEntityManager();
        entityManager.persist(entity);
    }

    public void merge(T entity) {
       // EntityManager em = this.getEntityManager();
        entityManager.merge(entity);
    }

    public void delete(T entity) {
       // EntityManager em = this.getEntityManager();
        entityManager.remove(entity);
    }

    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.getResultList();
        return t;
    }

    public T findOne(Query query) {
        T t;
        t = (T) query.getSingleResult();
        return t;
    }

    public T findById(Class clazz, int id) {
        //EntityManager em = this.getEntityManager();
        T t = null;
        t = (T) entityManager.find(clazz, id);
        return t;
    }

    public List findAll(Class clazz) {

        List T = null;
        Query query = entityManager.createQuery("from " + clazz.getName());
        T = query.getResultList();
        return T;
    }


}
