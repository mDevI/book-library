package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Author;
import com.mdevi.booklib.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    Book findById(Integer id);

    List<Book> findByTitleLike(String title);

    Book findByTitle(String title);

    Book findByAuthor(String author);

    List<Book> findByAuthor(Author author);

    List<Book> findByAuthorLike(String authorName);

    List<Book> findByGenreId(Integer genreId);

    String findTitleById(Integer id);


    void insert(Book book);

    void update(Book book);

    void delete(Integer id);

    List<Book> findAllWithDetails();

    void insertWithDetails(Book book);

}
