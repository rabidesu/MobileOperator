package com.tsystems.jschool.mobile.entities;


import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "t_option")
@NamedQueries({
        @NamedQuery(name = Option.GET_ANOTHER_OPTIONS, query = "SELECT x FROM Option x WHERE x.id != ?1 and x.available = true"),
        @NamedQuery(name = Option.REMOVE_BY_ID, query = "DELETE FROM Option x WHERE x.id = ?1"),
        @NamedQuery(name = Option.GET_BY_NAME, query = "SELECT x FROM Option x WHERE x.name LIKE ?1"),
        @NamedQuery(name = Option.GET_AVAILABLE_OPTIONS, query = "SELECT x FROM Option x WHERE x.available = true")
})
public class Option implements Serializable {

    public static final String GET_ANOTHER_OPTIONS= "optionsGetAnotherOption";
    public static final String REMOVE_BY_ID = "optionsRemoveById";
    public static final String GET_BY_NAME = "optionsGetByName";
    public static final String GET_AVAILABLE_OPTIONS = "getAvailableOptions";

    @Id
    @GeneratedValue
    @Column(name = "option_id")
    private int id;

    @NotEmpty
    @Column(name = "option_name", nullable = false, unique = true)
    private String name;

    @Range(min = 0, max = 99999)
    @NotNull
    @Column(name = "option_price", nullable = false)
    private int price;

    @Range(min = 0, max = 99999)
    @NotNull
    @Column(name = "connect_price", columnDefinition = "default '0'")
    private int connectPrice;

    @Column(name = "available")
    private boolean available = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "option_required",
            joinColumns = @JoinColumn(name = "option_current_id"),
            inverseJoinColumns = @JoinColumn(name = "option_required_id"))
    private List<Option> optionsRequired = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "option_incompatible",
            joinColumns = @JoinColumn(name = "option_current_id"),
            inverseJoinColumns = @JoinColumn(name = "option_incompatible_id"))
    private List<Option> optionsIncompatible = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public int hashCode() {
        return new Long(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Option)) {
            return false;
        }
        return this.id == ((Option)obj).getId();
    }
}
