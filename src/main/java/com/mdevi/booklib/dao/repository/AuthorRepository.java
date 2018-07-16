package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class AuthorRepository implements AuthorDAO {

    public static final String SELECT_ALL_FROM_AUTHORS = "select * from new_schema.authors order by name";
    public static final String SELECT_FROM_AUTHORS_BY_ID = "select * from new_schema.authors where id=?";
    public static final String SELECT_COUNT_ALL_AUTHORS = "select count(*) from new_schema.authors";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_AUTHORS,new AuthorRowMapper()) ;
    }

    @Transactional(readOnly = true)
    @Override
    public Author findById(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_FROM_AUTHORS_BY_ID, new Object[]{id},new AuthorRowMapper());
    }

    @Override
    public void insert(Author author) {

    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject(SELECT_COUNT_ALL_AUTHORS, Integer.class);
    }

    private class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = new Author();
            author.setId(resultSet.getInt("id"));
            author.setName(resultSet.getString("name"));
            // author.setDateOfBirth(resultSet.getInt("id"));
            author.setRank(resultSet.getInt("rank"));
            return author;
        }
    }
}
