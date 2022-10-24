package ssmyk.example.lab1.author.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)

public class Author implements Serializable {

    private String name;
    private int yearOfBirth;
    private String country;
}
