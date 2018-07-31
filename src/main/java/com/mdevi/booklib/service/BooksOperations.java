package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Author;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Service
@Transactional
public class BooksOperations {

    private static final String ALL_BOOKS_TITLE = " ALL BOOKS INFO. ";
    private static final String BOOK_BY_ID = " THE BOOK BY ID. ";
    private static final String ALL_BOOKS_BY_TITLE = " ALL BOOKS BY TITLE. ";
    private static final String ALL_BOOKS_BY_AUTHOR = "ALL BOOKS BY AUTHOR. ";
    private static final String ALL_BOOKS_BY_GENRE = "ALL BOOKS BY GENRE.";

    private final BookDAO bookDAO;
    private final AuthorDAO authorDAO;
    private final GenreDAO genreDAO;

    //
    @Autowired
    public BooksOperations(BookDAO bookDAO, AuthorDAO authorDAO, GenreDAO genreDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
        this.genreDAO = genreDAO;
    }

    @Transactional(readOnly = true)
    public void findAllBooks() {
        List<Book> theBooks = bookDAO.findAllWithDetails();
        printBookList(theBooks, ALL_BOOKS_TITLE);
    }

    @Transactional(readOnly = true)
    public void findBookByTitle(String titlePattern, Boolean searchStrongOption) {
        List<Book> theBooks = new ArrayList<>();
        if (searchStrongOption) {
            theBooks.add(bookDAO.findByTitle(titlePattern));
        } else {
            theBooks = bookDAO.findByTitleLike(titlePattern);
        }
        printBookList(theBooks, ALL_BOOKS_BY_TITLE);
    }

    @Transactional(readOnly = true)
    public void findBookByAuthor(String authorName, Boolean strictSearch) {
        List<Book> theBooks = new ArrayList<>();
        if (strictSearch) {
            theBooks.add(bookDAO.findByAuthor(authorName));
        } else {
            theBooks.addAll(bookDAO.findByAuthorLike(authorName));
        }
        printBookList(theBooks, ALL_BOOKS_BY_AUTHOR);
    }

    public void addBookInfo() {
        List<Author> authors = authorDAO.findAll();
        List<Genre> genres = genreDAO.findAll();
        final Scanner sc = new Scanner(System.in);
        printGenresInfo(genres);
        System.out.print("Please, select the genre ID for your new book: ");
        Integer genreId = Integer.parseInt(sc.nextLine());
        Genre genre = this.genreDAO.findById(genreId).get();
        printAuthorsInfo(authors);
        System.out.print("Please, select the author ID for your new book: ");
        Integer authorId = Integer.parseInt(sc.nextLine());
        Author author = this.authorDAO.findById(authorId);
        System.out.print("Please, enter the book's title: ");
        String title = sc.nextLine();
        System.out.print("Please, enter count of pages: ");
        Integer pages = Integer.parseInt(sc.nextLine());
        System.out.print("Please, enter count of books: ");
        Integer count = Integer.parseInt(sc.nextLine());
        Book newBook = new Book(0, title, author, genre, pages, count);
        // final show the books details.
        System.out.println("A new book's info is: " + newBook.toString());
        System.out.println();
        System.out.print("\nAre you sure to save it? (Y)es or (N)o : ");
        String answerToModify = sc.next();
        if (answerToModify.toUpperCase().equals("Y")) {
            this.bookDAO.insert(newBook);
            System.out.println("The book's info has been saved.");
        }
    }

    public void updateBookInfo(Integer bookId) {
        Book theBook = bookDAO.findById(bookId);
        if (theBook != null && theBook.getId() > 0) {

            System.out.println("Please enter new book's info step by step. All values are mandatory.");
            final Scanner sc = new Scanner(System.in);
            System.out.print("Book title: (" + theBook.getBookTitle() + "): ");
            String newBookTitle = sc.nextLine();
            if (!newBookTitle.isEmpty() && !newBookTitle.equals(theBook.getBookTitle()))
                theBook.setBookTitle(newBookTitle);

            System.out.printf("Book page count: (%4s): ", theBook.getPages());
            Integer newBookPages = Integer.parseInt(sc.nextLine());
            if (!newBookPages.equals(theBook.getPages()) || theBook.getPages() == 0)
                theBook.setPages(newBookPages);

            System.out.printf("Book quantity: (%2d): ", theBook.getQuantity());
            Integer newBookCount = Integer.parseInt(sc.nextLine());
            if (newBookCount != theBook.getQuantity()) theBook.setQuantity(newBookCount);

            System.out.printf("Book author ID: (%3d): ", theBook.getAuthor().getId());
            Integer newBookAuthor = Integer.parseInt(sc.nextLine());
            if (authorDAO.findById(newBookAuthor) != null) {
                theBook.setAuthor(authorDAO.findById(newBookAuthor));
            } else {
                System.out.println("Author with ID: " + newBookAuthor + " isn't exist.");
            }

            System.out.printf("Book genre ID: (%3d): ", theBook.getGenre().getId());
            Integer newBookGenre = Integer.parseInt(sc.nextLine());
            if (genreDAO.findById(newBookGenre) != null) {
                theBook.setGenre(genreDAO.findById(newBookGenre).get());
            } else {
                System.out.println("Bool genre with ID: " + newBookGenre + " isn't exist");
            }
            // final show book's info.
            System.out.println("Modified book's info is: " + theBook.toString());
            System.out.println();
            System.out.print("\nAre you sure to save it? (Y)es or (N)o : ");
            String answerToModify = sc.next();
            if (answerToModify.toUpperCase().equals("Y")) {
                bookDAO.update(theBook);
                System.out.println("The book's info has been updated.");
            }
        }
    }

    public void deleteBookById(Integer id) {
        bookDAO.delete(id);
    }


    private void printGenresInfo(List<Genre> genres) {
        genres.sort(Comparator.comparingInt(Genre::getId));
        int index = 0;
        int rows = 3;
        int columns = genres.size() % 3 == 0 ? genres.size() / 3 : genres.size() / 3 + 1;
        Genre[][] genre = new Genre[rows][columns];
        // fill out our genres matrix
        fillOutMatrix(genres, index, rows, columns, genre);
        // transpose the matrix
        int m = genre.length;
        int n = genre[0].length;
        Genre transpose[][] = new Genre[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = genre[i][j];
            }
        }
        // print the list
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (transpose[i][j] != null) {
                    System.out.printf(" %2d.--> %23s \t\t\t", transpose[i][j].getId(), transpose[i][j].getTitle());
                }
            }
            System.out.println();
        }
    }

    private void fillOutMatrix(List<?> listItems, int index, int rows, int columns, Object[][] arrayEntity) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (listItems.size() >= index + 1) {
                    arrayEntity[i][j] = listItems.get(index);
                } else {
                    arrayEntity[i][j] = null;
                }
                index++;
            }
        }
    }

    private void printAuthorsInfo(List<Author> authors) {
        authors.sort(Comparator.comparingInt(Author::getId));
        int index = 0;
        int rows = 4;
        int columns = authors.size() % rows == 0 ? authors.size() / rows : authors.size() / rows + 1;
        Author[][] author = new Author[rows][columns];
        // fill out our authors matrix
        fillOutMatrix(authors, index, rows, columns, author);
        // transpose the matrix
        int m = author.length;
        int n = author[0].length;
        Author transpose[][] = new Author[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transpose[j][i] = author[i][j];
            }
        }
        // print the list
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (transpose[i][j] != null) {
                    System.out.printf(" %2d.) %23s \t\t", transpose[i][j].getId(), transpose[i][j].getName());
                }
            }
            System.out.println();
        }
    }

    private void printBookList(List<Book> bookList, String paragraph) {

        if (bookList != null && bookList.size() > 0) {
            System.out.println("______________________________________________________________________________" + paragraph +
                    "_________________________________________________________________________");
            System.out.printf("| %3s | %65s | %30s | %40s | %6s | %6s |\n", "ID", "BOOK TITLE", "GENRE", "AUTHOR", "PAGES", "AVAIL.");
            bookList.forEach(book -> System.out.printf("| %3s | %65s | %30s | %40s | %6s | %6s |\n",
                    book.getId(), book.getBookTitle(), book.getGenre().getTitle(), book.getAuthor().getName(), book.getPages(), book.getQuantity()));
            System.out.println("______________________________________________________________________________" + paragraph +
                    "_________________________________________________________________________");
        }
    }
}
