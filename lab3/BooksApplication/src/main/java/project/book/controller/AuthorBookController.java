package project.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.author.entity.Author;
import project.author.service.AuthorService;
import project.book.DTO.GetBookResponse;
import project.book.DTO.GetBooksResponse;
import project.book.DTO.PostBookRequest;
import project.book.DTO.PutBookRequest;
import project.book.entity.Book;
import project.book.service.BookService;

import java.util.Optional;

@RestController
@RequestMapping("api/authors/{authorname}/books")
public class AuthorBookController {

    private final BookService bookService;

    private final AuthorService authorService;

    @Autowired
    public AuthorBookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks(@PathVariable("authorname") String authorName) {
        Optional<Author> author = authorService.find(authorName);
        return author.map(value -> ResponseEntity.ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{isbn}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("authorname") String authorname,
                                                   @PathVariable("isbn") long isbn) {
        return bookService.find(authorname, isbn)
                .map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postBook(@PathVariable("authorname") String authorname,
                                              @RequestBody PostBookRequest request,
                                              UriComponentsBuilder builder) {
        Optional<Author> author = authorService.find(authorname);
        if (author.isPresent()) {
            Book book = PostBookRequest
                    .dtoToEntityMapper(author::get)
                    .apply(request);
            book = bookService.create(book);
            return ResponseEntity.created(builder.pathSegment("api", "books", "{authorname}", "books", "{isbn}")
                    .buildAndExpand(author.get().getName(), book.getIsbn()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("authorname") String authorname,
                                                @PathVariable("isbn") long isbn) {
        Optional<Book> book = bookService.find(authorname, isbn);
        if (book.isPresent()) {
            bookService.delete(book.get().getIsbn());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Void> putBook(@PathVariable("authorname") String authorname,
                                             @RequestBody PutBookRequest request,
                                             @PathVariable("isbn") long isbn) {
        Optional<Book> book = bookService.find(authorname, isbn);
        if (book.isPresent()) {
            PutBookRequest.dtoToEntityUpdater().apply(book.get(), request);
            bookService.update(book.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
