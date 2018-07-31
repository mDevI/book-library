package com.mdevi.booklib.model;

import javax.persistence.*;

@Entity
@Table(name = "books", schema = "new_schema")
public class Book {
    @Id
    //@SequenceGenerator(name = "book_gen", sequenceName = "books_id_seq")
    //@GeneratedValue(generator = "book_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;
    @Column(name = "title")
    private String bookTitle;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Genre genre;
    @Column(name = "pages")
    private int pages;
    @Column(name = "count")
    private int quantity;

    public Book() {
    }

    public Book(int id, String bookTitle, Author author, Genre genre, int pages, int quantity) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
        this.quantity = quantity;
    }

    public Book(int id, String bookTitle, Author author, Genre genre) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.author = author;
        this.genre = genre;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                ", pages=" + pages +
                ", quantity=" + quantity +
                '}';
    }
}
