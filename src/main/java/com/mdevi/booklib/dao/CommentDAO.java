package com.mdevi.booklib.dao;

import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.BookBorrow;
import com.mdevi.booklib.model.Comment;
import com.mdevi.booklib.model.Reader;

import java.util.List;

public interface CommentDAO {
    void insert(BookBorrow bookBorrow, Comment comment);

    List<Comment> findAllCommentsByBook(Book book);

    List<Comment> findAllCommentByReader(Reader reader);
}
