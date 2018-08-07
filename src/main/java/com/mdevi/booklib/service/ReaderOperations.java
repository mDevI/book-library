package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.CommentDAO;
import com.mdevi.booklib.dao.ReaderDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.BookBorrow;
import com.mdevi.booklib.model.Comment;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


@Service
@Transactional(readOnly = true)
public class ReaderOperations {

    private final ReaderDAO readerDAO;
    private final BookDAO bookDAO;
    private final CommentDAO commentDAO;


    public ReaderOperations(ReaderDAO readerDAO, BookDAO bookDAO, CommentDAO commentDAO) {
        this.readerDAO = readerDAO;
        this.bookDAO = bookDAO;
        this.commentDAO = commentDAO;
    }

    @Transactional
    public void borrowBook(String bookId, String readerId, String term) {
        Book book = bookDAO.findById(Integer.parseInt(bookId));
        Reader reader = readerDAO.findById(Integer.parseInt(readerId));
        if (book.getQuantity() > 0) {
            LocalDate today = LocalDate.now();
            LocalDate returnDay = today.plusDays(Long.parseLong(term));
            Date dateFrom = Date.valueOf(today);
            Date dateTill = Date.valueOf(returnDay);
            readerDAO.borrowTheBook(book, reader, dateFrom, dateTill);
            System.out.println("The book \'" + book.getBookTitle() + "\' has been borrowed by " +
                    reader.getName() + " till " + dateTill.toString());
        } else {
            System.out.println("This book is not available to borrow at the moment.");
        }
    }

    public void findAllBorrowedBooksByReader(Integer readerID) {
        Reader theReader = readerDAO.findById(readerID);
        if (theReader != null) {
            List<Book> books = readerDAO.findAllBooksBorrowedByReader(theReader);
            if (books.size() > 0) {
                printBookList(books, theReader);
            } else {
                System.out.println("The reader " + theReader.getName() + " hasn't currently debt any books.");
            }
        } else {
            System.out.println("There is no such reader.");
        }

    }

    private void printBookList(List<Book> books, Reader reader) {
        int stringNumber = 1;
        System.out.println("The reader: " + reader.getName() + " has borrowed that book(s):");
        System.out.printf("%4s %10s %32s\n", "#", "BOOK ID", "TITLE");
        for (Book book : books) {
            System.out.printf("%4s %10s %32s\n", stringNumber, book.getId(), book.getBookTitle());
            stringNumber++;
        }

    }


    @Transactional
    public void takeBackTheBook(Integer bookId, Integer readerID) {
        Reader theReader = readerDAO.findById(readerID);
        Book theBook = bookDAO.findById(bookId);
        if (theReader != null) {
            List<Book> books = readerDAO.findAllBooksBorrowedByReader(theReader);
            if (books.size() > 0 && books.contains(theBook)) {
                BookBorrow bookBorrow = readerDAO.returnTheBook(theBook, theReader);
                System.out.println("The book has been taken back to the library.");
                System.out.print("Do you wish to comment that book? (Y)es or (N)o : ");
                Scanner sc = new Scanner(System.in);
                String answerToModify = sc.nextLine();
                if (answerToModify.toUpperCase().equals("Y")) {
                    System.out.println("Please, write back your comment below: (type \'q.q\' to exit)");
                    StringBuilder textComment = new StringBuilder();
                    String buffer = "";
                    while (!"q.q".equals(buffer)) {
                        textComment.append(buffer);
                        buffer = sc.nextLine();
                    }
                    Comment theComment = new Comment();
                    theComment.setComment(textComment.toString());
                    theComment.setCommentDate(Date.valueOf(LocalDate.now()));
                    commentDAO.insert(bookBorrow, theComment);
                    System.out.println("Your book comment has been saved.");
                }
            }
        } else {
            System.out.println("The reader with ID = " + readerID + " hasn't borrowed the book with ID = " + bookId);
        }
    }

    public void showAllBookComments(Integer bookId) {
        Book theBook = bookDAO.findById(bookId);
        if (theBook != null) {
            List<Comment> comments = commentDAO.findAllCommentsByBook(theBook);
            if (comments.size() > 0) {
                printCommentsList(comments, theBook);
            }
        }
    }

    public void showAllReaderComments(Integer readerId) {
        Reader theReader = readerDAO.findById(readerId);
        if (theReader != null) {
            List<Comment> comments = commentDAO.findAllCommentByReader(theReader);
            if (comments.size() > 0) {
                printCommentsList(comments, theReader);
            }
        }
    }

    private void printCommentsList(List<Comment> comments, Object o) {
        Book book;
        Reader reader;
        if (o instanceof Book) {
            book = (Book) o;
            System.out.println("Book: " + book.getBookTitle() + " has that comment(s): ");
        } else if (o instanceof Reader) {
            reader = (Reader) o;
            System.out.println("Reader: " + reader.getName() + " has made that comment(s): ");
        }
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment.getCommentDate().toString() + " " + comment.getComment());
        }
    }
}
