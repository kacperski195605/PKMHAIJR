package pkmhaijr;

/**
 * Created by Asasello on 22-Apr-17.
 */

import mvp.AppContract;
import mvp.AppPresenter;

/**
 * Store instance
 */
public class App implements AppContract.View{

    private AppContract.Presenter mPresenter;

    private App() {
        init();
    }

    public static App newInstance() {
        return new App();
    }

    private void init() {
        mPresenter = AppPresenter.newInstance(this);
    }


}
