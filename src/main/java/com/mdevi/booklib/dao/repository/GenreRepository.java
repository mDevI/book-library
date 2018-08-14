package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findById(Integer id);

    Optional<Genre> findByTitle(String title);

    void deleteById(Integer id);

    long count();
}
