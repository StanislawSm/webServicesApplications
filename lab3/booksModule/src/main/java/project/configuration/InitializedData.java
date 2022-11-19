package project.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.author.entity.Author;
import project.author.service.AuthorService;
import project.book.entity.Book;
import project.book.service.BookService;

import javax.annotation.PostConstruct;
import java.io.InputStream;


/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Component
public class InitializedData {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public InitializedData(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @PostConstruct
    private synchronized void init() {
        Author author1 = Author.builder()
                .name("John Smith")
                .yearOfBirth(1983)
                .country("USA")
                .build();

        Author author2 = Author.builder()
                .name("Jan Kowalski")
                .yearOfBirth(1963)
                .country("Poland")
                .build();

        Author author3 = Author.builder()
                .name("William John")
                .yearOfBirth(1985)
                .country("United Kingdom")
                .build();

        authorService.create(author1);
        authorService.create(author2);
        authorService.create(author3);

        Book book1 = Book.builder()
                .isbn(54684654L)
                .title("Something")
                .yearOfPublication(2002)
                .author(author1)
                .build();

        Book book2 = Book.builder()
                .isbn(2135174865L)
                .title("Pomorze")
                .yearOfPublication(1998)
                .author(author2)
                .build();

        Book book3 = Book.builder()
                .isbn(98518687L)
                .title("Best things")
                .yearOfPublication(2010)
                .author(author1)
                .build();

        Book book4 = Book.builder()
                .isbn(9842342323L)
                .title("Worst things")
                .author(author3)
                .yearOfPublication(2010)
                .build();

        Book book5 = Book.builder()
                .isbn(234234233L)
                .title("Kujawy")
                .yearOfPublication(2010)
                .author(author2)
                .build();

        bookService.create(book1);
        bookService.create(book2);
        bookService.create(book3);
        bookService.create(book4);
        bookService.create(book5);
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            assert is != null;
            return is.readAllBytes();
        } catch (NullPointerException exception){
            System.out.println(exception.getMessage());
            return null;
        }
    }

}

