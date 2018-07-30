package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "authors", schema = "new_schema")
public class Author {
    @Id
//    @SequenceGenerator(name = "Author_gen", sequenceName = "authors_id_seq")
//    @GeneratedValue(generator = "Author_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, length = 110)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Book> books;

    @Column(name = "rank")
    private int rank;

    public Author() {
    }

    public Author(int id, String name, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
}

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth.toString() +
                ", rank=" + rank +
                '}';
    }
}
