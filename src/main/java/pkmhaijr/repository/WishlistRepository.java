package pkmhaijr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pkmhaijr.model.dbEntities.Wishlist;

/**
 * Created by patry on 23/04/17.
 */
@Repository
public interface WishlistRepository extends CrudRepository<Wishlist, Integer> {
}
