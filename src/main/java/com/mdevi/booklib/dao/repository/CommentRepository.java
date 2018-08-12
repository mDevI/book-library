package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

//    List<Comment> findAllByBookBorrow_Reader(Reader reader);
//
//
//
//    List<Comment> findAllCommentsByBook(Book book);
//
//    List<Comment> findAllCommentByReader(Reader reader);
}
