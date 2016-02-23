package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Alexandra on 15.02.2016.
 */

@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    @Id
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

    @ManyToMany
    @JoinTable(name = "contract_option", joinColumns = {@JoinColumn(name = "contract_id")},
            inverseJoinColumns = {@JoinColumn(name = "option_id")})
    private List<Option> options;

    @Column(name = "is_blocked_by_client")
    private boolean isBlockedByClient;

    @Column(name = "is_blocked_by_admin")
    private boolean isBlockedByAdmin;

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
        return isBlockedByClient;
    }

    public void setBlockedByClient(boolean blockedByClient) {
        isBlockedByClient = blockedByClient;
    }

    public boolean isBlockedByAdmin() {
        return isBlockedByAdmin;
    }

    public void setBlockedByAdmin(boolean blockedByAdmin) {
        isBlockedByAdmin = blockedByAdmin;
    }
}
