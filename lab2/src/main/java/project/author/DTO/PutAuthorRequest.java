package project.author.DTO;

import lombok.*;
import project.author.entity.Author;

import java.util.function.BiFunction;

/**
 * A DTO for the {@link Author} entity
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutAuthorRequest {

    private String name;

    private int yearOfBirth;

    private String country;

    public static BiFunction<Author, PutAuthorRequest, Author> dtoToEntityUpdater() {
        return (author, request) -> {
            author.setName(request.getName());
            author.setYearOfBirth(request.getYearOfBirth());
            author.setCountry(request.getCountry());
            return author;
        };
    }
}