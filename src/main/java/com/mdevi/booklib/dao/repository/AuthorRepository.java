package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorRepository implements AuthorDAO {

    private static final String SELECT_ALL_FROM_AUTHORS = "select * from new_schema.authors order by name";
    private static final String SELECT_FROM_AUTHORS_BY_ID = "select * from new_schema.authors where id=?";
    private static final String SELECT_COUNT_ALL_AUTHORS = "select count(*) from new_schema.authors";
    private static final String INSERT_AUTHOR = "insert into new_schema.authors (name, dob, rank) values(?, ?, ?)";
    private static final String UPDATE_AUTHOR = "update new_schema.authors set name = ?, dob = ?, rank = ? where id = ?";
    private static final String DELETE_AUTHOR_BY_ID = "delete from new_schema.authors where id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_AUTHORS,new AuthorRowMapper()) ;
    }

    @Override
    public Author findById(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_FROM_AUTHORS_BY_ID, new Object[]{id},new AuthorRowMapper());
    }

    @Override
    public Integer insert(Author author) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR, new String[] {"id"});
                    ps.setString(1, author.getName());
                    ps.setDate(2, author.getDateOfBirth());
                    ps.setInt(3, author.getRank());
                    return ps;
                },
                keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR, author.getName(), author.getDateOfBirth(), author.getRank(), author.getId());
    }

    @Override
    public void delete(Author author) {
        if (author != null && author.getId() != 0) {
        jdbcTemplate.update(DELETE_AUTHOR_BY_ID, author.getId());
        }
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE_AUTHOR_BY_ID, id);
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
            author.setDateOfBirth( resultSet.getDate("dob"));
            author.setRank(resultSet.getInt("rank"));
            return author;
        }
    }
}
