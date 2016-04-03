package com.tsystems.jschool.mobile.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */

@Entity
@Table(name = "tariff")
@NamedQueries({
        @NamedQuery(name = Tariff.GET_BY_NAME, query = "SELECT x FROM Tariff x WHERE x.name LIKE ?1"),
        @NamedQuery(name = Tariff.REMOVE_BY_ID, query = "DELETE FROM Tariff x WHERE x.id = ?1"),
        @NamedQuery(name = Tariff.GET_ALL_AVAILABLE, query = "SELECT x FROM Tariff x WHERE x.available = true")
})
public class Tariff implements Serializable {

    public static final String GET_BY_NAME = "tariffGetByName";
    public static final String REMOVE_BY_ID = "tariffRemoveById";
    public static final String GET_ALL_AVAILABLE = "getAllAvailableTariffs";

    public Tariff() {
    }

    public Tariff(String name, int price, List<Option> options) {
        this.name = name;
        this.price = price;
        this.options = options;
    }

    @Id
    @GeneratedValue
    @Column(name = "tariff_id")
    private int id;

    @NotEmpty
    @Column(name = "tariff_name", nullable = false, unique = true)
    private String name;

    @Range(min = 0, max = 99999)
    @NotNull
    @Column(name = "tariff_price", nullable = false)
    private int price;

    @Column(name = "description", columnDefinition="text")
    @NotNull
    private String description;

    @Column(name = "available")
    private boolean available = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tariff_option", joinColumns = {@JoinColumn(name = "tariff_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    @JsonManagedReference
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

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (! (obj instanceof Tariff)) {
            return false;
        }
        return this.id == ((Tariff)obj).getId();
    }
}
