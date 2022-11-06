package project.book.DTO;

import lombok.*;
import project.author.entity.Author;

import java.util.List;
import java.util.function.Function;

/**
 * A DTO for the {@link project.book.DTO.GetBooksResponse.Book} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBooksByAuthorResponse {
    @Singular
    List<GetBooksResponse.Book> books;

    public static Function<Author, GetBooksByAuthorResponse> entityToDtoMapper() {
        return author -> {
            GetBooksByAuthorResponseBuilder response = GetBooksByAuthorResponse.builder();
            author.getBooks().stream()
                    .map(book -> GetBooksResponse.Book.builder()
                            .isbn(book.getIsbn())
                            .title(book.getTitle())
                            .build())
                    .forEach(response::book);
            return response.build();
        };
    }
}