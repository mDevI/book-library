package com.mdevi.booklib.dao.repository;

import com.mdevi.booklib.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAll();

    Optional<Book> findBookById(Integer id);

    List<Book> findAllByBookTitleIsContaining(String title);

    List<Book> findBooksByBookTitle(String title);

    Optional<Book> findBookByAuthor_Name(String author);

    List<Book> findBooksByAuthor_NameLike(String author);

    void deleteById(Integer id);

    List<Book> findAllByGenre_Id(Integer genreId);

    List<Book> findBooksByGenreTitle(String genreTitle);

}
