package pkmhaijr.manager;


import org.springframework.context.annotation.Bean;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;

import java.util.ArrayList;

/**
 * Created by Asasello on 19-Apr-17.
 */
public class DatabaseFacade {

    public DatabaseFacade(){}

//    public static DatabaseFacade getInstance(){return new DatabaseFacade();}

    public User getUser(){
        //TODO: logic for get user
        return new User();
    }

    //Field SortingType in SearchContext should be ignored in this method
    public ArrayList<Product> getProducts(SearchContext searchContext) {
        //TODO: Logic for filter products
//        return new ArrayList<Product>(){{
//            add(new Product());
//            add(new Product());
//            add(new Product());
//            add(new Product());
//        }};
        return null;
    }
}
