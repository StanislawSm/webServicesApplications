package project.author.DTO;

import lombok.*;
import project.author.entity.Author;

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

    private int yearOfBirth;

    private String country;

}