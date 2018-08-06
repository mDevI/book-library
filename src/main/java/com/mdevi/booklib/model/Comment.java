package com.mdevi.booklib.model;

import javax.persistence.*;
import java.util.Date;

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
    private Date commentDate;

    @Column(name = "rating")
    private byte rating;


    public Comment() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", commentDate=" + commentDate.toString() +
                ", rating=" + rating +
                '}';
    }
}
