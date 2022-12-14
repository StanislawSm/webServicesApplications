package project.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.author.entity.Author;
import project.book.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbnAndAuthor(Long isbn, Author author);
    List<Book> findAllByAuthor (Author author);
}

