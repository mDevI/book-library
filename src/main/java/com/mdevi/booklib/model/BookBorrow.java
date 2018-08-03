package com.mdevi.booklib.model;

import javax.persistence.*;

@Entity
@Table(name = "borrows", schema = "new_schema")
public class BookBorrow {

    @EmbeddedId
    private BorrowId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("readerId")
    private Reader reader;

    @Column(name = "date_till")
    @Temporal(TemporalType.DATE)
    private java.util.Date dateTill;

    @Column(name = "date_return")
    @Temporal(TemporalType.DATE)
    private java.util.Date dateReturn;

    @OneToOne
    private Comment comment;

}
