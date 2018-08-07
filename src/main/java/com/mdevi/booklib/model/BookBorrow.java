package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(BorrowPK.class)
@Table(name = "borrows", schema = "new_schema")
public class BookBorrow {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Id
    @Column(name = "date_from")
    @Temporal(TemporalType.DATE)
    private Date dateFrom;


    @Column(name = "date_till")
    @Temporal(TemporalType.DATE)
    private Date dateTill;

    @Column(name = "date_return")
    @Temporal(TemporalType.DATE)
    private Date dateReturn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public BookBorrow() {
    }

    public BookBorrow(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
    }


    public BookBorrow(Book book, Reader reader, Date dateFrom, Date dateTill) {
        this.book = book;
        this.reader = reader;
        this.dateFrom = dateFrom;
        this.dateTill = dateTill;
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

    public Date getDateTill() {
        return dateTill;
    }

    public void setDateTill(Date dateTill) {
        this.dateTill = dateTill;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BookBorrow{" +
                "book=" + book.getBookTitle() +
                ", reader=" + reader.getName() +
                ", dateFrom=" + dateFrom.toString() +
                ", dateReturn=" + (dateReturn == null ? "hasn't returned." : dateReturn.toString()) +
                ", dateTill=" + dateTill.toString() +
                '}';
    }
}
