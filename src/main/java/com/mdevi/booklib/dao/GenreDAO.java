package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDAO {
    List<Genre> findAll();

    Optional<Genre> findById(Integer id);

    Genre findByTitle(String title);

    Integer insert(Genre genre);

    void update(Genre genre);

    void delete(Genre genre);

    void deleteById(Integer id);

    Integer count();
}
