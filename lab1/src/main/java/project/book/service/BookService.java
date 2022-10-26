package project.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return repository.find(isbn);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public void create(Book book) {
        repository.create(book);
    }

    public void update(Book book) {
        repository.update(book);
    }

    public void delete(Book book) {
        repository.delete(repository.find(book.getIsbn()).orElseThrow());
    }

}

