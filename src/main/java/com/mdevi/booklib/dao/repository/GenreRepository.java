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
        List results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty(); // handle no-results case
        } else {
            return Optional.ofNullable((Genre) results.get(0));
        }
    }

    @Override
    public Integer insert(Genre genre) {
        em.persist(genre);
        em.flush();
        return genre.getId();
    }

    @Override
    public void update(Genre genre) {
        em.persist(genre);
    }

    @Override
    public void delete(Genre genre) {
        em.remove(genre);
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Genre> theGenre = this.findById(id);
        theGenre.ifPresent(genre -> em.remove(genre));

    }

    @Override
    public Integer count() {
        Query query = em.createQuery("select count(g) from Genre g");
        return query.getFirstResult();
    }
}
