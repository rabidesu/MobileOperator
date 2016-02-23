package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */
//
@Entity
@Table(name = "tariff")
public class Tariff implements Serializable {

    @Id
    @Column(name = "tariff_id")
    private int id;

    @Column(name = "tariff_name", nullable = false, unique = true)
    private String name;

    @Column(name = "tariff_price", nullable = false)
    private int price;

    @ManyToMany
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
}
