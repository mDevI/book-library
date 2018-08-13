package com.mdevi.booklib.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BorrowID implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "date_from")
    private Date dateFrom;

    public BorrowID() {
    }

    public BorrowID(Book book, Reader reader, Date dateFrom) {
        this.book = book;
        this.reader = reader;
        this.dateFrom = dateFrom;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BorrowID)) return false;
        BorrowID borrowID = (BorrowID) o;
        return Objects.equals(getBook(), borrowID.getBook()) &&
                Objects.equals(getReader(), borrowID.getReader()) &&
                Objects.equals(getDateFrom(), borrowID.getDateFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getReader(), getDateFrom());
    }
}
