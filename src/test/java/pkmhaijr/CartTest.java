package pkmhaijr;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.app.Cart;
import pkmhaijr.model.dbEntities.Author;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;
import pkmhaijr.service.AuthorService;
import pkmhaijr.service.ProductService;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Asasello on 12-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class CartTest {
    @Autowired
    ProductService productService;
    @Autowired
    AuthorService authorService;
    @Autowired
    private Cart mCart;
    private Product p1, p2;

    @Before
    public void init() {
        Author author = new Author();
        author.setName("Name1");
        author.setDescription("Description1");
        author = authorService.addAuthor(author);
        mCart.clearCart();

        p1 = productService.addProduct(new Product(new BigDecimal("10.0"), "Title", ProductType.CD, "desc", Genre.ALTERNATIVE, author));
        p2 = productService.addProduct(new Product(new BigDecimal("30.0"), "Title", ProductType.CD, "desc", Genre.ALTERNATIVE, author));

    }

    @After
    public void clear() {
        productService.deleteAllProducts();
        authorService.deleteAllAuthors();
    }

    @Test
    public void initTest() {
        assertEquals("Cart should be empty", mCart.getSize(), 0);
        assertEquals(0, mCart.getPrice(), 0.09);
    }

    @Test
    public void insertToCart() {
        mCart.addToCart(p1);
        mCart.addToCart(p1);
        mCart.addToCart(p2);
        assertEquals("Cart should contain 3 products", 3, mCart.getSize());
        assertEquals(50.0, mCart.getPrice(), 0.09);
    }

    @Test
    public void removeFromCart() {
        mCart.addToCart(p1);
        mCart.addToCart(p1);
        mCart.addToCart(p2);
        mCart.getOutOfCart(p2);
        mCart.getOutOfCart(p2);
        assertEquals("Cart should contain 2 products", 2, mCart.getSize());
        assertEquals(20.0, mCart.getPrice(), 0.09);
    }

    @Test
    public void clearCart() {
        mCart.addToCart(p1);
        mCart.addToCart(p1);
        mCart.addToCart(p2);
        mCart.clearCart();
        assertEquals("Cart should contain 0 products", 0, mCart.getSize());
        assertEquals(0.0, mCart.getPrice(), 0.09);

    }
}
