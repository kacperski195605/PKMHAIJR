package pkmhaijr.manager;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.service.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asasello on 19-Apr-17.
 */
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DatabaseFacade {

    private final ProductService productService;
    private final AddressService addressService;
    private final AuthorService authorService;
    private final CreditCardService creditCardService;
    private final UserService userService;
    private final WishlistService wishlistService;

    public User getUser(){
        //TODO: logic for get user
        return new User();
    }

    //Field SortingType in SearchContext should be ignored in this method
    public ArrayList<Product> getProducts(SearchContext searchContext) {
        //TODO: Logic for filter products
        return new ArrayList<Product>(){{
            add(new Product());
            add(new Product());
            add(new Product());
            add(new Product());
        }};
    }

    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }
}
