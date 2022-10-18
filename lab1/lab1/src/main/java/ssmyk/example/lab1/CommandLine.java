package ssmyk.example.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ssmyk.example.lab1.author.entity.Author;
import ssmyk.example.lab1.author.service.AuthorService;
import ssmyk.example.lab1.book.entity.Book;
import ssmyk.example.lab1.book.service.BookService;

import java.util.List;
import java.util.Scanner;

/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public CommandLine(BookService bookService, AuthorService authorService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    private void printAvailableCommands() {
        System.out.println();
        System.out.println("Choose command:");
        System.out.println("All authors");
        System.out.println("All books");
        System.out.println("All entities");
        System.out.println("Add a book");
        System.out.println("Remove a book");
        System.out.println("Quit");
    }

    private void createBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Creating new book");
        System.out.print("Isbn: ");
        Long isbn = Long.parseLong(scanner.nextLine());

        System.out.print("Year of publication: ");
        int publicationYear = Integer.parseInt(scanner.nextLine());

        System.out.println("Title: ");
        String title = scanner.nextLine();

        System.out.println("Available authors:");
        printAllAuthors();
        System.out.println("Choose from existing or create new one");
        System.out.print("Author: ");
        String authorName = scanner.nextLine();

        Author author = authorService.find(authorName)
                .orElseGet(() -> createAuthor(authorName));

        Book newBook = Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .yearOfPublication(publicationYear)
                .build();

        bookService.create(newBook);
        System.out.printf("Successfully added: %s\n", newBook);
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose isbn of book you want to delete");
        printAllBooksByIsbn();
        System.out.print("Isbn: ");
        Long isbn = Long.parseLong(scanner.nextLine());

        bookService.find(isbn).ifPresentOrElse(
                book -> {
                    bookService.delete(book);
                    System.out.printf("Successfully deleted %s\n", book);
                },
                () -> System.out.printf("Cannot found the book with isbn: %d\n", isbn)
        );
    }

    private Author createAuthor(String authorName) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new author");
        System.out.printf("Name: %s\n", authorName);
        System.out.print("Year of birth: ");
        int birthYear = Integer.parseInt(scanner.nextLine());
        System.out.print("Country: ");
        String country = scanner.nextLine();

        Author newAuthor = Author.builder()
                .name(authorName)
                .yearOfBirth(birthYear)
                .country(country)
                .build();

        authorService.create(newAuthor);
        System.out.printf("Successfully created: %s\n", newAuthor);
        return newAuthor;
    }

    private void printAllAuthors() {
        authorService.findAll().forEach(System.out::println);
    }

    private void printAllBooks() {
        bookService.findAll().forEach(System.out::println);
    }

    private void printAllEntities() {
        printAllAuthors();
        printAllBooks();
    }

    private void printAllBooksByIsbn() {
        List<Book> books = bookService.findAll();
        books.sort((Book b1, Book b2) -> (int) (b1.getIsbn() - b2.getIsbn()));
        books.forEach(System.out::println);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        printAvailableCommands();

        while(true) {
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("Quit")) {
                break;
            } else if (command.equalsIgnoreCase("All authors")) {
                printAllAuthors();
            } else if (command.equalsIgnoreCase("All books")) {
                printAllBooks();
            } else if (command.equalsIgnoreCase("Add a book")) {
                createBook();
            } else if (command.equalsIgnoreCase("All entities")) {
                printAllEntities();
            } else if (command.equalsIgnoreCase("Remove a book")) {
                deleteBook();
            } else {
                System.out.println("Unknown command.");
                printAvailableCommands();
            }
        }
    }
}

