package ssmyk.example.lab1.book.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ssmyk.example.lab1.book.entity.Book;
import ssmyk.example.lab1.dataStore.DataStore;
import ssmyk.example.lab1.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for character entity. Repositories should be used in business layer (e.g.: in services).
 */
@org.springframework.stereotype.Repository
public class BookRepository implements Repository<Book, Long> {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
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


    /*public Optional<Character> findByIdAndUser(Long id, User user) {
        return store.findAllCharacters().stream()
                .filter(character -> character.getUser().equals(user))
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public List<Character> findAllByUser(User user) {
        return store.findAllCharacters().stream()
                .filter(character -> character.getUser().equals(user))
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }*/

}

