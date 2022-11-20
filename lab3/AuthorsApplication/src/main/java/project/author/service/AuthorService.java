package project.author.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.author.entity.Author;
import project.author.event.repository.AuthorEventRepository;
import project.author.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    private AuthorEventRepository eventRepository;

    @Autowired
    public AuthorService(AuthorRepository repository, AuthorEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Author> find(String name) {
        return repository.findById(name);
    }

    @Transactional
    public void create(Author author) {
        repository.save(author);
        eventRepository.create(author);
    }
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Author author) {
        eventRepository.delete(author);
        repository.delete(author);
    }
}

