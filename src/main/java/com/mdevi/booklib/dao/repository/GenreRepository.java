package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository implements GenreDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Optional<Genre> findByTitle(String title) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.title = :name", Genre.class)
                .setParameter("name", title);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Integer insert(Genre genre) {
        em.persist(genre);
        em.flush();
        return genre.getId();
    }

    @Override
    public void update(Genre genre) {
        em.merge(genre);
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Genre> theGenre = this.findById(id);
        if (theGenre.isPresent()) em.remove(theGenre);
    }

    @Override
    public Integer count() {
        Query query = em.createQuery("select count(g) from Genre g");
        return query.getFirstResult();
    }


//    private static final String SELECT_ALL_FROM_GENRES = "select * from new_schema.genres order by name";
//    private static final String SELECT_FROM_GENRES_BY_ID = "select * from new_schema.genres where id=?";
//    private static final String SELECT_FROM_GENRE_BY_TITLE = "select * from new_schema.genres where name=?";
//    private static final String SELECT_COUNT_ALL_GENRES = "select count(*) from new_schema.genres";
//    private static final String INSERT_GENRE = "insert into new_schema.genres (name) values(?)";
//    private static final String UPDATE_GENRE = "update new_schema.genres set name = ? where id = ?";
//    private static final String DELETE_GENRE_BY_ID = "delete from new_schema.genres where id = ?";
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public GenreRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Genre> findAll() {
//        return jdbcTemplate.query(SELECT_ALL_FROM_GENRES, new GenreRowMapper());
//    }
//
//    @Override
//    public Optional<Genre> findById(Integer id) {
//        List<Genre> genreList = jdbcTemplate.query(SELECT_FROM_GENRES_BY_ID, new Object[]{id}, new GenreRowMapper());
//        return genreList.isEmpty() ? Optional.empty() : Optional.ofNullable(genreList.get(0));
//    }
//
//    @Override
//    public Genre findByTitle(String title) {
//        List<Genre> genreList = jdbcTemplate.query(SELECT_FROM_GENRE_BY_TITLE, new Object[]{title}, new GenreRowMapper());
//        return genreList.isEmpty() ? null : genreList.get(0);
//    }
//
//    @Override
//    public Integer insert(Genre genre) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(
//                connection -> {
//                    PreparedStatement ps = connection.prepareStatement(INSERT_GENRE, new String[]{"id"});
//                    ps.setString(1, genre.getTitle());
//                    return ps;
//                },
//                keyHolder);
//        return (Integer) keyHolder.getKey();
//    }
//
//    @Override
//    public void update(Genre genre) {
//        jdbcTemplate.update(UPDATE_GENRE, genre.getTitle(), genre.getId());
//    }
//
//    @Override
//    public void delete(Genre genre) {
//        if (genre != null && genre.getId() > 0) {
//            jdbcTemplate.update(DELETE_GENRE_BY_ID, genre.getId());
//        }
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        jdbcTemplate.update(DELETE_GENRE_BY_ID, id);
//    }
//
//    @Override
//    public Integer count() {
//        return jdbcTemplate.queryForObject(SELECT_COUNT_ALL_GENRES, Integer.class);
//    }
//
//    private class GenreRowMapper implements RowMapper<Genre> {
//
//        @Override
//        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
//            Genre genre = new Genre();
//            genre.setId(resultSet.getInt("id"));
//            genre.setTitle(resultSet.getString("name"));
//            return genre;
//        }
//    }
}
