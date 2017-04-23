package mvp;


import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.dbEntities.Product;
import pkmhaijr.model.dbEntities.User;
import pkmhaijr.model.enums.ErrorType;
import pkmhaijr.model.enums.FilterType;
import pkmhaijr.model.enums.Genre;

import java.util.ArrayList;

/**
 * Created by Asasello on 22-Apr-17.
 */
public interface AppContract {
    public interface View{
        void setCurrentUser(User user);
        void error(ErrorType errorType);
        void showProducts(ArrayList<Product> productsList);
    }

    public interface Presenter{
        void getCurrentUser();
        void getProducts(SearchContext searchContext);
    }
}
