package com.mdevi.booklib.shell;

import com.mdevi.booklib.service.AuthorsOperations;
import com.mdevi.booklib.service.BooksOperations;
import com.mdevi.booklib.service.GenreOperations;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import javax.validation.constraints.NotNull;

@ShellComponent
public class AppCommand {

    private final AuthorsOperations authorsOperations;
    private final BooksOperations booksOperations;
    private final GenreOperations genreOperations;

    public AppCommand(AuthorsOperations authorsOperations, BooksOperations booksOperations, GenreOperations genreOperations) {
        this.authorsOperations = authorsOperations;
        this.booksOperations = booksOperations;
        this.genreOperations = genreOperations;
    }

    @ShellMethod(value = "Show all books authors info.", key = "show-all-authors", group = "Author operations")
    public void showAuthors() {
        authorsOperations.showAuthors();
    }

    @ShellMethod(value = "Add new author info.", key = "add-new-author", group = "Author operations")
    public void addNewAuthor(
            @ShellOption(value = "--name", help = "--name \"author-full-name\"", arity = 1) @NotNull String name,
            @ShellOption(value = "--dateBirth", help = "--dateBirth \"dd-MM-yyyy\"", arity = 1) @NotNull String dob
    ) {
        authorsOperations.addNewAuthor(name, dob);
    }

    @ShellMethod(value = "Delete the selected author by it's ID.", key = "delete-by-id", group = "Author operations")
    public void deleteAuthor(
            @ShellOption(value = "--id", help = "--id number", arity = 1) Integer id) {
        authorsOperations.deleteAuthor(id);
    }

    @ShellMethod(value = "Update author's info.", key = "update-author-info", group = "Author operations")
    public void updateAuthorByID(@ShellOption Integer id) {
        authorsOperations.updateAuthorByID(id);
    }

    @ShellMethod(value = "Show all genres.", key = "show-all-genres", group = "Genre operations")
    public void findAllGenres() {
        genreOperations.findAllGenres();
    }

    @ShellMethod(value = "Find genre by Id.", key = "find-genre-by-id", group = "Genre operations")
    public void findGenreById(@ShellOption("--id") Integer id) {
        genreOperations.findGenreById(id);
    }

    @ShellMethod(value = "Find genre by title.", key = "find-genre-by-title", group = "Genre operations")
    public void findGenreByTitle(@ShellOption("--title") String title) {
        genreOperations.findGenreByTitle(title);
    }


    @ShellMethod(value = "Add a new genre.", key = "add-new-genre", group = "Genre operations")
    public void addNewGenre(@ShellOption(value = "--genre", help = "--genre \"genre-title\"", arity = 1) String genreToAdd) {
        genreOperations.addNewGenre(genreToAdd);
    }

    @ShellMethod(value = "Delete the genre.", key = "delete-genre", group = "Genre operations")
    public void deleteGenreById(@ShellOption(value = "--id", help = "Select the id to delete the genre.") Integer id) {
        genreOperations.deleteGenreById(id);
    }

    @ShellMethod(value = "Show all the books info.", key = "show-all-books", group = "Books operations")
    public void findAllBooks() {
        booksOperations.findAllBooks();
    }

    @ShellMethod(value = "Find the books with specified title.", key = "find-by-title", group = "Books operations")
    public void findBookByTitle(
            @ShellOption(value = "--title", help = "Search the books by title.") String titlePattern,
            @ShellOption(value = "--strictSearch", help = "An option is used to turn on a strict search.") Boolean isStrictSearch) {
        booksOperations.findBookByTitle(titlePattern, isStrictSearch);
    }

    @ShellMethod(value = "Find books by author name", key = "find-by-author", group = "Books operations")
    public void findBookByAuthor(
            @ShellOption(value = "--authorname", help = "Search the books by authors name.") String authorName,
            @ShellOption(value = "--strictSearch", help = "Turn on a strict search.") Boolean strictSearch) {
        booksOperations.findBookByAuthor(authorName, strictSearch);
    }

    @ShellMethod(value = "Add new book info to the library.", key = "add-book", group = "Books operations")
    public void addBookInfo() {
        booksOperations.addBookInfo();
    }

    @ShellMethod(value = "Update the selected book info.", key = "update-book", group = "Books operations")
    public void updateBookInfo(@ShellOption(value = "--bookID", help = "Given book ID.") Integer bookId) {
        booksOperations.updateBookInfo(bookId);
    }

    @ShellMethod(value = "Delete the selected book.", key = "delete-book", group = "Books operations")
    public void deleteBookById(@ShellOption(value = "--id", help = "Select the ID to delete selected book's info.") Integer id) {
        booksOperations.deleteBookById(id);
    }


}
