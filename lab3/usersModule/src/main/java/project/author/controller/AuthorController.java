package project.author.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import project.author.DTO.GetAuthorResponse;
import project.author.DTO.GetAuthorsResponse;
import project.author.DTO.PostAuthorRequest;
import project.author.DTO.PutAuthorRequest;
import project.author.entity.Author;
import project.author.service.AuthorService;
import project.book.DTO.GetBooksByAuthorResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetAuthorsResponse> getAuthorsResponse() {
        List<Author> all = authorService.findAll();
        Function<Collection<Author>, GetAuthorsResponse> mapper = GetAuthorsResponse.entityToDtoMapper();
        GetAuthorsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}/books")
    public ResponseEntity<GetBooksByAuthorResponse> getBooksByAuthorResponse(@PathVariable("name") String name) {
        return  authorService.find(name)
                .map(value -> ResponseEntity.ok(GetBooksByAuthorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{name}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable("name") String name) {
        return authorService.find(name)
                .map(value -> ResponseEntity.ok(GetAuthorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createAuthor(@RequestBody PostAuthorRequest request, UriComponentsBuilder builder) {
        Author author = PostAuthorRequest.dtoToEntityMapper().apply(request);
        author = authorService.create(author);
        return ResponseEntity.created(builder.pathSegment("api", "authors", "{name}")
                .buildAndExpand(author.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteBook(@PathVariable("name") String name) {
        Optional<Author> author = authorService.find(name);
        if (author.isPresent()) {
            authorService.delete(author.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateAuthor(@RequestBody PutAuthorRequest request, @PathVariable("name") String name) {
        Optional<Author> author = authorService.find(name);
        if (author.isPresent()) {
            PutAuthorRequest.dtoToEntityUpdater().apply(author.get(), request);
            authorService.update(author.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
