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


}
