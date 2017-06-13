package pkmhaijr.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pkmhaijr.model.dbEntities.Author;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by margo on 5/15/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Log4j2
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    private Author author1;
    private Author author2;
    private Author author3;

    @Before
    public void setUp() {
        author1 = new Author();
        author1.setName("Name1");
        author1.setDescription("Description1");

        author2 = new Author();
        author2.setName("Name2");
        author2.setDescription("Description2");

        author3 = new Author();
        author3.setName("Name3");
        author3.setDescription("Description3");
    }

    private void addAllAuthors() {
        authorService.addAuthor(author1);
        authorService.addAuthor(author2);
        authorService.addAuthor(author3);
    }

    @Test
    public void findAuthorByIdTest() {
        log.info("Testing finding an author by id");
        //preparation
        author1 = authorService.addAuthor(author1);

        //action
        Author newAuthor = authorService.findAuthorById(author1.getId());

        //assertion
        Assertions.assertThat(newAuthor).isNotNull();
        Assertions.assertThat(newAuthor).isEqualTo(author1);
    }

    @Test
    public void countOfEmptyDatabaseTest() {
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = authorService.countAuthors();

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Test
    public void countOfNotEmptyDatabaseTest() {
        log.info("Testing count of not empty database");
        //preparation
        addAllAuthors();
        int expectedCount = 3;

        //action
        int actualCount = authorService.countAuthors();

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
    }

    @Test
    public void findAllAuthorsTest() {
        log.info("Testing finding all authors");
        //preparation
        addAllAuthors();
        int expectedCount = 3;

        //action
        List<Author> authors = authorService.findAllAuthors();

        //assertion
        Assertions.assertThat(authors).isNotNull();
        Assertions.assertThat(expectedCount).isEqualTo(authors.size());
        Assertions.assertThat(authors).contains(author1, author2, author3);
    }

    @Test
    public void deleteAuthorTest() {
        log.info("Testing deleting author");
        //preparation
        author1 = authorService.addAuthor(author1);
        int expectedCount = 0;

        //action
        authorService.deleteAuthor(author1);
        int actualCount = authorService.countAuthors();
        Author newAuthor = authorService.findAuthorById(author1.getId());

        //assertion
        Assertions.assertThat(expectedCount).isEqualTo(actualCount);
        Assertions.assertThat(newAuthor).isNull();
    }

    @Test
    public void validUpdateAuthor() {
        log.info("Testing valid updating author");
        //preparation
        author1 = authorService.addAuthor(author1);
        author1.setName("New name");

        //action
        authorService.updateAuthor(author1);
        Author newAuthor = authorService.findAuthorById(author1.getId());

        //assertion
        Assertions.assertThat(newAuthor).isNotNull();
        Assertions.assertThat(author1.getName()).isEqualTo(newAuthor.getName());
    }

    @Test
    public void invalidUpdateAuthor() {
        log.info("Testing invalid updating author");
        //preparation
        author1 = authorService.addAuthor(author1);
        authorService.deleteAuthor(author1);

        //assertion
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> authorService.updateAuthor(author1));
    }

    @After
    public void clean() {
        authorService.deleteAllAuthors();
    }
}
