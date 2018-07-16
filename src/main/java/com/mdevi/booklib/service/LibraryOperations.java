package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.repository.AuthorRepository;
import com.mdevi.booklib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@ShellComponent
@Service
public class LibraryOperations {

    private final AuthorRepository repository;

    @Autowired
    public LibraryOperations(AuthorRepository repository) {
        this.repository = repository;
    }

    @ShellMethod(value = "Show all books authors info.", key = "getAuthors")
    public void showAuthors(){
        List<Author> authors = repository.findAll();
        if (authors != null && authors.size()>0) {
            System.out.printf("____________________        Authors       ____________________\n");
            System.out.printf("| %2s | %40s | %4s |\n", "ID", "NAME", "RANK");
            for (Author author : authors) {
                System.out.printf("| %2d | %40s | %4d |\n", author.getId(),author.getName(),author.getRank());
            }
            System.out.printf("____________________        Authors       ____________________\n");
            System.out.printf("Total %3d authors found.\n", repository.count());

        }

    }
}
