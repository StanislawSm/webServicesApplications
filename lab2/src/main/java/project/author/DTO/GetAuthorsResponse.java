package project.author.DTO;

import lombok.*;
import project.author.entity.Author;

import java.util.Collection;
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
public class GetAuthorsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Author {

        private String name;

        private String country;
    }

    @Singular
    private List<Author> authors;

    public static Function<Collection<project.author.entity.Author>, GetAuthorsResponse> entityToDtoMapper() {
        return authors -> {
            GetAuthorsResponseBuilder response = GetAuthorsResponse.builder();
            authors.stream()
                    .map(author -> Author.builder()
                            .name(author.getName())
                            .country(author.getCountry())
                            .build())
                    .forEach(response::author);
            return response.build();
        };
    }
}