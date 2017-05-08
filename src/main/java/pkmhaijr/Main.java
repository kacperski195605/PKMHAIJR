package pkmhaijr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Asasello on 08-Mar-17.
 */

@SpringBootApplication
@ComponentScan({"pkmhaijr"})
@EntityScan("pkmhaijr")
@EnableJpaRepositories("pkmhaijr")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}
