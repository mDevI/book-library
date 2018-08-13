package com.mdevi.booklib.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class BorrowID implements Serializable {

    @Column(name = "reader_id")
    private Integer readerId;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    public BorrowID() {
    }

    public BorrowID(Integer readerId, Integer bookId, Date dateFrom) {
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
        if (!(o instanceof BorrowID)) return false;
        BorrowID borrowID = (BorrowID) o;
        return Objects.equals(getReaderId(), borrowID.getReaderId()) &&
                Objects.equals(getBookId(), borrowID.getBookId()) &&
                Objects.equals(getDateFrom(), borrowID.getDateFrom());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReaderId(), getBookId(), getDateFrom());
    }
}
