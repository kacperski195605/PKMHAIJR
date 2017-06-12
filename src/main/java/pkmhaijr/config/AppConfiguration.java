package pkmhaijr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pkmhaijr.App;
import pkmhaijr.mvp.AppPresenter;

/**
 * Created by patry on 12/06/17.
 */

@Configuration
public class AppConfiguration {

    @Bean
    public App app() {
        return new App();
    }

    @Bean
    public AppPresenter appPresenter() {
        return new AppPresenter(app());
    }
}
