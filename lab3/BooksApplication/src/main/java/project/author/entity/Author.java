package project.author.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import project.book.entity.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode
@Entity
@Table(name = "authors")

public class Author implements Serializable {

    @Id
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Book> books;
}
