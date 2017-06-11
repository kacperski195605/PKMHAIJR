package pkmhaijr.model.dbEntities;

import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import java.math.BigDecimal;

/**
 * Created by patry on 22/04/17.
 */
public class Album extends Product {

    public Album(BigDecimal price, String title, ProductType type, String description, Genre genre) {
        super(price, title, type, description, genre);
    }

    public Album(BigDecimal price, String title, ProductType type, String description, Genre genre, Author author) {
        super(price, title, type, description, genre, author);
    }
}
