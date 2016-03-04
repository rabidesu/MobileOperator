package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;
import com.tsystems.jschool.mobile.exceptions.LoginUserException;
import com.tsystems.jschool.mobile.exceptions.MobileServiceException;

import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
public interface UserService {

    User adminExists(String email, String password) throws MobileServiceException, LoginUserException;

    User clientExists(String email, String password) throws MobileServiceException, LoginUserException;

    User userExists(String email, String password, RoleName role) throws MobileServiceException, LoginUserException;

    List<User> getAllUsers() throws MobileServiceException;

    List<User> getUserByField(String searchText, String searchField) throws MobileServiceException;

    User getUserById(String id) throws MobileServiceException;

}
