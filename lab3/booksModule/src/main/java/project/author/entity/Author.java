package project.author.entity;

import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import project.book.entity.Book;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    private String country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Author author = (Author) o;
        return name != null && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
