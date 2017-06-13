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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Asasello on 12-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class ProductQueriesTest {
    @Autowired
    ProductService productService;
    @Autowired
    AuthorService authorService;

    //Remember to update after add new product in setUp()
    private static int AlternativeCount = 4;
    private static int ALLCount = 11;
    private static int FolkCount = 3;
    private static int ClassicalCount = 2;

    @Before
    public void setUp() {
        Author author = new Author();
        author.setName("Name1");
        author.setDescription("Description1");
        author = authorService.addAuthor(author);

        productService.addProduct(new Product(new BigDecimal("0.0"),"FirstTitle_01_ALTERNATIVE",ProductType.CD,"desc",Genre.ALTERNATIVE, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_02_ALTERNATIVE",ProductType.CD,"desc",Genre.ALTERNATIVE, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_03_ALL",ProductType.CD,"desc",Genre.ALL, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_04_CLASSICAL",ProductType.CD,"desc",Genre.CLASSICAL, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_05_FOLK",ProductType.CD,"desc",Genre.FOLK, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_06_FOLK",ProductType.CD,"desc",Genre.FOLK, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_07_FOLK",ProductType.CD,"desc",Genre.FOLK, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_08_CLASSICAL",ProductType.CD,"desc",Genre.CLASSICAL, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"FirstTitle_09_ALTERNATIVE",ProductType.CD,"desc",Genre.ALTERNATIVE, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_10_ALL",ProductType.CD,"desc",Genre.ALL, author));
        productService.addProduct(new Product(new BigDecimal("0.0"),"Title_11_ALTERNATIVE",ProductType.CD,"desc",Genre.ALTERNATIVE, author));
    }

    @After
    public void clean() {
        productService.deleteAllProducts();
        authorService.deleteAllAuthors();
    }

    @Test
    public void productByGenreSearchTest_FOLK(){
        List<Product> sortedProductList = productService.getSortedProduct(Genre.FOLK);
        assertNotNull("sortedProductList should not be null", sortedProductList);
        assertEquals("sortedProductList should contain "+FolkCount+" products",FolkCount, sortedProductList.size());
    }

    @Test
    public void productByGenreSearchTest_ALL(){
        List<Product> sortedProductList = productService.getSortedProduct(Genre.ALL);
        assertNotNull("sortedProductList should not be null", sortedProductList);
        assertEquals("sortedProductList should contain "+ALLCount+" products",ALLCount, sortedProductList.size());
    }

    @Test
    public void productByGenreSearchTest_OTHER(){
        List<Product> sortedProductList = productService.getSortedProduct(Genre.POP);
        assertNotNull("sortedProductList should not be null", sortedProductList);
        assertEquals("sortedProductList should contain "+0+" products",0, sortedProductList.size());
    }

    @Test
    public void productByNameTestFirst(){
        List<Product> sortedProductList = productService.getSortedProduct("First");
        assertNotNull("sortedProductList should not be null", sortedProductList);
        assertEquals("sortedProductList should contain "+2+" products",2, sortedProductList.size());
    }

    @Test
    public void productByNameTestNE(){
        List<Product> sortedProductList = productService.getSortedProduct("NNNNNNNNNNN");
        assertNotNull("sortedProductList should not be null", sortedProductList);
        assertEquals("sortedProductList should contain "+0+" products",0, sortedProductList.size());
    }
}
