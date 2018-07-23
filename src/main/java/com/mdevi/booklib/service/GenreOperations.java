package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.BookDAO;
import com.mdevi.booklib.dao.GenreDAO;
import com.mdevi.booklib.model.Book;
import com.mdevi.booklib.model.Genre;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ShellComponent
@Service
@Transactional
public class GenreOperations {
    private final GenreDAO genreDAO;
    private final BookDAO bookDAO;

    public GenreOperations(GenreDAO genreDAO, BookDAO bookDAO) {
        this.genreDAO = genreDAO;
        this.bookDAO = bookDAO;
    }

    @ShellMethod(value = "Show all genres.")
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

    @ShellMethod(value = "Find genre by Id.")
    public void findGenreById(@ShellOption("--id") Integer id) {
        Optional<Genre> theGenre = genreDAO.findById(id);
        if (theGenre.isPresent()) {
            System.out.println(theGenre.get().toString());
        } else {
            System.out.println("A genre with id = " + id + " is not found.");
        }
    }

    @ShellMethod(value = "Find genre by title.")
    public void findGenreByTitle(@ShellOption("--title") String title) {
        Genre genre = genreDAO.findByTitle(title);
        System.out.println(genre.toString());
    }


    @ShellMethod(value = "Add a new genre.")
    public void addNewGenre(@ShellOption(value = "--genre") String genreToAdd) {
        Genre newGenre = new Genre();
        newGenre.setTitle(genreToAdd);
        Genre genre = genreDAO.findByTitle(genreToAdd);

        if (genre == null) {
            if (genreDAO.insert(newGenre) > 0) {
                System.out.println("New genre has been saved.");
            }
        } else {
            System.out.println("The such genre is already exists.");
        }
    }

    @ShellMethod(value = "Delete the genre.")
    public void deleteGenreById(@ShellOption(value = "--id", help = "Select the id to delete the genre.") Integer id) {
        List<Book> books = bookDAO.findByGenreId(id);
        List<Genre> allGenres = genreDAO.findAll();
        if (books.size() > 0) {
            System.out.println("The deletion impossible due to the books is found with such ID.");
        } else if (!allGenres.stream().map(Genre::getId).collect(Collectors.toList()).contains(id)) {
            System.out.println("There is no such genre");
        } else {
            System.out.println("The genre has been deleted successfully");
            genreDAO.deleteById(id);
        }

    }


}
