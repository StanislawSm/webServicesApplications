package ssmyk.example.lab1.author.repository;


import org.springframework.beans.factory.annotation.Autowired;
import ssmyk.example.lab1.author.entity.Author;
import ssmyk.example.lab1.dataStore.DataStore;
import ssmyk.example.lab1.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class AuthorRepository implements Repository<Author, String> {

    private DataStore store;

    @Autowired
    public AuthorRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Author> find(String name) {
        return store.findAuthor(name);
    }

    @Override
    public List<Author> findAll() {
        return store.findAllAuthors();
    }

    @Override
    public void create(Author entity) {
        store.createAuthor(entity);
    }

    @Override
    public void delete(Author entity) {
        store.deleteAuthor(entity.getName());
    }

    @Override
    public void update(Author entity) {
        store.updateAuthor(entity);
    }

}

