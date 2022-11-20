package project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.author.entity.Author;
import project.author.service.AuthorService;

import javax.annotation.PostConstruct;


@Component
public class InitializedData {

    private final AuthorService authorService;

    @Autowired
    public InitializedData(AuthorService authorService) {
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
    }
}

