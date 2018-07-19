package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Genre;
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
public class GenreRepository implements GenreDAO {

    private static final String SELECT_ALL_FROM_GENRES = "select * from new_schema.genres order by name";
    private static final String SELECT_FROM_GENRES_BY_ID = "select * from new_schema.genres where id=?";
    private static final String SELECT_COUNT_ALL_GENRES = "select count(*) from new_schema.genres";
    private static final String INSERT_GENRE = "insert into new_schema.genres (name) values(?)";
    private static final String UPDATE_GENRE = "update new_schema.genres set name = ? where id = ?";
    private static final String DELETE_GENRE_BY_ID = "delete from new_schema.genres where id = ?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(SELECT_ALL_FROM_GENRES, new GenreRowMapper());
    }

    @Override
    public Genre findById(Integer id) {
        return jdbcTemplate.queryForObject(SELECT_FROM_GENRES_BY_ID, new Object[]{id}, new GenreRowMapper());
    }

    @Override
    public Integer insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(INSERT_GENRE, new String[]{"id"});
                    ps.setString(1, genre.getTitle());
                    return ps;
                },
                keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public void update(Genre genre) {
        jdbcTemplate.update(UPDATE_GENRE, genre.getTitle(), genre.getId());
    }

    @Override
    public void delete(Genre genre) {
        if (genre != null && genre.getId() > 0) {
            jdbcTemplate.update(DELETE_GENRE_BY_ID, genre.getId());
        }
    }

    @Override
    public void deleteById(Integer id) {
        jdbcTemplate.update(DELETE_GENRE_BY_ID, id);
    }

    @Override
    public Integer count() {
        return jdbcTemplate.queryForObject(SELECT_COUNT_ALL_GENRES, Integer.class);
    }

    private class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Genre genre = new Genre();
            genre.setId(resultSet.getInt("id"));
            genre.setTitle(resultSet.getString("name"));
            return genre;
        }
    }
}
