package services.Impl;

import dao.Impl.UserDAOImpl;
import dao.API.UserDAO;
import dao.JpaUtil;
import entities.QueryNames;
import entities.Role;
import entities.User;
import services.API.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Alexandra on 18.02.2016.
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public User adminExists(String email, String password) {
        return userExists(email, password, "admin");
    }

    public User clientExists(String email, String password) {
        return userExists(email, password, "client");
    }

    public User userExists(String email, String password, String role) {
        EntityManager em = JpaUtil.getEntityManager();
        userDAO = new UserDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<User> users = userDAO.getUserByEmailPassword(email, password);
        transaction.commit();
        em.close();
        if (users.isEmpty()) return null;
        else {
            for (Role userRole : users.get(0).getRole()){
                if (userRole.getRoleName().equals(role)) return users.get(0);
            }
        }
        return null;
    }

    public String addUser(String name, String surname, String date, String passport, String address, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPassport(passport);
        user.setAddress(address);
        user.setEmail(email);
        user.setPassword(password);
        Date birthday = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        try {
            birthday = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        user.setBirthday(birthday);

        EntityManager em = JpaUtil.getEntityManager();
        userDAO = new UserDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        userDAO.save(user);
        transaction.commit();
        em.close();
        return "OK";
    }

    public List<User> getAllUsers(){
        EntityManager em = JpaUtil.getEntityManager();
        userDAO = new UserDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<User> users = userDAO.findAll(User.class);
        transaction.commit();
        em.close();
        return users;
    }



    public List<User> getUserByNumber(String number) {
        EntityManager em = JpaUtil.getEntityManager();
        userDAO = new UserDAOImpl(em);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        List<User> users = userDAO.getUserByPhoneNumber(number);
        transaction.commit();
        em.close();
        return users;
    }


    //    public boolean userIsAdmin(int id){
//
//    }
//
//    public boolean userIsClient(int id){
//
//    }
//
//    public User findUserById(int id){
//
//    }
//
//    public User findUserByEmail(String email){
//
//    }
}
