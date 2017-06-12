package pkmhaijr.model.dbEntities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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
public class Author implements Serializable, Cloneable {

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

    @Override
    public Author clone() {
        Author cloned = new Author();
        cloned.setName(name);
        cloned.setDescription(description);
        cloned.setId(id);
        return cloned;
    }
    
}
