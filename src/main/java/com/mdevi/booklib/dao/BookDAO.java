package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Author;
import com.mdevi.booklib.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByAuthor(Author author);

    String findTitleById(Integer id);

    void insert(Book book);

    void update(Book book);

    void delete(Integer id);

    List<Book> findAllWithDetails();

    void insertWithDetails(Book book);
}
