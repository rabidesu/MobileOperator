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

    public int getId() {
        return id;
    }

    public Role(int id) {
        this.id = id;
    }

    public Role() {
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

}
