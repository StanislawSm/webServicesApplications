package project.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.author.service.AuthorService;
import project.book.DTO.GetBookResponse;
import project.book.DTO.GetBooksResponse;
import project.book.DTO.PostBookRequest;
import project.book.DTO.PutBookRequest;
import project.book.entity.Book;
import project.book.service.BookService;

import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<GetBooksResponse> getBooks() {
        return ResponseEntity
                .ok(GetBooksResponse.entityToDtoMapper().apply(bookService.findAll()));
    }

    @GetMapping("{isbn}")
    public ResponseEntity<GetBookResponse> getBook(@PathVariable("isbn") long isbn) {
        Optional<Book> book = bookService.find(isbn);
        return book.map(value -> ResponseEntity.ok(GetBookResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postBook(@RequestBody PostBookRequest request, UriComponentsBuilder builder) {
        Book book = PostBookRequest
                .dtoToEntityMapper(() -> null)
                .apply(request);

        book = bookService.create(book);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "books", "{isbn}")
                        .buildAndExpand(book.getIsbn()).toUri())
                .build();
    }

    @DeleteMapping("{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable("isbn") long isbn) {
        Optional<Book> book = bookService.find(isbn);
        if (book.isPresent()) {
            bookService.delete(book.get().getIsbn());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @PutMapping("{isbn}")
    public ResponseEntity<Void> putCharacter(@RequestBody PutBookRequest request, @PathVariable("isbn") long isbn) {
        Optional<Book> book = bookService.find(isbn);
        if (book.isPresent()) {
            PutBookRequest.dtoToEntityUpdater().apply(book.get(), request);
            bookService.update(book.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
}