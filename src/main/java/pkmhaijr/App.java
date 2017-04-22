package pkmhaijr;

/**
 * Created by Asasello on 22-Apr-17.
 */

import mvp.AppContract;
import mvp.AppPresenter;
import pkmhaijr.model.ErrorType;

import java.util.logging.Logger;

/**
 * Store instance
 */
public class App implements AppContract.View {
    private AppContract.Presenter mPresenter;
    private User currentUser;

    //TODO: to remove
    public static class User {
    }

    private App() {init();}

    public static App newInstance() {
        return new App();
    }

    private void init() {mPresenter = AppPresenter.newInstance(this);}

    @Override
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void error(ErrorType errorType) {
        //TODO: add logic to error
        System.err.println(errorType);
    }


}
