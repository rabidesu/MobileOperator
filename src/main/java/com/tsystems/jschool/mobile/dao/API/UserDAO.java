package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    List<User> getUserByEmail(String email);

    List<User> getPageUsersByPhoneNumber(int page, int pageSize, String number);

    List<User> getPageUsersByEmail(int page, int pageSize, String email);

    List<User> getPageUsersBySurname(int page, int pageSize, String surname);

    long getCountUsers();

    long getCountUserByNumber(String number);

    long getCountUserByEmail(String email);

    long getCountUserBySurname(String surname);

    List<User> getPageUsers(int page, int pageSize);

}
