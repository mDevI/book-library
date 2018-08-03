package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.ReaderDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ReaderOperations {

    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;


    public ReaderOperations(ReaderDAO readerDAO, BookDAO bookDAO) {
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
    }

    public void borrowBook(String bookId, String readerId, String term) {


    }
}
