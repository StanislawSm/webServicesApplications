package project.book.repository;

import org.springframework.beans.factory.annotation.Autowired;
import project.book.entity.Book;
import project.dataStore.DataStore;
import project.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class BookRepository implements Repository<Book, Long> {

    private DataStore store;

    @Autowired
    public BookRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Book> find(Long isbn) {
        return store.findBook(isbn);
    }

    @Override
    public List<Book> findAll() {
        return store.findAllBooks();
    }

    @Override
    public void create(Book entity) {
        store.createBook(entity);
    }

    @Override
    public void delete(Book entity) {
        store.deleteBook(entity.getIsbn());
    }

    @Override
    public void update(Book entity) {
        store.updateBook(entity);
    }
}

