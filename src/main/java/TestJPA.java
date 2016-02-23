import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Alexandra on 08.02.2016.
 */
public class TestJPA {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MobilePU");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
//        em.persist(new User("Ann", "mail", "ppp"));
        transaction.commit();

        User u = em.find(User.class, 2);
        for (Role role : u.getRole()){
            System.out.println(role.getRoleName());
        }
        em.close();
        factory.close();
    }
}
