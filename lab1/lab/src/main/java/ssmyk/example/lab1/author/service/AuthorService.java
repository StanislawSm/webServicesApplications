package ssmyk.example.lab1.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssmyk.example.lab1.author.entity.Author;
import ssmyk.example.lab1.author.repository.AuthorRepository;
import ssmyk.example.lab1.book.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Optional<Author> find(String name) {
        return repository.find(name);
    }

    public List<Author> findAll() {
        return repository.findAll();
    }

    public void create(Author author) {
        repository.create(author);
    }

    public void update(Author author) {
        repository.update(author);
    }

    public void delete(Author author) {
        repository.delete(repository.find(author.getName()).orElseThrow());
    }
}

