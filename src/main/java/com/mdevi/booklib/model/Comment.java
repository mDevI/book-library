package com.mdevi.booklib.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comments", schema = "new_schema")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment", nullable = false, length = 200)
    private String comment;

    @Column(name = "create_on")
    @Temporal(TemporalType.DATE)
    private LocalDate commentDate;

    @Column(name = "rationg")
    private byte rating;

    public Comment() {
    }
}
