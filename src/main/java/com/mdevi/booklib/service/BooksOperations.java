package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@ShellComponent
@Service
public class BooksOperations {

    private static final String ALL_BOOKS_TITLE = " ALL BOOKS INFO. ";
    private static final String ALL_BOOKS_BY_ID = " ALL BOOKS BY ID. ";
    private static final String ALL_BOOKS_BY_TITLE = " ALL BOOKS BY TITLE. ";
    private static final String ALL_BOOKS_BY_AUTHOR = "ALL BOOKS BY AUTHOR. ";
    private static final String ALL_BOOKS_BY_GENRE = "ALL BOOKS BY GENRE.";


    private final BookDAO bookDAO;


    @Autowired
    public BooksOperations(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @ShellMethod(value = "show all books info.")
    public void showAllBooks() {
        List<Book> bookList = bookDAO.findAllWithDetails();
        printBookList(bookList);
    }

    @ShellMethod(value = "find books with specified title.")
    public void showBookByTitle(
            @ShellOption(value = "--title", help = "Search by book title.") String titlePattern,
            @ShellOption(value = "--isStrongSearch", help = "An option to get search.") Boolean searchStrongOption) {
        List<Book> theBooks = new ArrayList<>();
        if (searchStrongOption) {
            theBooks.add(bookDAO.findByTitle(titlePattern));
        } else {
            theBooks = bookDAO.findByTitleLike(titlePattern);
        }
        printBookList(theBooks);
    }

    private void printBookList(List<Book> bookList) {

        if (bookList != null && bookList.size() > 0) {
            System.out.println("______________________________________________________________________________" + ALL_BOOKS_TITLE +
                    "_________________________________________________________________________");
            System.out.printf("| %3s | %65s | %30s | %40s | %6s | %6s |\n","ID", "BOOK TITLE", "GENRE", "AUTHOR", "PAGES", "AVAIL." );
            bookList.forEach(book -> {
                System.out.printf("| %3s | %65s | %30s | %40s | %6s | %6s |\n",
                        book.getId(), book.getBookTitle(), book.getGenre().getTitle(), book.getAuthor().getName(), book.getPages(), book.getQuantity());
            });
            System.out.println("_____________________________________________________________________________" + ALL_BOOKS_TITLE +
                    "_________________________________________________________________________");
        }
    }
}
