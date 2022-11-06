package project.author.DTO;

import lombok.*;
import project.author.entity.Author;

import java.util.List;
import java.util.function.Function;

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
public class PostAuthorRequest {

    private String name;

    private int yearOfBirth;

    private String country;

    public static Function<PostAuthorRequest, Author> dtoToEntityMapper() {
        return request -> Author.builder()
                .name(request.getName())
                .yearOfBirth(request.getYearOfBirth())
                .country(request.getCountry())
                .books(List.of())
                .build();
    }
}