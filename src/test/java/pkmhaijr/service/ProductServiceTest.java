package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.dbEntities.Author;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by margo on 5/15/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class ProductServiceTest {

    @Autowired ProductService productService;
    @Autowired AuthorService authorService;

    private Author author1;
    private Author author2;
    private Author author3;
    private Product product1;
    private Product product2;
    private Product product3;

    @Before
    public void setUp() {
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

        author2 = new Author();
        author2.setName("Name2");
        author2.setDescription("Description2");

        product2 = new Product();
        product2.setDescription("Description2");
        product2.setTitle("Title2");
        product2.setPrice(new BigDecimal("12.99"));
        product2.setAuthor(author2);
        product2.setGenre(Genre.BLUES);
        product2.setType(ProductType.CD);

        author3 = new Author();
        author3.setName("Name3");
        author3.setDescription("Description3");

        product3 = new Product();
        product3.setDescription("Description3");
        product3.setTitle("Title3");
        product3.setPrice(new BigDecimal("9.99"));
        product3.setAuthor(author3);
        product3.setGenre(Genre.FOLK);
        product3.setType(ProductType.CD);
    }

    private void addAllProducts() {
        authorService.addAuthor(author1);
        authorService.addAuthor(author2);
        authorService.addAuthor(author3);

        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
    }

    @Test
    public void findProductByIdTest() {
        log.info("Testing finding a product by id");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);

        //action
        Product newProduct = productService.findProductById(product1.getId());

        //assertion
        assertNotNull("Product should not be null", newProduct);
        assertEquals("Products should be equal", product1, newProduct);
    }

    @Test
    public void countOfEmptyDatabaseTest() {
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = productService.countProducts();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @Test
    public void countOfNotEmptyDatabaseTest() {
        log.info("Testing count of not empty database");
        //preparation
        addAllProducts();
        int expectedCount = 3;

        //action
        int actualCount = productService.countProducts();

        //assertion
        assertEquals("Count should be equal to 3", expectedCount, actualCount);
    }

    @Test
    public void findAllProductsTest() {
        log.info("Testing finding all products");
        //preparation
        addAllProducts();
        int expectedCount = 3;

        //action
        List<Product> products = productService.findAllProducts();

        //assertion
        assertNotNull("List should not be null", products);
        assertEquals("List should have a length of 3", expectedCount, products.size());
        //TODO: change these 3 into something nicer
        assertTrue("List should contain address 1", products.contains(product1));
        assertTrue("List should contain address 2", products.contains(product2));
        assertTrue("List should contain address 3", products.contains(product3));
    }

    @Test
    public void deleteProductTest() {
        log.info("Testing deleting product");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        int expectedCount = 0;

        //action
        productService.deleteProduct(product1);
        int actualCount = productService.countProducts();
        Product newProduct = productService.findProductById(product1.getId());

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
        assertNull("newProduct should be null", newProduct);
    }

    @Test
    public void validUpdateProduct() {
        log.info("Testing valid updating product");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        product1.setTitle("New title");

        //action
        productService.updateProduct(product1);
        Product newProduct = productService.findProductById(product1.getId());

        //assertion
        assertNotNull("New product should not be null", newProduct);
        assertEquals("New product should have updated title field", product1.getTitle(), newProduct.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidUpdateProduct() {
        log.info("Testing invalid updating product");
        //preparation
        author1 = authorService.addAuthor(author1);
        product1 = productService.addProduct(product1);
        productService.deleteProduct(product1);

        //action
        productService.updateProduct(product1);
    }

    @Test
    public void deleteAllProductsTest() {
        log.info("Testing deleting all products");
        //preparation
        addAllProducts();
        int expectedCount = 0;

        //action
        productService.deleteAllProducts();
        int actualCount = productService.countProducts();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
    }

    @After
    public void clean() {
        productService.deleteAllProducts();
    }
}
