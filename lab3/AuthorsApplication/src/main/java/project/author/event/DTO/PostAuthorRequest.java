package project.author.event.DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import project.author.entity.Author;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode

public class PostAuthorRequest {

    private String name;

    public static Function<Author, PostAuthorRequest> entityToDtoMapper() {
        return entity -> PostAuthorRequest.builder()
                .name(entity.getName())
                .build();
    }
}
