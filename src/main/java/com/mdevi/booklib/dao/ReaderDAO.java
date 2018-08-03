package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Reader;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReaderDAO {
    List<Reader> findAll();

    Optional<Reader> findByName(String name);

    Reader findById(Integer id);

    List<Reader> findByNameLike(String name);

    Integer insert(Reader reader);

    void delete(Reader reader);

    void update(Reader reader);

    void borrowTheBook(Book book, Reader reader, LocalDate dateFrom, LocalDate dateTill);

//    boolean takeBackTheBook(Book book);
//
}
