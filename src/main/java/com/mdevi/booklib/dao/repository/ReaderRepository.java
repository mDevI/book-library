package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.ReaderDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    public void borrowTheBook(@NotNull Book book, @NotNull Reader reader, @NotNull LocalDate dateFrom, @NotNull LocalDate dateTill) {

    }
}
