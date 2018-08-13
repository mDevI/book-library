package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrows", schema = "new_schema")
public class Borrow {

    @EmbeddedId
    private BorrowID id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("bookId")
//    private Book book;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("readerId")
//    private Reader reader;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_till")
    private Date dateTill;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_return")
    private Date dateReturn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private Comment comment;


    public Borrow() {
    }

    public Borrow(Book book, Reader reader, Date dateFrom, Date dateTill) {
        this.dateTill = dateTill;
        this.id = new BorrowID(reader.getId(), book.getId(), dateFrom);
    }

    public Borrow(Integer bookId, Integer readerId, Date dateFrom, Date dateTill) {
        this.id = new BorrowID();
        this.id.setBookId(bookId);
        this.id.setReaderId(readerId);
        this.id.setDateFrom(dateFrom);
        this.dateTill = dateTill;
    }


    public BorrowID getId() {
        return id;
    }

    public void setId(BorrowID id) {
        this.id = id;
    }

//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
//
//    public Reader getReader() {
//        return reader;
//    }
//
//    public void setReader(Reader reader) {
//        this.reader = reader;
//    }

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
        return "Borrow{" +
//                "book=" + book.getBookTitle() +
//                ", reader=" + reader.getName() +
                ", dateFrom=" + id.getDateFrom().toString() +
                '}';
    }


}
