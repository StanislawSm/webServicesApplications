package project.author.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.author.entity.Author;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    Optional<Author> findByName (String name);
}

