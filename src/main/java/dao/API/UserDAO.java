package dao.API;

import entities.Role;
import entities.User;

import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    public List<User> getUserByEmailPassword(String email, String password);

    public List<User> getUserByPhoneNumber(String number);

//    public List<Role> getUserRoles ();
//
//    public User getUserById (int id);
//
//    public User getUserByEmail (String email);
}
