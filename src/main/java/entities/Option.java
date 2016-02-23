package entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 08.02.2016.
 */
//
@Entity
@Table(name = "t_option")
public class Option implements Serializable {

    @Id
    @Column(name = "option_id")
    private int id;

    @Column(name = "option_name", nullable = false, unique = true)
    private String name;

    @Column(name = "option_price", nullable = false)
    private int price;

    @Column(name = "connect_price", nullable = false)
    private int connectPrice;

    @ManyToMany
    @JoinTable(name = "option_required",
            joinColumns = @JoinColumn(name = "option_current_id"),
            inverseJoinColumns = @JoinColumn(name = "option_required_id"))
    private final List<Option> optionsRequired = new ArrayList<Option>();

    @ManyToMany
    @JoinTable(name = "option_incompatible",
            joinColumns = @JoinColumn(name = "option_current_id"),
            inverseJoinColumns = @JoinColumn(name = "option_incompatible_id")
    )
    private final List<Option> optionsIncompatible = new ArrayList<Option>();

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
}
