package pkmhaijr.manager;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;
import pkmhaijr.service.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by patry on 10/06/17.
 */

@RunWith(MockitoJUnitRunner.class)
@Log4j2
public class DatabaseFacadeTest {

    @Mock
    private ProductService productService;
    @Mock
    private AddressService addressService;
    @Mock
    private AuthorService authorService;
    @Mock
    private CreditCardService creditCardService;
    @Mock
    private UserService userService;
    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private DatabaseFacade databaseFacade;

    private List<Product> products;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        products = Arrays.asList(
                new Product(new BigDecimal("14.99"), "title1", ProductType.CD, "desc1", Genre.ALTERNATIVE),
                new Product(new BigDecimal("12.99"), "title2", ProductType.CD, "desc2", Genre.ALTERNATIVE),
                new Product(new BigDecimal("16.99"), "title3", ProductType.CD, "desc2", Genre.BLUES),
                new Product(new BigDecimal("8.99"), "title4", ProductType.CD, "desc2", Genre.CLASSICAL),
                new Product(new BigDecimal("13.99"), "title5", ProductType.CD, "desc2", Genre.CLASSICAL),
                new Product(new BigDecimal("23.99"), "title6", ProductType.VINYL, "desc2", Genre.COUNTRY),
                new Product(new BigDecimal("9.99"), "title7", ProductType.CD, "desc2", Genre.DUBSTEP),
                new Product(new BigDecimal("15.99"), "title8", ProductType.CD, "desc2", Genre.ELECTRONIC),
                new Product(new BigDecimal("13.99"), "title9", ProductType.CD, "desc2", Genre.ELECTRONIC),
                new Product(new BigDecimal("17.99"), "title10", ProductType.CD, "desc2", Genre.ELECTRONIC),
                new Product(new BigDecimal("18.99"), "title11", ProductType.VINYL, "desc2", Genre.ELECTRONIC),
                new Product(new BigDecimal("14.99"), "title12", ProductType.CD, "desc2", Genre.FOLK),
                new Product(new BigDecimal("25.99"), "title13", ProductType.VINYL, "desc2", Genre.HOUSE),
                new Product(new BigDecimal("23.99"), "title14", ProductType.VINYL, "desc2", Genre.JAZZ),
                new Product(new BigDecimal("19.99"), "title15", ProductType.VINYL, "desc2", Genre.ELECTRONIC),
                new Product(new BigDecimal("11.99"), "title16", ProductType.CD, "desc2", Genre.PUNK),
                new Product(new BigDecimal("18.99"), "title17", ProductType.VINYL, "desc2", Genre.ROCK)
        );
        Mockito.when(productService.findAllProducts()).thenReturn(products);
    }


    @Test
    public void getAllProductsTest() {
        //preparatiom
        int expectedCount = products.size();

        //action
        List<Product> productsReturned = databaseFacade.getAllProducts();
        int actualCount = productsReturned.size();

        //assertion
        assertEquals("Sizes of lists should be equal", expectedCount, actualCount);
    }
}
