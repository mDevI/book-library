package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.repository.BookRepository;
import com.mdevi.booklib.dao.repository.BorrowRepository;
import com.mdevi.booklib.dao.repository.CommentRepository;
import com.mdevi.booklib.dao.repository.ReaderRepository;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Borrow;
import com.mdevi.booklib.model.Comment;
import com.mdevi.booklib.model.Reader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class ReaderOperations {

    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final BorrowRepository borrowRepository;


    public ReaderOperations(ReaderRepository readerRepository,
                            BookRepository bookRepository,
                            CommentRepository commentRepository, BorrowRepository borrowRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.borrowRepository = borrowRepository;
    }

    @Transactional
    public void borrowBook(String bookId, String readerId, String term) {
        Optional<Book> book = bookRepository.findById(Integer.parseInt(bookId));
        Optional<Reader> reader = readerRepository.findById(Integer.parseInt(readerId));
        if (book.isPresent() && reader.isPresent()) {
            if (book.get().getQuantity() > 0) {
                LocalDate today = LocalDate.now();
                LocalDate returnDay = today.plusDays(Long.parseLong(term));
                Date dateFrom = Date.valueOf(today);
                Date dateTill = Date.valueOf(returnDay);

                Borrow borrow = new Borrow(book.get(), reader.get(), dateFrom, dateTill);
                borrowRepository.save(borrow);
                // This is a correction of an available count of books in a library.
                int count = book.get().getQuantity();
                book.get().setQuantity(--count);
                // Save to
                bookRepository.save(book.get());

                System.out.println("The book \'" + book.get().getBookTitle() + "\' has been borrowed by " +
                        reader.get().getName() + " till " + dateTill.toString());
            } else {
                System.out.println("This book is not available to borrow at the moment.");
            }
        }
    }

    public void findAllBorrowedBooksByReader(Integer readerID) {
        Optional<Reader> theReader = readerRepository.findById(readerID);
        if (theReader.isPresent()) {
            List<Borrow> borrowsByReader = borrowRepository.findBorrowsById_ReaderId(theReader.get().getId());
            // cutting off all returned books by that reader...
            borrowsByReader = borrowsByReader.stream()
                    .filter(borrow -> borrow.getDateReturn() == null)
                    .collect(Collectors.toList());
            // if reader has currently had a book to be returned to the library then print it out.
            if (borrowsByReader.size() > 0) {
                printBookList(borrowsByReader, theReader.get());
            } else {
                System.out.println("The reader " + theReader.get().getName() + " hasn't currently debt any books.");
            }
        } else {
            System.out.println("There is no such reader.");
        }
    }

    private void printBookList(List<Borrow> books, Reader reader) {
        int stringNumber = 1;
        System.out.println("The reader " + reader.getName() + " has currently borrowed that book(s):");
        System.out.printf("%4s %10s %52s %12s\n", "#", "BOOK ID", "TITLE", "DATE TILL");
        for (Borrow borrow : books) {
            System.out.printf("%4s %10s %52s %12s\n", stringNumber, borrow.getId().getBook().getId(),
                    borrow.getId().getBook().getBookTitle(), borrow.getDateTill().toString());
            stringNumber++;
        }

    }


    @Transactional
    public void takeBackTheBook(Integer bookId, Integer readerID) {
        Optional<Reader> theReader = readerRepository.findById(readerID);
        Optional<Book> theBook = bookRepository.findById(bookId);
        Borrow borrowToReturn;
        if (theReader.isPresent() && theBook.isPresent()) {
            List<Borrow> borrows = borrowRepository.findById_ReaderAndId_Book(theReader.get(), theBook.get()).stream()
                    .filter(borrow -> borrow.getDateReturn() == null).collect(Collectors.toList());
            if (borrows.size() > 0 && borrows.get(0).getId().getBook().equals(theBook.get())) {
                borrowToReturn = borrows.get(0);
                borrowToReturn.setDateReturn(Date.valueOf(LocalDate.now()));
                int countAvail = borrowToReturn.getId().getBook().getQuantity();
                borrowToReturn.getId().getBook().setQuantity(++countAvail);
                borrowRepository.save(borrowToReturn);
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
                    borrowToReturn.setComment(theComment);
                    borrowRepository.save(borrowToReturn);
                    System.out.println("Your comment has been saved.");
                }
            } else {
                System.out.println("The reader with ID = " + readerID + " hasn't borrowed the book with ID = " + bookId);
            }
        }
    }

    public void showAllBookComments(Integer bookId) {
        Optional<Book> theBook = bookRepository.findById(bookId);
        if (theBook.isPresent()) {
            List<Comment> comments = borrowRepository.findBorrowsById_Book_Id(bookId).stream()
                    .map(Borrow::getComment)
                    .collect(Collectors.toList());
            if (comments.size() > 0) {
                printCommentsList(comments, theBook);
            } else {
                System.out.println("That book hasn't any comments yet.");
            }
        }
    }

    public void showAllReaderComments(Integer readerId) {
        Optional<Reader> theReader = readerRepository.findById(readerId);
        if (theReader.isPresent()) {
            List<Comment> comments = borrowRepository.findBorrowsById_ReaderId(readerId).stream()
                    .map(Borrow::getComment)
                    .collect(Collectors.toList());
            if (comments.size() > 0) {
                printCommentsList(comments, theReader);
            } else {
                System.out.println("That reader hasn't done any comments yet.");
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
