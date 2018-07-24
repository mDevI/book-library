package com.mdevi.booklib.model;

public class Book {
    private int id;
    private String bookTitle;
    private Author author;
    private Genre genre;
    private int pages;
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
