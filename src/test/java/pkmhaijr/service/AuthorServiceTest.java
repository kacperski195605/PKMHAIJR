package pkmhaijr.service;

import lombok.extern.log4j.Log4j2;
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

    @Autowired AuthorService authorService;

    private Author author1;
    private Author author2;
    private Author author3;

    @Before
    public void setUp() {
        author1 = new Author();
        author1.setName("Name1");
        author1.setId(1);
        author1.setDescription("Description1");

        author2 = new Author();
        author2.setName("Name2");
        author2.setId(2);
        author2.setDescription("Description2");

        author3 = new Author();
        author3.setName("Name3");
        author3.setId(3);
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
        assertNotNull("Author should not be null", newAuthor);
        assertEquals("Authors should be equal", author1, newAuthor);
    }

    @Test
    public void countOfEmptyDatabaseTest() {
        log.info("Testing count of empty database");
        //preparation
        int expectedCount = 0;

        //action
        int actualCount = authorService.countAuthors();

        //assertion
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
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
        assertEquals("Count should be equal to 3", expectedCount, actualCount);
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
        assertNotNull("List should not be null", authors);
        assertEquals("List should have a length of 3", expectedCount, authors.size());
        //TODO: change these 3 into something nicer
        assertTrue("List should contain author 1", authors.contains(author1));
        assertTrue("List should contain author 2", authors.contains(author2));
        assertTrue("List should contain author 3", authors.contains(author3));
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
        assertEquals("Count should be equal to 0", expectedCount, actualCount);
        assertNull("newAuthor should be null", newAuthor);
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
        assertNotNull("New author should not be null", newAuthor);
        assertEquals("New author should have updated name field", author1.getName(), newAuthor.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidUpdateAuthor() {
        log.info("Testing invalid updating author");
        //preparation
        author1 = authorService.addAuthor(author1);
        authorService.deleteAuthor(author1);

        //action
        authorService.updateAuthor(author1);
    }

    @After
    public void clean() {
        authorService.deleteAllAuthors();
    }
}
