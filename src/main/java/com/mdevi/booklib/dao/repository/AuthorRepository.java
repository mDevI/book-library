package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.model.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AuthorRepository implements AuthorDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author findById(Integer id) {
        return em.find(Author.class, id);
    }

    @Override
    public Integer insert(Author author) {
        em.persist(author);
        em.flush();
        return author.getId();
    }

    @Override
    public void update(Author author) {
        em.merge(author);
    }

    @Override
    public void delete(Author author) {
        em.remove(author);
    }

    @Override
    public void deleteById(Integer id) {
        Author theAuthor = this.findById(id);
        if (theAuthor != null) {
            em.remove(theAuthor);
        }
    }

    @Override
    public int count() {
        return em.createQuery("select a from Author a", Author.class).getResultList().size();
    }


//    private static final String SELECT_ALL_FROM_AUTHORS = "select * from new_schema.authors order by name";
//    private static final String SELECT_FROM_AUTHORS_BY_ID = "select * from new_schema.authors where id=?";
//    private static final String SELECT_COUNT_ALL_AUTHORS = "select count(*) from new_schema.authors";
//    private static final String INSERT_AUTHOR = "insert into new_schema.authors (name, dob, rank) values(?, ?, ?)";
//    private static final String UPDATE_AUTHOR = "update new_schema.authors set name = ?, dob = ?, rank = ? where id = ?";
//    private static final String DELETE_AUTHOR_BY_ID = "delete from new_schema.authors where id = ?";
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AuthorRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<Author> findAll() {
//        return jdbcTemplate.query(SELECT_ALL_FROM_AUTHORS,new AuthorRowMapper()) ;
//    }
//
//    @Override
//    public Author findById(Integer id) {
//        return jdbcTemplate.queryForObject(SELECT_FROM_AUTHORS_BY_ID, new Object[]{id},new AuthorRowMapper());
//    }
//
//    @Override
//    public Integer insert(Author author) {
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(
//                connection -> {
//                    PreparedStatement ps = connection.prepareStatement(INSERT_AUTHOR, new String[] {"id"});
//                    ps.setString(1, author.getName());
//                    ps.setDate(2, author.getDateOfBirth());
//                    ps.setInt(3, author.getRank());
//                    return ps;
//                },
//                keyHolder);
//        return (Integer) keyHolder.getKey();
//    }
//
//    @Override
//    public void update(Author author) {
//        jdbcTemplate.update(UPDATE_AUTHOR, author.getName(), author.getDateOfBirth(), author.getRank(), author.getId());
//    }
//
//    @Override
//    public void delete(Author author) {
//        if (author != null && author.getId() != 0) {
//        jdbcTemplate.update(DELETE_AUTHOR_BY_ID, author.getId());
//        }
//    }
//
//    @Override
//    public void deleteById(Integer id) {
//        jdbcTemplate.update(DELETE_AUTHOR_BY_ID, id);
//    }
//
//    @Override
//    public int count() {
//        return jdbcTemplate.queryForObject(SELECT_COUNT_ALL_AUTHORS, Integer.class);
//    }
//
//    private class AuthorRowMapper implements RowMapper<Author> {
//
//        @Override
//        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
//            Author author = new Author();
//            author.setId(resultSet.getInt("id"));
//            author.setName(resultSet.getString("name"));
//            author.setDateOfBirth( resultSet.getDate("dob"));
//            author.setRank(resultSet.getInt("rank"));
//            return author;
//        }
//    }
}
