package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> findAll();
    Author findById(Integer id);
    Integer insert(Author author);
    void update(Author author);
    void delete(Author author);
    void deleteById(Integer id);
    int count();
}
