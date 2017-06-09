package pkmhaijr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pkmhaijr.manager.DatabaseFacade;

/**
 * Created by Asasello on 09-Jun-17.
 */
@Configuration
public class dbc {
    @Bean
    public DatabaseFacade databaseFacade(){
        return new DatabaseFacade();
    }
}
