package pkmhaijr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pkmhaijr.manager.DatabaseFacade;
import pkmhaijr.service.ProductService;

/**
 * Created by patry on 10/06/17.
 */

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DatabaseFacade databaseFacade() {
        return new DatabaseFacade();
    }
}
