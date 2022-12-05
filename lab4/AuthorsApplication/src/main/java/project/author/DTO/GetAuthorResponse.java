package project.author.DTO;

import lombok.*;
import project.author.entity.Author;

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
public class GetAuthorResponse {

    private String name;

    private int yearOfBirth;

    private String country;

    public static Function<Author, GetAuthorResponse> entityToDtoMapper() {
        return author -> GetAuthorResponse.builder()
                .name(author.getName())
                .yearOfBirth(author.getYearOfBirth())
                .country(author.getCountry())
                .build();
    }
}