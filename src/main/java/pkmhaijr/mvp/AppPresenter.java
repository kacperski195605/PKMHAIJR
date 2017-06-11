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

    private AppPresenter(AppContract.View view) {
        this.view = view;
    }

    public static AppPresenter newInstance(AppContract.View view) {
        return new AppPresenter(view);
    }


    @Override
    public void getCurrentUser() {
        User temp = databaseFacade.getUser();
        if (temp == null) view.error(ErrorType.USER_NOT_FOUND);
        else view.setCurrentUser(temp);
    }

    @Override
    public void getProducts(SearchContext searchContext) {
         view.showProducts(databaseFacade.getProducts(searchContext));
    }
}
