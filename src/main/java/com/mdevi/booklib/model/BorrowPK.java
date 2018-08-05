package com.mdevi.booklib.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


public class BorrowPK implements Serializable {

    private Book book;
    private Reader reader;
    private Date dateFrom;

    public BorrowPK() {
    }

    public BorrowPK(Book book, Reader reader, Date dateFrom) {
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
        if (!(o instanceof BorrowPK)) return false;
        BorrowPK borrowPK = (BorrowPK) o;
        return Objects.equals(getBook(), borrowPK.getBook()) &&
                Objects.equals(getReader(), borrowPK.getReader()) &&
                Objects.equals(getDateFrom(), borrowPK.getDateFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook(), getReader(), getDateFrom());
    }
}
