package com.tsystems.jschool.mobile.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 08.02.2016.
 */
//
@Entity
@Table(name = "t_option")
@NamedQueries({
        @NamedQuery(name = Option.GET_ANOTHER_OPTIONS, query = "SELECT x FROM Option x WHERE x.id != ?1"),
        @NamedQuery(name = Option.REMOVE_BY_ID, query = "DELETE FROM Option x WHERE x.id = ?1"),
        @NamedQuery(name = Option.GET_BY_NAME, query = "SELECT x FROM Option x WHERE x.name LIKE ?1")
})
public class Option implements Serializable {

    public static final String GET_ANOTHER_OPTIONS= "optionsGetAnotherOption";
    public static final String REMOVE_BY_ID = "optionsRemoveById";
    public static final String GET_BY_NAME = "optionsGetByName";

    @Id
    @GeneratedValue
    @Column(name = "option_id")
    private int id;

    @Column(name = "option_name", nullable = false, unique = true)
    private String name;

    @Column(name = "option_price", nullable = false)
    private int price;

    @Column(name = "connect_price", nullable = false)
    private int connectPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "option_required",
            joinColumns = @JoinColumn(name = "option_current_id"),
            inverseJoinColumns = @JoinColumn(name = "option_required_id"))
    private List<Option> optionsRequired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "option_incompatible",
            joinColumns = @JoinColumn(name = "option_current_id"),
            inverseJoinColumns = @JoinColumn(name = "option_incompatible_id"))
    private List<Option> optionsIncompatible;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getConnectPrice() {
        return connectPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setConnectPrice(int connectPrice) {
        this.connectPrice = connectPrice;
    }

    public List<Option> getOptionsRequired() {
        return optionsRequired;
    }

    public List<Option> getOptionsIncompatible() {
        return optionsIncompatible;
    }

    public void setOptionsRequired(List<Option> optionsRequired) {
        this.optionsRequired = optionsRequired;
    }

    public void setOptionsIncompatible(List<Option> optionsIncompatible) {
        this.optionsIncompatible = optionsIncompatible;
    }
}
