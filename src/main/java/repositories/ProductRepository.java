package repositories;

import org.springframework.data.repository.CrudRepository;
import pkmhaijr.model.dbEntities.Product;

/**
 * Created by patry on 23/04/17.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
