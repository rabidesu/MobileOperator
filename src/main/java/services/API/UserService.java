package services.API;

import entities.User;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
public interface UserService {

    public User adminExists(String email, String password);

    public User clientExists(String email, String password);

    public User userExists(String email, String password, String role);

    public List<User> getAllUsers();

    public List<User> getUserByField(String searchText, String searchField);


    public String addUser(String name, String surname, String date, String passport, String address, String email, String password);

    public User getUserById(String id);

//    public boolean userIsAdmin(int id);
//
//    public boolean userIsClient(int id);
//
//    public User findUserById(int id);
//
//    public User findUserByEmail(String email);
}
