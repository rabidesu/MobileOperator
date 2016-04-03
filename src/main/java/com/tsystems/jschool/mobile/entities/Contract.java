package com.tsystems.jschool.mobile.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */

@Entity
@Table(name = "contract")
@NamedQueries({
        @NamedQuery(name = Contract.GET_COUNT_BY_NUMBER, query = "SELECT COUNT(x.id) FROM Contract x WHERE x.number LIKE ?1"),
        @NamedQuery(name = Contract.GET_COUNT, query = "SELECT COUNT(x.id) FROM Contract x"),
        @NamedQuery(name = Contract.GET_ALL, query = "SELECT x FROM Contract x"),
        @NamedQuery(name = Contract.GET_BY_NUMBER, query = "SELECT x FROM Contract x WHERE x.number LIKE ?1"),
        @NamedQuery(name = Contract.GET_WITH_TARIFF, query = "SELECT x FROM Contract x WHERE x.tariff.id = ?1"),
        @NamedQuery(name = Contract.GET_WITH_OPTION, query = "SELECT x FROM Contract x LEFT JOIN x.options c WHERE c.id = ?1")
})
public class Contract implements Serializable {

    public static final String GET_BY_NUMBER = "contractGetByNumber";
    public static final String GET_WITH_TARIFF = "contractGetWithTariff";
    public static final String GET_WITH_OPTION = "contractGetWithOption";
    public static final String GET_ALL = "contractGetALL";
    public static final String GET_COUNT = "contractGetCount";
    public static final String GET_COUNT_BY_NUMBER = "contractGetCountByNumber";

    @Id
    @GeneratedValue
    @Column(name = "contract_id")
    private int id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToOne
    @JoinColumn(name = "tariff_id", nullable = false)
    @JsonManagedReference
    private Tariff tariff;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contract_option", joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    @JsonManagedReference
    private List<Option> options;

    @Column(name = "is_blocked_by_client")
    private boolean blockedByClient;

    @Column(name = "is_blocked_by_admin")
    private boolean blockedByAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public boolean isBlockedByClient() {
        return blockedByClient;
    }

    public void setBlockedByClient(boolean blockedByClient) {
        this.blockedByClient = blockedByClient;
    }

    public boolean isBlockedByAdmin() {
        return blockedByAdmin;
    }

    public void setBlockedByAdmin(boolean blockedByAdmin) {
        this.blockedByAdmin = blockedByAdmin;
    }
}
