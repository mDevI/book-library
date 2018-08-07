package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.model.Author;
import com.mdevi.booklib.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepository implements BookDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book findById(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> findByTitleLike(String title) {
        TypedQuery<Book> queryByTitleLike = em.createQuery("select b from Book b where b.bookTitle like :titlePattern", Book.class)
                .setParameter("titlePattern", "%" + title + "%");
        return queryByTitleLike.getResultList();
    }

    @Override
    public Book findByTitle(String title) {
        TypedQuery<Book> queryByTitle = em.createQuery("select b from Book b where b.bookTitle = :title", Book.class)
                .setParameter("title", title);
        return queryByTitle.getSingleResult();
    }

    @Override
    public Book findByAuthor(String author) {
        TypedQuery<Book> queryByAuthor = em.createQuery("select b from Book b  where b.author.name = :authorName", Book.class)
                .setParameter("authorName", author);
        return queryByAuthor.getSingleResult();
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        TypedQuery<Book> queryByAuthor = em.createQuery("select b from Book b where b.author.name = :authorName", Book.class)
                .setParameter("authorName", author.getName());
        return queryByAuthor.getResultList();
    }

    @Override
    public List<Book> findByAuthorLike(String authorName) {
        TypedQuery<Book> queryByAuthorLike = em.createQuery("select b from Book b  where b.author.name LIKE :authorName", Book.class)
                .setParameter("authorName", "%" + authorName + "%");
        return queryByAuthorLike.getResultList();
    }

    @Override
    public List<Book> findByGenreId(Integer genreId) {
        TypedQuery<Book> queryByGenreId = em.createQuery("select b from Book b where b.genre.id = :genreId", Book.class)
                .setParameter("genreId", genreId);
        return queryByGenreId.getResultList();
    }

    @Override
    public String findTitleById(Integer id) {
        Book theBook = this.findById(id);
        if (theBook != null) {
            return theBook.getBookTitle();
        } else {
            return null;
        }
    }

    @Override
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public void update(Book book) {
        em.persist(book);
    }

    @Override
    public void delete(Integer id) {
        Book theBook = this.findById(id);
        if (theBook != null) {
            em.remove(theBook);
        }
    }

    @Override
    public List<Book> findAllWithDetails() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void insertWithDetails(Book book) {
        em.persist(book);
    }

    @Override
    public List<Book> findBooksByGenre(String genre) {
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.genre.title = :title", Book.class)
                .setParameter("title", genre);
        return query.getResultList();
    }
}
