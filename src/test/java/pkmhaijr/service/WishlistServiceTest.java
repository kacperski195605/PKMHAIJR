package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.Wishlist;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by anastasiia on 5/16/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class WishlistServiceTest {

    @Autowired WishlistService wishlistService;

    private Wishlist wishlist1;
    private Wishlist wishlist2;
    private Wishlist wishlist3;

    @Before
    public void setUp(){

//        Set<Product> products = new TreeSet<>();
//        products.add(new Product());
//        products.add(new Product());

        wishlist1 = new Wishlist();
        //wishlist1.setProducts(products);
        wishlist2 = new Wishlist();
        wishlist3 = new Wishlist();
    }

    private void addAllWishLists(){
        wishlistService.addWishlist(wishlist1);
        wishlistService.addWishlist(wishlist2);
        wishlistService.addWishlist(wishlist3);
    }

    @Test
    public void findWishListByIdTest(){
        log.info("Testing finding wishlist by id");
        //preparation
        wishlist1 = wishlistService.addWishlist(wishlist1);

        //action
        Wishlist newWishlist = wishlistService.findWishlistById(wishlist1.getId());

        //assertion
        assertNotNull("Wishlist should not be null", newWishlist);
        assertEquals("Wishlist should be equal", wishlist1, newWishlist);
    }

    @Test
    public void countOfEmptyDatabaseTest(){
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = wishlistService.countWishlists();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @Test
    public void countOfNotEmptyDatabaseTest(){
        log.info("Testing count of not empty database");
        //preparation
        addAllWishLists();
        int expectedCount = 3;

        //action
        int actualCount = wishlistService.countWishlists();

        //asseertion
        assertEquals("Count should be equal to 3", expectedCount, actualCount);
    }

    @Test
    public void findAllWishlistsTest(){
        log.info("Testing finding all wishlists");
        //preparation
        addAllWishLists();
        int expectedCount = 3;

        //action
        List<Wishlist> wishlists = wishlistService.findAllWishlists();

        //assertion
        assertNotNull("List shoul not be null", wishlists);
        assertEquals("List should have a lenght of 3", expectedCount, wishlists.size());
        //TODO: change these 3 into something nicer
        assertTrue("List should contain wishlist 1", wishlists.contains(wishlist1));
        assertTrue("List should contain wishlist 2", wishlists.contains(wishlist2));
        assertTrue("List should contain wishlist 3", wishlists.contains(wishlist3));
    }

    @Test
    public void deleteWishlistTest(){
        log.info("Testing deleting wishlist");
        //preparation
        wishlist1 = wishlistService.addWishlist(wishlist1);
        int expectedCount = 0;

        //action
        wishlistService.deleteWishlist(wishlist1);
        int actualCount = wishlistService.countWishlists();
        Wishlist newWishlist = wishlistService.findWishlistById(wishlist1.getId());

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
        assertNull("newWishlist should be null", newWishlist);
    }

    @Test
    public void validUpdateAddress(){
        log.info("Testing valid updating wishlist");
        //prepartion
        wishlist1 = wishlistService.addWishlist(wishlist1);
        //TODO make changes in wishlist1
        //wishlist1.setProducts();

        //action
        wishlistService.updateWishlist(wishlist1);
        Wishlist newWishlist = wishlistService.findWishlistById(wishlist1.getId());

        //assertion
        assertNotNull("New wishlist should not be null", newWishlist);
        assertEquals("New wishlist should have updated product set", wishlist1.getProducts(), newWishlist.getProducts());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidUpdateWishlist(){
        log.info("Testing invalid updating wishlist");
        //preparation
        wishlist1 = wishlistService.addWishlist(wishlist1);
        wishlistService.deleteWishlist(wishlist1);

        //action
        wishlistService.updateWishlist(wishlist1);
    }

    @Test
    public void deleteAllWislistTest(){
        log.info("Testing deleting all wishlists");
        //preparation
        addAllWishLists();
        int expectedCount = 0;

        //action
        wishlistService.deleteAllWishlists();
        int actualCount = wishlistService.countWishlists();

        //assertion
        assertEquals("Count should bre equal to 0", expectedCount, actualCount);
    }

    @After
    public void clean(){
        wishlistService.deleteAllWishlists();
    }


}
