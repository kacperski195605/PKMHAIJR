package pkmhaijr;

/**
 * Created by Asasello on 22-Apr-17.
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pkmhaijr.mvp.AppContract;
import pkmhaijr.mvp.AppPresenter;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.ErrorType;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.util.ProductsSortingUtils;

import java.util.ArrayList;

/**
 * Store instance
 */

@Component
@Log4j2
public class App implements AppContract.View {

    @Autowired
    private AppContract.Presenter mPresenter;

    private User currentUser;
    private SearchContext searchContext;

    public App() {
        init();
    }

    private void init() {
        searchContext = new SearchContext.Builder("").build();
    }

    public void updateSearchContext(SearchContext searchContext) {
        this.searchContext = searchContext;
    }

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void showProducts(ArrayList<Product> productsList) {
        //TODO: do something with products list :) ASK HOW TO TEST IT (WE DO NOT SAVE PRODUCT LIST HERE)
        ProductsSortingUtils.getSortedList(productsList, searchContext.getSortingType());
    }

    @Override
    public void error(ErrorType errorType) {
        //TODO: add logic to error
        log.error(errorType);
    }

}
