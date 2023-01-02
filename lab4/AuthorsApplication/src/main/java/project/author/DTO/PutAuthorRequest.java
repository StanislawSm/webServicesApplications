package project.author.DTO;

import lombok.*;
import project.author.entity.Author;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutAuthorRequest {

    private int yearOfBirth;
    private String country;

    public static BiFunction<Author, PutAuthorRequest, Author> dtoToEntityUpdater() {
        return (author, request) -> {
            author.setCountry(request.getCountry());
            author.setYearOfBirth(request.getYearOfBirth());
            return author;
        };
    }

}