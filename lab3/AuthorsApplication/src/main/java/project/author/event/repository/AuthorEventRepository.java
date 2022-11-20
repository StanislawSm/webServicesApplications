package project.author.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import project.author.entity.Author;
import project.author.event.DTO.PostAuthorRequest;

@Repository
public class AuthorEventRepository {

    private RestTemplate restTemplate;

    @Autowired
    public AuthorEventRepository(@Value("${lab.books.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Author author) {
        restTemplate.delete("/authors/{name}", author.getName());
    }

    public void create(Author author) {
        restTemplate.postForLocation("/authors", PostAuthorRequest.entityToDtoMapper().apply(author));
    }
}
