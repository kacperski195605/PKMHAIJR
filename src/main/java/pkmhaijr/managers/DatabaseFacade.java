package pkmhaijr.managers;

import pkmhaijr.App.User;

/**
 * Created by Asasello on 19-Apr-17.
 */
public class DatabaseFacade {

    private DatabaseFacade(){}

    public static DatabaseFacade getInstance(){return new DatabaseFacade();}

    public User getUser(){
        //TODO: logic for get user
        return new User();
    }
}
