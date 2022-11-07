package project.book.DTO;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * A DTO for the {@link Book} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBooksResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Book {
        private Long isbn;

        private String title;
    }

    @Singular
    private List<Book> books;

    public static Function<Collection<project.book.entity.Book>, GetBooksResponse> entityToDtoMapper() {
        return books -> {
            GetBooksResponseBuilder response = GetBooksResponse.builder();
            books.stream()
                    .map(book -> Book.builder()
                            .isbn(book.getIsbn())
                            .title(book.getTitle())
                            .build())
                    .forEach(response::book);
            return response.build();
        };
    }
}