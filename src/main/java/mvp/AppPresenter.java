package mvp;

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


}
