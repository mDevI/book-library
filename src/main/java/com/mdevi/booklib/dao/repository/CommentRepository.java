package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.CommentDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.BookBorrow;
import com.mdevi.booklib.model.Comment;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class CommentRepository implements CommentDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(BookBorrow bookBorrow, Comment comment) {
        em.persist(comment);
        em.flush();
        bookBorrow.setComment(comment);
        em.merge(bookBorrow);
    }

    @Override
    public List<Comment> findAllCommentsByBook(Book book) {
        TypedQuery<Comment> queryComment =
                em.createQuery("select c from Comment c, BookBorrow b where c = b.comment and  b.book.id = :bookId", Comment.class)
                        .setParameter("bookId", book.getId());
        return queryComment.getResultList();
    }

    @Override
    public List<Comment> findAllCommentByReader(Reader reader) {
        TypedQuery<Comment> queryComment =
                em.createQuery("select c from Comment c , BookBorrow b where c = b.comment and b.reader.id = :readerId", Comment.class)
                        .setParameter("readerId", reader.getId());
        return queryComment.getResultList();
    }
}
