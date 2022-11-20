package project.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return repository.findById(name);
    }

    @Transactional
    public Author create(Author author){
         return repository.save(author);
    }

    @Transactional
    public void delete(Author author) {
        repository.delete(author);
    }
}

