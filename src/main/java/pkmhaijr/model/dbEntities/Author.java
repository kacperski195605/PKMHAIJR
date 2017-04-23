package pkmhaijr.model.dbEntities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patry on 22/04/17.
 */

@Data
@Entity
@Table(name = "AUTHORS")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column
    @Size(min = 1, max = 80)
    private String name;

    @NotNull
    @Column
    @Size(min = 1, max = 2000)
    private String description;

    @OneToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER, mappedBy = "author")
    private Set<Product> products = new HashSet<>();

}
