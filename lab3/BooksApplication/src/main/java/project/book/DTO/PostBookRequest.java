package project.book.DTO;

import project.author.entity.Author;
import project.book.entity.Book;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

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
    private String title;
    private int yearOfPublication;
    private String author;

    public static Function<PostBookRequest, Book> dtoToEntityMapper(Supplier<Author> authorSupplier) {
        return request -> Book.builder()
                .title(request.getTitle())
                .yearOfPublication(request.getYearOfPublication())
                .author(authorSupplier.get())
                .build();
    }
}