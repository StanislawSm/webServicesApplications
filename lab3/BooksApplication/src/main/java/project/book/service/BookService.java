package project.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.author.entity.Author;
import project.author.repository.AuthorRepository;
import project.book.entity.Book;
import project.book.repository.BookRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Book> find(Long isbn) {
        return bookRepository.findById(isbn);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAll(Author author) {
        return bookRepository.findAllByAuthor(author);
    }

    @Transactional
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(Long isbn) {
        bookRepository.deleteById(isbn);
    }

}

