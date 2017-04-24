package mvp;

import pkmhaijr.manager.DatabaseFacade;
import pkmhaijr.model.app.SearchContext;
import pkmhaijr.model.enums.ErrorType;
import pkmhaijr.model.dbEntities.User;

/**
 * Created by Asasello on 22-Apr-17.
 */
public class AppPresenter implements AppContract.Presenter {

    private AppContract.View view;

    private AppPresenter(AppContract.View view) {
        this.view = view;
    }

    public static AppPresenter newInstance(AppContract.View view) {
        return new AppPresenter(view);
    }


    @Override
    public void getCurrentUser() {
        User temp = DatabaseFacade.getInstance().getUser();
        if (temp == null) view.error(ErrorType.USER_NOT_FOUND);
        else view.setCurrentUser(temp);
    }

    @Override
    public void getProducts(SearchContext searchContext) {
         view.showProducts(DatabaseFacade.getInstance().getProducts(searchContext));
    }
}
