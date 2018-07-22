package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Genre;

import java.util.List;

public interface GenreDAO {
    List<Genre> findAll();

    Genre findById(Integer id);

    Genre findByTitle(String title);

    Integer insert(Genre genre);

    void update(Genre genre);

    void delete(Genre genre);

    void deleteById(Integer id);

    Integer count();
}
