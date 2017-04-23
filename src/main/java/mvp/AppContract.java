package mvp;

import pkmhaijr.App;
import pkmhaijr.model.ErrorType;
import pkmhaijr.model.FilterType;

import java.util.ArrayList;

/**
 * Created by Asasello on 22-Apr-17.
 */
public interface AppContract {
    public interface View{
        void setCurrentUser(App.User user);
        void error(ErrorType errorType);

    }

    public interface Presenter{
        void getCurrentUser();
        void getProducts(FilterType type);
    }
}
