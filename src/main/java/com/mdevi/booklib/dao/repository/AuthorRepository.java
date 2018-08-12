package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    List<Author> findAll();

    Optional<Author> findAuthorById(Integer integer);

    void delete(Author author);

    void deleteById(Integer integer);

    long count();
}
