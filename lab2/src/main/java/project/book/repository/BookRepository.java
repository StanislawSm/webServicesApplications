package project.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.author.entity.Author;
import project.book.entity.Book;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn (Long isbn);
    List<Book> findAllByAuthor (Author author);
}

