package com.tsystems.jschool.mobile.entities;

import com.tsystems.jschool.mobile.enumerates.RoleName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */

@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = Role.GET_BY_NAME, query = "SELECT x FROM Role x WHERE x.roleName = ?1")
})
public class Role implements Serializable {

    public static final String GET_BY_NAME = "roleGetByName";

    @Id
    @Column(name = "role_id")
    private int id;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users = new ArrayList<User>();

    public int getId() {
        return id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
