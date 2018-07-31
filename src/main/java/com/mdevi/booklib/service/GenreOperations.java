package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Genre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GenreOperations {
    private final GenreDAO genreDAO;
    private final BookDAO bookDAO;

    public GenreOperations(GenreDAO genreDAO, BookDAO bookDAO) {
        this.genreDAO = genreDAO;
        this.bookDAO = bookDAO;
    }

    @Transactional(readOnly = true)
    public void findAllGenres() {
        List<Genre> genres = genreDAO.findAll();
        genres.sort(Comparator.comparingInt(Genre::getId));
        if (genres.size() > 0) {
            System.out.println("_______________ Genres ________________");
            System.out.println("| ID |           Title                 ");
            for (Genre genre : genres) {
                System.out.printf("| %2d | %30s |", genre.getId(), genre.getTitle());
                System.out.println();
            }
            System.out.println("_______________ Genres ________________");
        } else {
            System.out.println("There isn't any genre in library database.");
        }
    }

    @Transactional(readOnly = true)
    public void findGenreById(Integer id) {
        Optional<Genre> theGenre = genreDAO.findById(id);
        if (theGenre.isPresent()) {
            System.out.println(theGenre.get().toString());
        } else {
            System.out.println("A genre with id = " + id + " is not found.");
        }
    }

    @Transactional(readOnly = true)
    public void findGenreByTitle(String title) {
        Optional<Genre> genre = genreDAO.findByTitle(title);
        if (genre.isPresent()) {
            System.out.println(genre.get().toString());
        } else {
            System.out.println("A genre with title = " + title + " is not found.");
        }
    }

    public void addNewGenre(String genreToAdd) {
        Genre newGenre = new Genre();
        newGenre.setTitle(genreToAdd);
        Optional<Genre> genre = Optional.ofNullable(genreDAO.findByTitle(genreToAdd));
        if (!genre.isPresent()) {
            if (genreDAO.insert(newGenre) > 0) {
                System.out.println("New genre has been saved.");
            }
        } else {
            System.out.println("The such genre is already exists.");
        }
    }

    public void deleteGenreById(Integer id) {
        List<Book> books = bookDAO.findByGenreId(id);
        List<Genre> allGenres = genreDAO.findAll();
        if (books.size() > 0) {
            System.out.println("The deletion impossible due to the books have been found with such ID.");
        } else if (!allGenres.stream().map(Genre::getId).collect(Collectors.toList()).contains(id)) {
            System.out.println("There is no such genre");
        } else {
            System.out.println("The genre has been deleted successfully");
            genreDAO.deleteById(id);
        }
    }
}
