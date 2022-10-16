package ssmyk.example.lab1.book.entity;


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
public class Book implements Serializable {
    private Long isbn;
    private String title;
    private int yearOfPublication;
}
