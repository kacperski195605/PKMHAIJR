package pkmhaijr.manager;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
@Log4j2
public class DatabaseFacade {

    @Autowired
    private  ProductService productService;
    @Autowired
    private  AddressService addressService;
    @Autowired
    private  AuthorService authorService;
    @Autowired
    private  CreditCardService creditCardService;
    @Autowired
    private  UserService userService;
    @Autowired
    private  WishlistService wishlistService;

    public User getUser(int id){
        return userService.findUserByd(id);
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
