package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.model.Author;
import com.mdevi.booklib.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository implements BookDAO {

    private static final String SELECT_ALL_FROM_BOOKS = "select * from new_schema.books order by title";
    private static final String SELECT_ALL_WITH_DETAILS = "select b.book_id, b.title, b.pages, b.count, a.id, a.name," +
            " a.dob, a.rank, from new_schema.books b join authors a on b.author = a.id";

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_BOOKS, new BookRowMapper());
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return null;
    }

    @Override
    public String findTitleById(Integer id) {
        return null;
    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Book> findAllWithDetails() {
        return jdbcTemplate.query(SELECT_ALL_WITH_DETAILS, new BookWithDetailsExtractor());
    }

    @Override
    public void insertWithDetails(Book book) {

    }

    private static final class BookWithDetailsExtractor implements ResultSetExtractor<List<Book>> {

        @Override
        public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Map<Integer, Book> mapBook = new HashMap<>(1);
            Book theBook;
            while ((resultSet.next())) {
                Integer id = resultSet.getInt("book_id");
                theBook = mapBook.get(id);
                if (theBook == null) {
                    theBook = new Book();
                    theBook.setId(id);
                    theBook.setBookTitle(resultSet.getString("title"));
                    theBook.setPages(resultSet.getInt("pages"));
                    theBook.setQuantity(resultSet.getInt("count"));
                    theBook.setAuthor(new Author());
                    mapBook.put(id, theBook);
                }
                Integer authorId = resultSet.getInt("id");
                if (authorId > 0) {
                    Author author = new Author();
                    author.setId(authorId);
                    author.setName(resultSet.getString("name"));
                    author.setRank(resultSet.getInt("rank"));
                    theBook.setAuthor(author);
                }
            }
            return new ArrayList<>(mapBook.values());
        }
    }

    private class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Book theBook = new Book();
            theBook.setId(resultSet.getInt("id"));
            theBook.setBookTitle(resultSet.getString("title"));
            theBook.setPages(resultSet.getInt("pages"));
            theBook.setQuantity(resultSet.getInt("count"));

            return theBook;
        }
    }
}
