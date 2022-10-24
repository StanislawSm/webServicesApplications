package ssmyk.example.lab1.dataStore;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ssmyk.example.lab1.serialization.CloningUtility;
import ssmyk.example.lab1.author.entity.Author;
import ssmyk.example.lab1.book.entity.Book;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {

    private final Set<Book> books = new HashSet<>();
    private final Set<Author> authors = new HashSet<>();

    public synchronized List<Book> findAllBooks() {
        return new ArrayList<>(books);
    }

    public synchronized Optional<Book> findBook(Long isbn) {
        return books.stream()
                .filter(book -> Objects.equals(book.getIsbn(), isbn))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createBook(Book book) throws IllegalArgumentException {
        findBook(book.getIsbn()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The book isbn \"%d\" is not unique", book.getIsbn()));
                },
                () -> books.add(CloningUtility.clone(book)));
    }

    public synchronized void updateBook(Book book) throws IllegalArgumentException {
        findBook(book.getIsbn()).ifPresentOrElse(
                original -> {
                    books.remove(original);
                    books.add(CloningUtility.clone(book));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Isbn \"%d\" does not exist", book.getIsbn()));
                });
    }

    public synchronized void deleteBook(Long isbn) throws IllegalArgumentException {
        findBook(isbn).ifPresentOrElse(
                original -> books.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The book with isbn: \"%d\" does not exist", isbn));
                });
    }

    public synchronized List<Author> findAllAuthors() {
        return authors.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Author> findAuthor(String name) {
        return authors.stream()
                .filter(author -> author.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createAuthor(Author author) throws IllegalArgumentException {
        findAuthor(author.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("\"%s\" already exist in the database", author.getName()));
                },
                () -> authors.add(CloningUtility.clone(author)));
    }

    public synchronized void updateAuthor(Author author) throws IllegalArgumentException {
        findAuthor(author.getName()).ifPresentOrElse(
                original -> {
                    authors.remove(original);
                    authors.add(CloningUtility.clone(author));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The author \"%s\" does not exist", author.getName()));
                });
    }

    public synchronized void deleteAuthor(String name) throws IllegalArgumentException {
        findAuthor(name).ifPresentOrElse(
                original -> authors.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The author \"%s\" does not exist", name));
                });
    }
}
