package pkmhaijr.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patry on 23/04/17.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(nativeQuery = true, value= "Select * from PRODUCTS p where p.title like %:title%")
    List<Product> findProducts(@Param("title") String title);

    @Query(nativeQuery = true, value= "Select * from PRODUCTS p where p.title like %:title% and p.genre in :genre)")//= \':genre\'")
    List<Product> findProducts(@Param("title") String title, @Param("genre")Genre genre);

    List<Product> findByGenre(Genre genre);
}
