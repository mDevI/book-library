package com.mdevi.booklib.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BorrowId implements Serializable {
    @Column(name = "reader_id")
    private Integer readerId;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;


    private BorrowId() {
    }

    public BorrowId(Integer readerId, Integer bookId, Date dateFrom) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.dateFrom = dateFrom;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
        if (!(o instanceof BorrowId)) return false;
        BorrowId borrowId = (BorrowId) o;
        return Objects.equals(getReaderId(), borrowId.getReaderId()) &&
                Objects.equals(getBookId(), borrowId.getBookId()) &&
                Objects.equals(getDateFrom(), borrowId.getDateFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReaderId(), getBookId(), getDateFrom());
    }
}
