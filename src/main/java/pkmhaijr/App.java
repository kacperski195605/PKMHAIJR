package pkmhaijr;

/**
 * Created by Asasello on 22-Apr-17.
 */

import mvp.AppContract;
import mvp.AppPresenter;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.enums.ErrorType;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.util.ProductsSortingUtils;

import java.util.ArrayList;

/**
 * Store instance
 */
public class App implements AppContract.View {
    private AppContract.Presenter mPresenter;
    private User currentUser;
    private SearchContext searchContext;

    private App() {
        init();
    }

    public static App newInstance() {
        return new App();
    }

    private void init() {
        mPresenter = AppPresenter.newInstance(this);
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
        System.err.println(errorType);
    }

}
