package project.book.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import project.author.entity.Author;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long isbn;

    private String title;

    @Column(name = "year_of_publication")
    private int yearOfPublication;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name ="author")
    private Author author;
}
