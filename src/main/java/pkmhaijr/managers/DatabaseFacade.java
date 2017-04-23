package pkmhaijr.managers;


import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.model.enums.FilterType;
import pkmhaijr.model.enums.Genre;

import java.util.ArrayList;

/**
 * Created by Asasello on 19-Apr-17.
 */
public class DatabaseFacade {

    private DatabaseFacade(){}

    public static DatabaseFacade getInstance(){return new DatabaseFacade();}

    public User getUser(){
        //TODO: logic for get user
        return new User();
    }

    public ArrayList<Product> getProducts(SearchContext searchContext) {
        //TODO: Logic for filter products
        return new ArrayList<Product>(){{
            add(new Product());
            add(new Product());
            add(new Product());
            add(new Product());
        }};
    }
}
