package pkmhaijr.mvp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pkmhaijr.manager.DatabaseFacade;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.enums.ErrorType;
import pkmhaijr.model.dbEntities.User;

/**
 * Created by Asasello on 22-Apr-17.
 */

@Component
public class AppPresenter implements AppContract.Presenter {

    @Autowired
    private DatabaseFacade databaseFacade;

    private AppContract.View view;

    public AppPresenter(AppContract.View view) {
        this.view = view;
    }

    @Override
    public void getCurrentUser() {
        User temp = databaseFacade.getUser(1);
        if (temp == null) {
            view.error(ErrorType.USER_NOT_FOUND);
        }
        else {
            view.setCurrentUser(temp);
        }
    }

    @Override
    public void getProducts(SearchContext searchContext) {
         view.showProducts(databaseFacade.getProducts(searchContext));
    }
}
