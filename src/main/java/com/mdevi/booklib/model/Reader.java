package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers", schema = "new_schema")
public class Reader {
    @Id
    //@SequenceGenerator(name = "reader_gen", sequenceName = "readers_reader_id_seq")
    //@GeneratedValue(generator = "reader_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reader_id")
    private int id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "rank")
    private int rank;
    @Column(name = "discount")
    private byte discount_point;

    @OneToMany(mappedBy = "id.reader", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrow> borrows = new ArrayList<>();

    public Reader() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public byte getDiscount_point() {
        return discount_point;
    }

    public void setDiscount_point(byte discount_point) {
        this.discount_point = discount_point;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }
}
