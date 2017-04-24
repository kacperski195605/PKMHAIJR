package pkmhaijr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.dbEntities.Wishlist;
import pkmhaijr.WishlistRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by patry on 23/04/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public Wishlist addWishlist(Wishlist wishlist) {
        log.info("Adding wishlist: " + wishlist);
        return wishlistRepository.save(wishlist);
    }

    public Wishlist findWishlistById(Integer id) {
        log.info("Searching for wishlist with id: " + id);
        return wishlistRepository.findOne(id);
    }

    public List<Wishlist> findAllWishlists() {
        log.info("Getting all wishlists");
        return StreamSupport.stream(wishlistRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteWishlist(Wishlist wishlist) {
        log.info("Deleting wishlist: " + wishlist);
        wishlistRepository.delete(wishlist);
    }

    public Wishlist updateWishlist(Wishlist newWishlist) {
        log.info("Updating wishlist: " + newWishlist);
        Wishlist oldWishlist = findWishlistById(newWishlist.getId());
        if (oldWishlist != null) {
            return wishlistRepository.save(newWishlist);
        } else {
            throw new IllegalArgumentException("There is no wishlist with id " + newWishlist.getId() + " in database");
        }
    }
}
