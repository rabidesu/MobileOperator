package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.exceptions.MobileDAOException;

import javax.persistence.EntityManager;
import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    List<User> getUserByPhoneNumber(String number) throws MobileDAOException;

    List<User> getUserByEmail(String email) throws MobileDAOException;

    List<User> getUserBySurname(String surname) throws MobileDAOException;

}
