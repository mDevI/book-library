package com.mdevi.booklib.dao.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

//    private static final String SELECT_ALL_FROM_BOOKS = "select * from new_schema.books order by title";
//
//    private static final String SELECT_ALL_WITH_DETAILS = "select b.book_id, b.title, g.name as genre, b.pages," +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            " left join new_schema.genres g on g.id = b.genre";
//
//    private static final String SELECT_ONE_BY_TITLE = "select b.book_id, b.title, g.name as genre, b.pages, " +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            " left join new_schema.genres g on g.id = b.genre where b.title = :pattern";
//
//    private static final String SELECT_BOOKS_BY_TITLE_LIKE = "select b.book_id, b.title, g.name as genre, b.pages, " +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            " left join new_schema.genres g on g.id = b.genre where b.title LIKE :pattern";
//
//    private static final String SELECT_BOOK_TITLE_BY_ID = "select title from new_schema.books where book_id = :id";
//
//    private static final String INSERT_BOOK_WITH_DETAILS = "insert into new_schema.books (title, pages, count, author, genre ) " +
//            "values (:title,:pages,:count,:author,:genre)";
//
//    private static final String UPDATE_BOOK_WITH_DETAILS = "update new_schema.books set title = :newtitle, pages = :newpages, " +
//            "count = :newcount, author = :newauthor, genre = :newgenre where book_id = :id";
//
//    private static final String SELECT_BOOKS_BY_AUTHOR_LIKE = "select b.book_id, b.title, g.name as genre, b.pages, " +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            "left join new_schema.genres g on g.id = b.genre where author_name LIKE :pattern";
//
//    private static final String SELECT_BOOKS_BY_AUTHOR = "select b.book_id, b.title, g.name as genre, b.pages, " +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            "left join new_schema.genres g on g.id = b.genre where author_name = :pattern";
//
//    private static final String DELETE_FROM_BOOKS_BY_ID = "delete from new_schema.books where book_id = :id";
//    private static final String SELECT_BOOK_BY_ID = "select b.book_id, b.title, g.name as genre, b.pages, " +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            " left join new_schema.genres g on g.id = b.genre where b.book_id = :id";
//    private static final String SELECT_BOOK_BY_GENRE_ID = "select b.book_id, b.title, g.name as genre, b.pages, " +
//            "b.count, a.id author_id, a.name author_name, a.dob author_dob, a.rank, g.id genre_id " +
//            "from new_schema.books b left join new_schema.authors a on b.author = a.id " +
//            " left join new_schema.genres g on g.id = b.genre where g.id = :genre_id";
//
//
//    private final NamedParameterJdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookRepository(NamedParameterJdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    @Override
//    public List<Book> findAll() {
//        return jdbcTemplate.query(SELECT_ALL_FROM_BOOKS, new BookRowMapper());
//    }
//
//    @Override
//    public Book findById(Integer id) {
//        Map<String, Integer> params = new HashMap<>(1);
//        params.put("id", id);
//        Book theBook = jdbcTemplate.query(SELECT_BOOK_BY_ID, params, new BookWithDetailsExtractor()).get(0);
//        return theBook;
//    }
//
//    @Override
//    public List<Book> findByTitleLike(String title) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("pattern", "%" + title + "%");
//        return jdbcTemplate.query(SELECT_BOOKS_BY_TITLE_LIKE, params, new BookWithDetailsExtractor() );
//    }
//
//    @Override
//    public Book findByTitle(String title) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("pattern", title);
//        return jdbcTemplate.query(SELECT_ONE_BY_TITLE, params, new BookWithDetailsExtractor()).get(0);
//    }
//
//    @Override
//    public List<Book> findByAuthorLike(String authorName) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("pattern", "%" + authorName + "%");
//        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR_LIKE, params, new BookWithDetailsExtractor());
//    }
//
//    @Override
//    public List<Book> findByGenreId(Integer genreId) {
//        Map<String, Object> params = new HashMap<>(1);
//        params.put("genre_id", genreId);
//        return jdbcTemplate.query(SELECT_BOOK_BY_GENRE_ID, params, new BookWithDetailsExtractor());
//    }
//
//    @Override
//    public Book findByAuthor(String authorName) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("pattern", authorName);
//        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR, params, new BookWithDetailsExtractor()).get(0);
//    }
//
//    @Override
//    public List<Book> findByAuthor(Author author) {
//        return null;
//    }
//
//    @Override
//    public String findTitleById(Integer id) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", id);
//        return jdbcTemplate.queryForObject(SELECT_BOOK_TITLE_BY_ID, params, String.class);
//    }
//
//    @Override
//    public void insert(Book book) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        if(book.getAuthor().getId()>0 && book.getGenre().getId()>0) {
//            Map<String, Object> params = new HashMap<>();
//            //params.put("id", book.getId());
//            params.put("title", book.getBookTitle());
//            params.put("pages", book.getPages());
//            params.put("count", book.getQuantity());
//            params.put("author", book.getAuthor().getId());
//            params.put("genre", book.getGenre().getId());
//            jdbcTemplate.update(INSERT_BOOK_WITH_DETAILS, new MapSqlParameterSource(params), keyHolder, new String[]{"book_id"});
//
///*            if (keyHolder.getKeys().size() > 1) {
//                System.out.println(keyHolder.getKeys().get("book_id"));
//            }*/
//        }
//    }
//
//    @Override
//    public void update(Book book) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("newtitle", book.getBookTitle());
//        params.put("newpages", book.getPages());
//        params.put("newcount", book.getQuantity());
//        params.put("newauthor", book.getAuthor().getId());
//        params.put("newgenre", book.getGenre().getId());
//        params.put("id", book.getId());
//        jdbcTemplate.update(UPDATE_BOOK_WITH_DETAILS, params);
//    }
//
//    @Override
//    public void delete(Integer id) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", id);
//        jdbcTemplate.update(DELETE_FROM_BOOKS_BY_ID, params);
//    }
//
//    @Override
//    public List<Book> findAllWithDetails() {
//        return jdbcTemplate.query(SELECT_ALL_WITH_DETAILS, new BookWithDetailsExtractor());
//    }
//
//    @Override
//    public void insertWithDetails(Book book) {
//
//    }
//
//
//    private static final class BookWithDetailsExtractor implements ResultSetExtractor<List<Book>> {
//
//        @Override
//        public List<Book> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
//            Map<Integer, Book> mapBook = new HashMap<>(1);
//            Book theBook;
//            while ((resultSet.next())) {
//                Integer bookId = resultSet.getInt("book_id");
//                theBook = mapBook.get(bookId);
//                if (theBook == null) {
//                    theBook = new Book();
//                    theBook.setId(bookId);
//                    theBook.setBookTitle(resultSet.getString("title"));
//                    theBook.setPages(resultSet.getInt("pages"));
//                    theBook.setQuantity(resultSet.getInt("count"));
//                    mapBook.put(bookId, theBook);
//                }
//                Integer authorId = resultSet.getInt("author_id");
//                if (authorId > 0) {
//                    Author author = new Author();
//                    author.setId(authorId);
//                    author.setName(resultSet.getString("author_name"));
//                    author.setDateOfBirth(resultSet.getDate("author_dob"));
//                    author.setRank(resultSet.getInt("rank"));
//                    theBook.setAuthor(author);
//                }
//                Integer genreId = resultSet.getInt("genre_id");
//                if (genreId > 0) {
//                    Genre genre = new Genre();
//                    genre.setId(genreId);
//                    genre.setTitle(resultSet.getString("genre"));
//                    theBook.setGenre(genre);
//                }
//            }
//            return new ArrayList<>(mapBook.values());
//        }
//    }
//
//    private class BookRowMapper implements RowMapper<Book> {
//
//        @Override
//        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//            Book theBook = new Book();
//            theBook.setId(resultSet.getInt("id"));
//            theBook.setBookTitle(resultSet.getString("title"));
//            theBook.setPages(resultSet.getInt("pages"));
//            theBook.setQuantity(resultSet.getInt("count"));
//            return theBook;
//        }
//    }
}
