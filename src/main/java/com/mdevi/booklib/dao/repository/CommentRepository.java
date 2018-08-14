package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
