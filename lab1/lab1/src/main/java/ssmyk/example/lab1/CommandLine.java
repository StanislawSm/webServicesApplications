package ssmyk.example.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ssmyk.example.lab1.book.service.BookService;

/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    /**
     * Service for managing characters. Should be injected automatically.
     */
    private BookService bookService;

    /**
     * @param characterService service for managing characters
     */
    @Autowired
    public CommandLine(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        bookService.findAll().forEach(System.out::println);
    }

}

