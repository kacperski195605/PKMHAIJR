package pkmhaijr.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by patry on 18/04/17.
 */

@Data
@Entity
@Table(name = "ALBUMS")
public class Album implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(length = 80)
    @Size(min = 1, max = 80)
    private String name;
}
