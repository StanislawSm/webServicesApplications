package ssmyk.example.lab1.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssmyk.example.lab1.author.entity.Author;
import ssmyk.example.lab1.author.repository.AuthorRepository;

import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Optional<Author> find(String name) {
        return repository.find(name);
    }

    public void create(Author author) {
        repository.create(author);
    }

}

