package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.AuthorDAO;
import com.mdevi.booklib.dao.repository.AuthorRepository;
import com.mdevi.booklib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

@ShellComponent
@Service
public class AuthorsOperations {

    private final AuthorDAO repositoryAuthors;

    @Autowired
    public AuthorsOperations(AuthorRepository repository) {
        this.repositoryAuthors = repository;
    }

    @ShellMethod(value = "Show all books authors info.", key = "get-all-authors")
    public void showAuthors(){
        List<Author> authors = repositoryAuthors.findAll();
        if (authors != null && authors.size()>0) {
            System.out.println("_______________________        Authors       _______________________");
            System.out.printf("| %2s | %40s | %10s | %4s |\n", "ID", "NAME", "DATE-BIRTH", "RANK");
            for (Author author : authors) {
                System.out.printf("| %2d | %40s | %10s | %4d |\n",
                        author.getId(),author.getName(), author.getDateOfBirth().toString(),author.getRank());
            }
            System.out.println("_______________________        Authors       _______________________");
            System.out.printf("Total %3d authors found.\n", repositoryAuthors.count());

        }
    }

    @ShellMethod(value = "Add new author info.", key = "add-new-author")
    public void addNewAuthor(
            @ShellOption(value = "--name", help = "--name \"author-full-name\"", arity = 1) @NotNull String name,
            @ShellOption(value = "--dateBirth", help = "--dateBirth \"dd-MM-yyyy\"", arity = 1) @NotNull String dob) {
        Author newAuthor = new Author();
        newAuthor.setName(name);

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date date = sdf1.parse(dob);
            java.sql.Date sqlDateOfBirth = new java.sql.Date(date.getTime());
            newAuthor.setDateOfBirth(sqlDateOfBirth);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        //newAuthor.setRank(1);
        System.out.println(newAuthor.toString());
        Integer idCreated =  repositoryAuthors.insert(newAuthor);
        if (idCreated != null) {
            System.out.println("A new author info has been saved successfully.");
            System.out.println(repositoryAuthors.findById(idCreated).toString());
        } else {
            System.out.println("An author info hasn't been saved.");
        }
    }
    @ShellMethod(value = "Delete the selected author by it's ID.", key = "delete-by-id")
    public void deleteAuthor(
            @ShellOption(value = "--id", help = "--id number", arity = 1) Integer id) {
        repositoryAuthors.deleteById(id);
    }

    @ShellMethod(value = "Update author's info.", key = "update-author-info")
    public void updateAuthorByID(@ShellOption Integer id){
        Author selectedAuthor = repositoryAuthors.findById(id);
        if (selectedAuthor != null) {

            System.out.println("Please enter new author's info step by step.");
            final Scanner sc = new Scanner(System.in);
            System.out.print("Author's name: (" + selectedAuthor.getName() + "): ");
            String newNameAuthor = sc.nextLine();
            if (!newNameAuthor.isEmpty() && !newNameAuthor.equals(selectedAuthor.getName())) selectedAuthor.setName(newNameAuthor);

            System.out.printf("Author's date of birth: (%10s): ", selectedAuthor.getDateOfBirth().toString());
            String newAuthorBirthDate = sc.nextLine();
            if (!newAuthorBirthDate.equals(selectedAuthor.getDateOfBirth().toString()) && !newAuthorBirthDate.isEmpty())
                selectedAuthor.setDateOfBirth(Date.valueOf(newAuthorBirthDate));

            System.out.printf("Author's rank: (%2d): ", selectedAuthor.getRank());
            Integer newAuthorRank = sc.nextInt();
            if (!newNameAuthor.equals(selectedAuthor.getName())) selectedAuthor.setRank(newAuthorRank);

            System.out.println("Modified author's info is: " + selectedAuthor.toString());
            System.out.println();
            System.out.print("\nAre you sure to save it? (Y)es or (N)o : ");
            String answerToModify =  sc.next();
            if (answerToModify.toUpperCase().equals("Y")){
                repositoryAuthors.update(selectedAuthor);
                System.out.println("The author's info has been updated.");
            }
        }
    }
}
