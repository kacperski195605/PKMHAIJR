package mvp;

import pkmhaijr.App.User;
import pkmhaijr.managers.DatabaseFacade;
import pkmhaijr.model.ErrorType;

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
}
