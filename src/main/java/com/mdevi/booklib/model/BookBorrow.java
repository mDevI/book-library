package com.mdevi.booklib.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    @MapsId("dateFrom")
    private LocalDate dateFrom = LocalDate.now();

    @Column(name = "date_till")
    @Temporal(TemporalType.DATE)
    private LocalDate dateTill;

    @Column(name = "date_return")
    @Temporal(TemporalType.DATE)
    private LocalDate dateReturn;

    @OneToOne
    private Comment comment;

}
