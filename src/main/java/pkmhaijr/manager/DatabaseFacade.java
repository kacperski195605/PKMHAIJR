package pkmhaijr.manager;


import org.springframework.beans.factory.annotation.Autowired;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.service.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asasello on 19-Apr-17.
 */
public class DatabaseFacade {

    @Autowired
    private ProductService productService;

    private DatabaseFacade() {
    }

    public static DatabaseFacade getInstance(){return new DatabaseFacade();}

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
