package pkmhaijr.model.dbEntities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by patry on 22/04/17.
 */

@Data
@Entity
@Table(name = "WISHLIST")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    private Set<Product> products;
}
