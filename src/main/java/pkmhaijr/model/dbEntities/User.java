package pkmhaijr.model.dbEntities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patry on 23/04/17.
 */

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @Size(max = 80)
    private String firstName;

    @Column
    @Size(max = 80)
    private String lastName;

    @OneToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    private Set<CreditCard> cards = new HashSet<>();

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
//        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
//        final Object this$addresses = this.getAddresses();
//        final Object other$addresses = other.getAddresses();
//        if (this$addresses == null ? other$addresses != null : !this$addresses.equals(other$addresses)) return false;
//        final Object this$cards = this.getCards();
//        final Object other$cards = other.getCards();
//        if (this$cards == null ? other$cards != null : !this$cards.equals(other$cards)) return false;
        return true;
    }
}
