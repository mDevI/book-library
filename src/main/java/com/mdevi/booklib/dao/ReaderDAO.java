package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Reader;

import java.util.List;

public interface ReaderDAO {
    List<Reader> findAll();

    Reader findByName();

    Integer insert(Reader reader);

    void delete(Reader reader);

    void update(Reader reader);
//    List<Book> borrowTheBook(Book book);
//    boolean takeBackTheBook(Book book);
//
}
