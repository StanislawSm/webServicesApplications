package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application main class.
 */
@SpringBootApplication
public class AuthorsApplication {

    /**
     * Application main entry point. Starts Spring Boot application context.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthorsApplication.class, args);
    }

}

