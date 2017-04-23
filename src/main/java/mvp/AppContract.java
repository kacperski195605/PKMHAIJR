package mvp;

import pkmhaijr.model.enums.ErrorType;
import pkmhaijr.model.dbEntities.User;

/**
 * Created by Asasello on 22-Apr-17.
 */
public interface AppContract {
    public interface View{
        void setCurrentUser(User user);
        void error(ErrorType errorType);
    }

    public interface Presenter{
        void getCurrentUser();
    }
}
