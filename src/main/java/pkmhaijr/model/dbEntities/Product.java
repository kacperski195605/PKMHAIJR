package pkmhaijr.model.dbEntities;

import lombok.Data;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by patry on 18/04/17.
 */

@Data
@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column
    @Min(value = 0)
    private BigDecimal price;

    @NotNull
    @Column
    @Size(min = 1, max = 80)
    private String title;

    @NotNull
    @Column
    private ProductType type;

    @NotNull
    @Column
    @Size(min = 1, max = 2000)
    private String description;

//    @NotNull
//    @Column
//    @Min(value = 0)
//    private Integer releaseDate;

    @NotNull
    @Column
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;

}
