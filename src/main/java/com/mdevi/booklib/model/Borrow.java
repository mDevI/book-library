package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "borrows", schema = "new_schema")
public class Borrow {

    @EmbeddedId
    private BorrowID id;

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
        this.id = new BorrowID(book, reader, dateFrom);
    }


    public BorrowID getId() {
        return id;
    }

    public void setId(BorrowID id) {
        this.id = id;
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
        return "Borrow{" +
                "book=" + id.getBook().getBookTitle() +
                ", reader=" + id.getReader().getName() +
                ", dateFrom=" + id.getDateFrom().toString() +
                '}';
    }


}
