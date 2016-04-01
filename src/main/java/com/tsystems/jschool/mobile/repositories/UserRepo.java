//package com.tsystems.jschool.mobile.repositories;
//
//import com.tsystems.jschool.mobile.entities.Contract;
//import com.tsystems.jschool.mobile.entities.User;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository()
//public interface UserRepo extends JpaRepository<User, Integer> {
//
//    Page<User> findAll(Pageable pageable);
//
//    @Query("select u from User u where u.surname like ?1")
//    Page<User> findBySurname(String text, Pageable pageable);
//
//    @Query("select u from User u where u.email like ?1")
//    Page<User> findByEmail(String text, Pageable pageable);
//
//    @Query("SELECT x FROM User x LEFT JOIN x.contracts c WHERE c.number LIKE ?1")
//    Page<User> findByContract(String text, Pageable pageable);
//}
