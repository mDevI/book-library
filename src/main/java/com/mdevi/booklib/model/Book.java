package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "new_schema")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", unique = true, nullable = false)
    private int id;
    @Column(name = "title")
    private String bookTitle;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "author")
    private Author author;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "genre")
    private Genre genre;
    @Column(name = "pages")
    private int pages;
    @Column(name = "count")
    private int quantity;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookBorrow> readers = new ArrayList<>();


    public Book() {
    }

    public Book(String bookTitle, Author author, Genre genre, int pages, int quantity) {
        this.id = 0;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<BookBorrow> getReaders() {
        return readers;
    }

    public void setReaders(List<BookBorrow> readers) {
        this.readers = readers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", pages=" + pages +
                ", quantity=" + quantity +
                '}';
    }
}
