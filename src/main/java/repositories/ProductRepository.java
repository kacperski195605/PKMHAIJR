package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pkmhaijr.model.dbEntities.Product;

/**
 * Created by patry on 23/04/17.
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
