package pkmhaijr.manager;

import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.model.enums.Genre;
import pkmhaijr.model.enums.ProductType;
import pkmhaijr.service.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
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

    private User user1;

    private List<Product> products;
    private List<User> users;

    private void initProducts() {
        products = Arrays.asList(
                new Product(new BigDecimal("14.99"), "title1", ProductType.CD, "desc1", Genre.ALTERNATIVE),
                new Product(new BigDecimal("12.99"), "title2", ProductType.CD, "desc2", Genre.ALTERNATIVE),
                new Product(new BigDecimal("16.99"), "title3", ProductType.CD, "desc3", Genre.BLUES),
                new Product(new BigDecimal("8.99"), "title4", ProductType.CD, "desc4", Genre.CLASSICAL),
                new Product(new BigDecimal("13.99"), "title5", ProductType.CD, "desc5", Genre.CLASSICAL),
                new Product(new BigDecimal("23.99"), "title6", ProductType.VINYL, "desc6", Genre.COUNTRY),
                new Product(new BigDecimal("9.99"), "title7", ProductType.CD, "desc7", Genre.DUBSTEP),
                new Product(new BigDecimal("15.99"), "title8", ProductType.CD, "desc8", Genre.ELECTRONIC),
                new Product(new BigDecimal("13.99"), "title9", ProductType.CD, "desc9", Genre.ELECTRONIC),
                new Product(new BigDecimal("17.99"), "title10", ProductType.CD, "desc1", Genre.ELECTRONIC),
                new Product(new BigDecimal("18.99"), "title11", ProductType.VINYL, "desc11", Genre.ELECTRONIC),
                new Product(new BigDecimal("14.99"), "title12", ProductType.CD, "desc12", Genre.FOLK),
                new Product(new BigDecimal("25.99"), "title13", ProductType.VINYL, "desc13", Genre.HOUSE),
                new Product(new BigDecimal("23.99"), "title14", ProductType.VINYL, "desc14", Genre.JAZZ),
                new Product(new BigDecimal("19.99"), "title15", ProductType.VINYL, "desc15", Genre.ELECTRONIC),
                new Product(new BigDecimal("11.99"), "title16", ProductType.CD, "desc16", Genre.PUNK),
                new Product(new BigDecimal("18.99"), "title17", ProductType.VINYL, "desc17", Genre.ROCK)
        );
        Mockito.when(productService.findAllProducts()).thenReturn(products);
    }

    private void initUsers() {
        //TODO: change this method when constructors are updated
        user1 = new User();
        user1.setAddresses(Collections.emptySet());
        user1.setCards(Collections.emptySet());
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setOrderHistory(products);
        user1.setId(1);
        users = Collections.singletonList(user1);
        Mockito.when(userService.findAllUsers()).thenReturn(users);
        Mockito.when(userService.findUserByd(Matchers.eq(1))).thenReturn(user1);
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        initProducts();
        initUsers();
    }


    @Test
    public void getAllProductsTest() {
        //preparation
        List<Product> productsFromDb = products;

        //action
        List<Product> productsReturned = databaseFacade.getAllProducts();

        //assertion
        Assertions.assertThat(productsFromDb).hasSameSizeAs(productsReturned);
        Assertions.assertThat(productsFromDb).hasSameElementsAs(productsReturned);
    }

    @Test
    public void getUsersOrderHistoryTest() {
        //preparation
        List<Product> orderHistoryFromDb = user1.getOrderHistory();

        //action
        List<Product> orderHistory = databaseFacade.getUsersOrderHistory(1);

        //assertion
        Assertions.assertThat(orderHistoryFromDb).hasSameSizeAs(orderHistory);
        Assertions.assertThat(orderHistoryFromDb).hasSameElementsAs(orderHistory);
    }
}
