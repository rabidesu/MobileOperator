package com.tsystems.jschool.mobile.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
//
@Entity
@Table(name = "tariff")
@NamedQueries({
        @NamedQuery(name = Tariff.GET_BY_NAME, query = "SELECT x FROM Tariff x WHERE x.name LIKE ?1"),
        @NamedQuery(name = Tariff.REMOVE_BY_ID, query = "DELETE FROM Tariff x WHERE x.id = ?1")
})
public class Tariff implements Serializable {

    public static final String GET_BY_NAME = "tariffGetByName";
    public static final String REMOVE_BY_ID = "tariffRemoveById";

    @Id
    @GeneratedValue
    @Column(name = "tariff_id")
    private int id;

    @Column(name = "tariff_name", nullable = false, unique = true)
    private String name;

    @Column(name = "tariff_price", nullable = false)
    private int price;

//    @Column(name = "description", nullable = false)
//    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tariff_option", joinColumns = {@JoinColumn(name = "tariff_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    private List<Option> options;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
