package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.dao.ReaderDAO;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReaderRepository implements ReaderDAO {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Reader> findAll() {
        return em.createQuery("select r from Reader r", Reader.class).getResultList();
    }

    @Override
    public Reader findByName() {
        return null;
    }

    @Override
    public Integer insert(Reader reader) {
        return null;
    }

    @Override
    public void delete(Reader reader) {

    }

    @Override
    public void update(Reader reader) {

    }
}
