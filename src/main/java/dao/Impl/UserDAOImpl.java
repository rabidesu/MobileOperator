package dao.Impl;

import dao.API.UserDAO;
import dao.JpaUtil;
import entities.User;

import entities.QueryNames;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

    public UserDAOImpl(EntityManager entityManager) {
        super(entityManager);
    }

    public List<User> getUserByEmailPassword(String email, String password) {

        Query query = JpaUtil.getEntityManager().createNamedQuery(QueryNames.USER_GET_BY_EMAIL_PASSWD);
        query.setParameter(1, email);
        query.setParameter(2, password);
        return findMany(query);
    }

    public List<User> getUserByPhoneNumber(String number) {
        Query query = JpaUtil.getEntityManager().createNamedQuery(QueryNames.USER_GET_BY_PHONE_NUMBER);
        query.setParameter(1, number);
        return findMany(query);
    }

    //    public List<Role> getUserRoles (){
//
//    }
//
//    public User getUserById (int id){
//
//    }
//
//    public User getUserByEmail (String email){
//
//    }
}
