package services.Impl;

import dao.API.GenericDAO;
import dao.Impl.GenericDAOImpl;
import dao.JpaUtil;
import services.API.GenericService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Alexandra on 23.02.2016.
 */
//public class GenericServiceImpl<T> implements GenericService<T> {
//
//    private GenericDAO<T, Integer> dao;
//
//    public List<T> getAll() {
//
//        List<T> list;
//        EntityManager em = JpaUtil.getEntityManager();
//        dao = new GenericDAOImpl<T, Integer>(em);
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//
//    }
//
//    protected GenericDAO<T, Integer> getDAO(){
//        return dao;
//    }
//
//    public void setDao(GenericDAO<T, Integer> dao) {
//        this.dao = dao;
//    }
//}
