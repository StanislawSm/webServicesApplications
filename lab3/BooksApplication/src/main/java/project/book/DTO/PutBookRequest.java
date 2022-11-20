package project.book.DTO;

import lombok.*;
import project.book.entity.Book;

import java.util.function.BiFunction;

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
public class PutBookRequest {

    private String title;

    private int yearOfPublication;

    public static BiFunction<Book, PutBookRequest, Book> dtoToEntityUpdater() {
        return (book, request) -> {
            book.setTitle(request.getTitle());
            book.setYearOfPublication(request.getYearOfPublication());
            return book;
        };
    }
}