package com.tsystems.jschool.mobile.dao.API;

import com.tsystems.jschool.mobile.entities.User;

import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public interface UserDAO extends GenericDAO<User> {

    List<User> getUserByEmailPassword(String email, String password);

    List<User> getUserByPhoneNumber(String number);

    List<User> getUserByEmail(String email);

    List<User> getUserBySurname(String surname);

}
