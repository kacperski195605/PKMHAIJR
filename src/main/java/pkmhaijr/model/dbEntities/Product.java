package pkmhaijr.model.dbEntities;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by patry on 18/04/17.
 */

@Data
@Entity
@Table(name = "PRODUCTS")
@Log4j2
public class Product implements Serializable, Cloneable {

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
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private Author author;

    public Product() {
        this(new BigDecimal("0.0"), "", null, "", null);
    }

    public Product(BigDecimal price, String title, ProductType type, String description, Genre genre) {
        this(price, title, type, description, genre, null);
    }

    public Product(BigDecimal price, String title, ProductType type, String description, Genre genre, Author author) {
        this.price = price;
        this.title = title;
        this.type = type;
        this.description = description;
        this.genre = genre;
        this.author = author;
    }

    @Override
    public Product clone() {
        Product cloned = new Product(price, title, type, description, genre, Objects.isNull(author) ? null : author.clone());
        cloned.setId(id);
        return cloned;
    }
}
