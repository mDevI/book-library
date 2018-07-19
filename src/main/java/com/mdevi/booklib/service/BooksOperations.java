package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Author;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@ShellComponent
@Service
public class BooksOperations {

    private static final String ALL_BOOKS_TITLE = " ALL BOOKS INFO. ";
    private static final String ALL_BOOKS_BY_ID = " ALL BOOKS BY ID. ";
    private static final String ALL_BOOKS_BY_TITLE = " ALL BOOKS BY TITLE. ";
    private static final String ALL_BOOKS_BY_AUTHOR = "ALL BOOKS BY AUTHOR. ";
    private static final String ALL_BOOKS_BY_GENRE = "ALL BOOKS BY GENRE.";

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;

    @Autowired
    public BooksOperations(BookDAO bookDAO, AuthorDAO authorDAO, GenreDAO genreDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.genreDAO = genreDAO;
    }

    @ShellMethod(value = "Show all the books info.")
    public void findAllBooks() {
        List<Book> theBooks = bookDAO.findAllWithDetails();
        printBookList(theBooks, ALL_BOOKS_TITLE);
    }

    @ShellMethod(value = "Find the books with specified title.")
    public void findBookByTitle(
            @ShellOption(value = "--title", help = "Search the books by title.") String titlePattern,
            @ShellOption(value = "--strictSearch", help = "An option is used to turn on a strict search.") Boolean searchStrongOption) {
        List<Book> theBooks = new ArrayList<>();
        if (searchStrongOption) {
            theBooks.add(bookDAO.findByTitle(titlePattern));
        } else {
            theBooks = bookDAO.findByTitleLike(titlePattern);
        }
        printBookList(theBooks, ALL_BOOKS_BY_TITLE);
    }

    @ShellMethod(value = "Find books by author name")
    public void findBookByAuthor() {

    }



    @ShellMethod(value = "Add new book info to the library.")
    public void addBookInfo() {
        List<Author> authors = authorDAO.findAll();
        List<Genre> genres = genreDAO.findAll();
        final Scanner sc = new Scanner(System.in);
        printGenresInfo(genres);
        System.out.print("Please, select the genre ID for your new book: ");
        Integer genreId = sc.nextInt();
        Genre genre = this.genreDAO.findById(genreId);
        printAuthorsInfo(authors);
        System.out.print("Please, select the author ID for your new book: ");
        Integer authorId = sc.nextInt();
        Author author = this.authorDAO.findById(authorId);
        System.out.print("Please, enter the book's title: ");
        String title = sc.nextLine();
        System.out.print("Please, enter count of pages: ");
        Integer pages = sc.nextInt();
        System.out.print("Please, enter count of books: ");
        Integer count = sc.nextInt();
        Book newBook = new Book(0, title, author, genre, pages, count);
        System.out.println(newBook);
        //this.bookDAO.insert(newBook);
    }

    private void printGenresInfo(List<Genre> genres) {
        int rows = 3;
        int columns = genres.size()%3 == 0 ? genres.size()/3 : genres.size()/3 +1 ;
        Genre[][] genre = new Genre[rows][columns];
        int index = 0;
        // fill out our genres matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(genres.size() >= index + 1) {
                    genre[i][j] = genres.get(index);
                } else {
                    genre[i][j] = null;
                }
                index++;
            }
        }

        for (int i = 0; i < columns; i++) {
            for (int j = i+1; j < rows; j++) {
                Genre temp = genre [i][j];
                genre[i][j] = genre[j][i];
                genre[j][i] = temp;
            }
        }


    }

    private void printAuthorsInfo(List<Author> authors) {

    }

    private void printBookList(List<Book> bookList, String paragraph) {

        if (bookList != null && bookList.size() > 0) {
            System.out.println("______________________________________________________________________________" + paragraph +
                    "_________________________________________________________________________");
            System.out.printf("| %3s | %65s | %30s | %40s | %6s | %6s |\n","ID", "BOOK TITLE", "GENRE", "AUTHOR", "PAGES", "AVAIL." );
            bookList.forEach(book -> System.out.printf("| %3s | %65s | %30s | %40s | %6s | %6s |\n",
                    book.getId(), book.getBookTitle(), book.getGenre().getTitle(), book.getAuthor().getName(), book.getPages(), book.getQuantity()));
            System.out.println("______________________________________________________________________________" + paragraph +
                    "_________________________________________________________________________");
        }
    }


}
