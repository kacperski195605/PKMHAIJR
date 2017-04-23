package repositories;

import org.springframework.data.repository.CrudRepository;
import pkmhaijr.model.dbEntities.Wishlist;

/**
 * Created by patry on 23/04/17.
 */
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
}
