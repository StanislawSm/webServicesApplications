package project.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.book.entity.Book;
import project.book.repository.BookRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<Book> find(Long isbn) {
        return repository.findById(isbn);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Book create(Book book) {
        return repository.save(book);
    }

    @Transactional
    public void update(Book book) {
        repository.save(book);
    }

    @Transactional
    public void delete(Long book) {
        repository.deleteById(book);
    }

}

