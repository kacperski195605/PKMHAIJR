package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pkmhaijr.model.dbEntities.Author;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.Wishlist;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import java.math.BigDecimal;
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
    @Autowired ProductService productService;
    @Autowired AuthorService authorService;

    private Wishlist wishlist1;
    private Wishlist wishlist2;
    private Wishlist wishlist3;
    private Product product1;
    private Product product2;
    private Product product3;
    private Author author1;

    @Before
    public void setUp(){

        author1 = new Author();
        author1.setName("Name1");
        author1.setDescription("Description1");

        product1 = new Product();
        product1.setDescription("Description1");
        product1.setTitle("Title1");
        product1.setPrice(new BigDecimal("14.99"));
        product1.setAuthor(author1);
        product1.setGenre(Genre.ALTERNATIVE);
        product1.setType(ProductType.VINYL);

        product2 = new Product();
        product2.setDescription("Description2");
        product2.setTitle("Title2");
        product2.setPrice(new BigDecimal("12.99"));
        product2.setAuthor(author1);
        product2.setGenre(Genre.BLUES);
        product2.setType(ProductType.CD);

        product3 = new Product();
        product3.setDescription("Description3");
        product3.setTitle("Title3");
        product3.setPrice(new BigDecimal("9.99"));
        product3.setAuthor(author1);
        product3.setGenre(Genre.FOLK);
        product3.setType(ProductType.CD);

        Set<Product> productSet1 = new HashSet<>();
        productSet1.add(product1);

        Set<Product> productSet2 = new HashSet<>();
        productSet2.add(product2);

        Set<Product> productSet3 = new HashSet<>();
        productSet3.add(product3);

        wishlist1 = new Wishlist();
        wishlist1.setProducts(productSet1);

        wishlist2 = new Wishlist();
        wishlist2.setProducts(productSet2);

        wishlist3 = new Wishlist();
        wishlist3.setProducts(productSet3);

    }

    private void addAllWishLists(){
        authorService.addAuthor(author1);

        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);

        wishlistService.addWishlist(wishlist1);
        wishlistService.addWishlist(wishlist2);
        wishlistService.addWishlist(wishlist3);
    }

    @Transactional
    @Test
    public void findWishListByIdTest(){
        log.info("Testing finding wishlist by id");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        product2 = productService.addProduct(product2);

        wishlist1 = wishlistService.addWishlist(wishlist1);

        //action
        Wishlist newWishlist = wishlistService.findWishlistById(wishlist1.getId());

        //assertion
        assertNotNull("Wishlist should not be null", newWishlist);
        assertEquals("Wishlists should be equal", wishlist1, newWishlist);
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

    @Transactional
    @Test
    public void countOfNotEmptyDatabaseTest(){
        log.info("Testing count of not empty database");
        //preparation
        addAllWishLists();
        int expectedCount = 3;

        //action
        int actualCount = wishlistService.countWishlists();

        //assertion
        assertEquals("Count should be equal to 3", expectedCount, actualCount);
    }

    @Transactional
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

    @Transactional
    @Test
    public void deleteWishlistTest(){
        log.info("Testing deleting wishlist");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        product2 = productService.addProduct(product2);
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

    @Transactional
    @Test
    public void validUpdateWishlist(){
        log.info("Testing valid updating wishlist");
        //prepartion
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        product2 = productService.addProduct(product2);
        wishlist1 = wishlistService.addWishlist(wishlist1);
        Set<Product> newProductsSet = new HashSet<>();
        newProductsSet.add(product3);
        wishlist1.setProducts(newProductsSet);

        //action
        wishlistService.updateWishlist(wishlist1);
        Wishlist newWishlist = wishlistService.findWishlistById(wishlist1.getId());


        //assertion
        assertNotNull("New wishlist should not be null", newWishlist);
        assertEquals("New wishlist should have updated product set", wishlist1.getProducts(), newWishlist.getProducts());
    }

    @Transactional
    @Test(expected = IllegalArgumentException.class)
    public void invalidUpdateWishlist(){
        log.info("Testing invalid updating wishlist");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        product2 = productService.addProduct(product2);
        wishlist1 = wishlistService.addWishlist(wishlist1);
        wishlistService.deleteWishlist(wishlist1);

        //action
        wishlistService.updateWishlist(wishlist1);
    }

    @Transactional
    @Test
    public void deleteAllWishlistsTest(){
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
