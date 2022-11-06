package project.author.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.annotations.Fetch;
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

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    private String country;

    @OneToMany
    @ToString.Exclude
    private List<Book> books;
}
