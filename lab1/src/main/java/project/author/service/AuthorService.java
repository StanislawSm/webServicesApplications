package project.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.author.entity.Author;
import project.author.repository.AuthorRepository;

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

