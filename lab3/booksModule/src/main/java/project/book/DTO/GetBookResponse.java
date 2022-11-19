package project.book.DTO;

import project.book.entity.Book;

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
public class GetBookResponse {
    private Long isbn;
    private String title;
    private String author;
    private int yearOfPublication;

    public static Function<Book, GetBookResponse> entityToDtoMapper() {
        return book -> GetBookResponse.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor().getName())
                .yearOfPublication(book.getYearOfPublication())
                .build();
    }
}