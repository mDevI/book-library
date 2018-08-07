package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.ReaderDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.BookBorrow;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ReaderRepository implements ReaderDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Reader> findAll() {
        return em.createQuery("select r from Reader r", Reader.class).getResultList();
    }

    @Override
    public Optional<Reader> findByName(String name) {
        TypedQuery<Reader> queryReader = em.createQuery("select r from Reader r where r.name = :name", Reader.class)
                .setParameter("name", name);
        List<Reader> results = queryReader.getResultList();
        if (results.isEmpty()) {
            return Optional.empty(); // handle no-results case
        } else {
            return Optional.ofNullable(results.get(0));
        }
    }

    @Override
    public Reader findById(Integer id) {
        return em.find(Reader.class, id);
    }

    @Override
    public List<Reader> findByNameLike(String name) {
        TypedQuery<Reader> query = em.createQuery("select r from Reader r where r.name LIKE :name", Reader.class)
                .setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public Integer insert(Reader reader) {
        em.persist(reader);
        em.flush();
        return reader.getId();
    }

    @Override
    public void delete(Reader reader) {
        em.remove(reader);
    }

    @Override
    public void update(Reader reader) {
        em.persist(reader);
    }

    @Override
    public void borrowTheBook(Book book, Reader reader, Date dateFrom, Date dateTill) {
        int availBookCount = book.getQuantity();
        BookBorrow bookBorrow = new BookBorrow(book, reader, dateFrom, dateTill);
        book.setQuantity(availBookCount - 1);
        try {
            em.persist(bookBorrow);
            em.persist(book);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public List<Book> findAllBooksBorrowedByReader(Reader reader) {
        List<BookBorrow> bookBorrows = reader.getBorrows();
        List<Book> books = new ArrayList<>();
        bookBorrows.forEach(bookBorrow -> books.add(bookBorrow.getBook()));
        return books;
    }

    @Override
    public BookBorrow returnTheBook(Book book, Reader reader) {
        TypedQuery<BookBorrow> queryToReturnBook =
                em.createQuery("select b from BookBorrow b where b.reader.id = :readerId and" +
                        " b.book.id = :bookId and b.dateReturn = null", BookBorrow.class)
                        .setParameter("bookId", book.getId())
                        .setParameter("readerId", reader.getId());
        BookBorrow bookBorrowToClose = queryToReturnBook.getSingleResult();
        bookBorrowToClose.setDateReturn(Date.valueOf(LocalDate.now()));
        int bookCountAfter = book.getQuantity();
        book.setQuantity(++bookCountAfter);
        em.persist(book);
        em.persist(bookBorrowToClose);
        return bookBorrowToClose;
    }
}
