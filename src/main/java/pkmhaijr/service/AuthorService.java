package pkmhaijr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.dbEntities.Author;
import pkmhaijr.repository.AuthorRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by patry on 23/04/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        log.info("Adding author: " + author);
        return authorRepository.save(author);
    }

    public Author findAuthorById(Integer id) {
        log.info("Searching for author with id: " + id);
        return authorRepository.findOne(id);
    }

    public List<Author> findAllAuthors() {
        log.info("Getting all authors");
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteAuthor(Author author) {
        log.info("Deleting author: " + author);
        authorRepository.delete(author);
    }

    public Author updateAuthor(Author newAuthor) {
        log.info("Updating author: " + newAuthor);
        Author oldAuthor = findAuthorById(newAuthor.getId());
        if (oldAuthor != null) {
            return authorRepository.save(newAuthor);
        } else {
            throw new IllegalArgumentException("There is no author with id " + newAuthor.getId() + " in database");
        }
    }

    public void deleteAllAuthors() {
        log.info("Deleting all authors");
        authorRepository.deleteAll();
    }

    public Integer countAuthors() {
        log.info("Returning number of authors");
        return Math.toIntExact(authorRepository.count());
    }
}
