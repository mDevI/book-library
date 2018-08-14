package com.mdevi.booklib;

import com.mdevi.booklib.dao.repository.AuthorRepository;
import com.mdevi.booklib.model.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest(properties = {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "= false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@DirtiesContext
@Transactional
@Rollback(true)
public class AuthorDaoTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFindOne() {
        Optional<Author> author = authorRepository.findById(1);
        assertTrue(author.isPresent());
    }

    @Test
    public void testFindCount() {
        long count = authorRepository.count();
        assertTrue(count > 0);

    }

    @Test
    public void testInsert() {
        Author testAuthor = new Author();
        testAuthor.setName("Test Test");
        testAuthor = authorRepository.save(testAuthor);
        assertTrue(testAuthor.getId() > 0);
    }

    @Test
    public void testFindAll() {
        assertTrue(authorRepository.findAll().size() > 0);
    }

    @After
    public void tearDown() throws Exception {
    }
}
