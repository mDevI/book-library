package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.ReaderDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class ReaderOperations {

    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;


    public ReaderOperations(ReaderDAO readerDAO, BookDAO bookDAO) {
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
    }

    @Transactional
    public void borrowBook(String bookId, String readerId, String term) {
        Book book = bookDAO.findById(Integer.parseInt(bookId));
        Reader reader = readerDAO.findById(Integer.parseInt(readerId));
        LocalDate today = LocalDate.now();
        LocalDate returnDay = today.plusDays(Long.parseLong(term));
        Date dateFrom = Date.valueOf(today);
        Date dateTill = Date.valueOf(returnDay);
        readerDAO.borrowTheBook(book, reader, dateFrom, dateTill);
    }

    public void findAllBorrowedBooksByReader(Integer readerID) {
        Reader theReader = readerDAO.findById(readerID);
        if (theReader != null) {
            List<Book> books = readerDAO.findAllBooksBorrowedByReader(theReader);
            if (books.size() > 0) {
                printBookList(books, theReader);
            }
        } else {
            System.out.println("There is no such reader.");
        }

    }

    private void printBookList(List<Book> books, Reader reader) {
        int stringNumber = 1;
        System.out.println("The reader: " + reader.getName() + " has borrowed that book(s):");
        System.out.println("#    book id:          book title:");
        for (Book book : books) {
            System.out.printf("%2s %10s %32s", stringNumber, book.getId(), book.getBookTitle());
            stringNumber++;
            System.out.println();
        }
    }


    @Transactional
    public void takeBackTheBook(Integer bookId, Integer readerID) {
        Reader theReader = readerDAO.findById(readerID);
        Book theBook = bookDAO.findById(bookId);
        if (theReader != null) {
            List<Book> books = readerDAO.findAllBooksBorrowedByReader(theReader);
            if (books.size() > 0 && books.contains(theBook)) {
                readerDAO.returnTheBook(theBook, theReader);
                System.out.println();
            }
        } else {
            System.out.println("The reader with ID = " + readerID + " hasn't borrowed the book with ID = " + bookId);
        }
    }
}
