package com.mdevi.booklib.model;

import javax.persistence.*;

@Entity
@Table(name = "readers", schema = "new_schema")
public class Reader {
    @Id
    @SequenceGenerator(name = "reader_gen", sequenceName = "readers_reader_id_seq")
    @GeneratedValue(generator = "reader_gen")
    @Column(name = "reader_id")
    private int id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "rank")
    private int rank;
    @Column(name = "discount")
    private byte discount_point;


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


}
