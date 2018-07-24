package com.mdevi.booklib;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "= false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})

public class BookLibApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private GenreDAO genreDAO;


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void shouldGetCountOfAuthorsGreaterThenZero() {
        assertEquals(5L, authorDAO.count());
    }

    @Test
    public void shouldGetAllAuthorsNotNull() {
        assertTrue(authorDAO.findAll().size() > 0);
    }

    @Test
    public void shouldSaveReadAndDeleteAuthor() {
        Author theAuthor = new Author();
        theAuthor.setId(0);
        theAuthor.setName("John Reed");
        theAuthor.setRank(0);
        theAuthor.setDateOfBirth(Date.valueOf("1900-11-11"));
        Integer newRecordId = authorDAO.insert(theAuthor);
        assertNotNull(newRecordId);
        assertNotNull(authorDAO.findById(newRecordId));
        assertTrue(authorDAO.count() == 6);
        authorDAO.deleteById(newRecordId);
        assertEquals(5L, authorDAO.count());
    }

    @Test
    public void shouldGetGenresCountGreaterThanZero() {
        assertEquals(29, genreDAO.findAll().size());
    }

    @Test
    public void shouldGetGenreByIDAndName() {
        assertNotNull(genreDAO.findById(20));
        assertNotNull(genreDAO.findByTitle("Fantasy"));
    }

    @After
    public void tearDown() throws Exception {

    }
}
