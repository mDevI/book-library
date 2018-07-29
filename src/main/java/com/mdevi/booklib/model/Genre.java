package com.mdevi.booklib.model;

import javax.persistence.*;

@Entity
@Table(name = "genres", schema = "new_schema")
public class Genre {

    @Id
    @SequenceGenerator(name = "Genre_gen", sequenceName = "genres_id_seq")
    @GeneratedValue(generator = "Genre_gen")
    private int id;
    @Column(name = "name", nullable = false)
    private String title;

    public Genre() {
    }

    public Genre(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
