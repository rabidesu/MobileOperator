package com.tsystems.jschool.mobile.services.API;

import com.tsystems.jschool.mobile.entities.User;
import com.tsystems.jschool.mobile.enumerates.RoleName;

import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
public interface UserService {

    User adminExists(String email, String password);

    User clientExists(String email, String password);

    User userExists(String email, String password, RoleName role);

    List<User> getAllUsers();

    List<User> getUserByField(String searchText, String searchField);

    String addUser(String name, String surname, String date, String passport, String address, String email, String password);

    User getUserById(String id);

}
