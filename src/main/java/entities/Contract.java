package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */

@Entity
@Table(name = "contract")
@NamedQueries({
        @NamedQuery(name = Contract.GET_BY_NUMBER, query = "SELECT x FROM Contract x WHERE x.number LIKE ?1")
})
public class Contract implements Serializable {

    public static final String GET_BY_NUMBER = "contractGetByNumber";

    @Id
    @GeneratedValue
    @Column(name = "contract_id")
    private int id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "tariff_id", nullable = false)
    private Tariff tariff;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contract_option", joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    private List<Option> options;

    @Column(name = "is_blocked_by_client")
    private boolean blockedByClient;

    @Column(name = "is_blocked_by_admin")
    private boolean blockedByAdmin;

    public int getId() {
        return id;
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
