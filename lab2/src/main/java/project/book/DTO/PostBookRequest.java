package project.book.DTO;

import lombok.*;
import project.author.entity.Author;
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
public class PostBookRequest {
    private Long isbn;
    private String title;
    private String author;

    public static Function<PostBookRequest, Book> dtoToEntityMapper(Function<String, Author> authorFunction) {
        return request -> Book.builder()
                .author(authorFunction.apply(request.getAuthor()))
                .title(request.getTitle())
                .build();
    }
}