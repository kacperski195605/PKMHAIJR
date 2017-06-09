package pkmhaijr.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pkmhaijr.model.dbEntities.Product;

import java.util.ArrayList;

/**
 * Created by patry on 23/04/17.
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor {

    @Query(nativeQuery = true, value= "Select * from PRODUCTS p where p.title like %:title%")
    ArrayList<Product> findProducts(@Param("title") String title);

}
