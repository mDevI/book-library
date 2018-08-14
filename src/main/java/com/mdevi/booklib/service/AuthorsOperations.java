package com.mdevi.booklib.service;

import com.mdevi.booklib.dao.repository.AuthorRepository;
import com.mdevi.booklib.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@Transactional
public class AuthorsOperations {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorsOperations(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Transactional(readOnly = true)
    public void showAuthors() {
        List<Author> authors = authorRepository.findAll();
        System.out.println("Authors size = " + authors.size());
        if (authors.size() > 0) {
            System.out.println("_______________________        Authors       _______________________");
            System.out.printf("| %2s | %40s | %10s | %4s |\n", "ID", "NAME", "DATE-BIRTH", "RANK");
            for (Author author : authors) {
                System.out.printf("| %2d | %40s | %10s | %4d |\n",
                        author.getId(), author.getName(), author.getDateOfBirth().toString(), author.getRank());
            }
            System.out.println("_______________________        Authors       _______________________");
            System.out.printf("Total %3d authors found.\n", authorRepository.count());
        }
    }

    @Transactional(readOnly = true)
    public void findById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            Author theAuthor = author.get();
            System.out.println(theAuthor.toString());
        } else {
            System.out.println("Author with ID= " + id + " is not found.");
        }
    }

    public void addNewAuthor(String name, String dob) {
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
        //System.out.println(newAuthor.toString());

        int idCreated = (authorRepository.save(newAuthor)).getId();
        if (idCreated != 0) {
            System.out.println("A new author info has been saved successfully.");
            System.out.println(newAuthor.toString());
        } else {
            System.out.println("An author info hasn't been saved.");
        }
    }

    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    public void updateAuthorByID(Integer id) {
        Optional<Author> selectedAuthor = authorRepository.findById(id);
        if (selectedAuthor.isPresent()) {

            System.out.println("Please enter new author's info step by step.");
            final Scanner sc = new Scanner(System.in);
            System.out.print("Author's name: (" + selectedAuthor.get().getName() + "): ");
            String newNameAuthor = sc.nextLine();
            if (!newNameAuthor.isEmpty() && !newNameAuthor.equals(selectedAuthor.get().getName()))
                selectedAuthor.get().setName(newNameAuthor);

            System.out.printf("Author's date of birth: (%10s): ", selectedAuthor.get().getDateOfBirth().toString());
            String newAuthorBirthDate = sc.nextLine();
            if (!newAuthorBirthDate.equals(selectedAuthor.get().getDateOfBirth().toString()) && !newAuthorBirthDate.isEmpty())
                selectedAuthor.get().setDateOfBirth(Date.valueOf(newAuthorBirthDate));

            System.out.printf("Author's rank: (%2d): ", selectedAuthor.get().getRank());
            int newAuthorRank = sc.nextInt();
            if (!newNameAuthor.equals(selectedAuthor.get().getName())) selectedAuthor.get().setRank(newAuthorRank);

            System.out.println("Modified author's info is: " + selectedAuthor.get().toString());
            System.out.println();
            System.out.print("\nAre you sure to save it? (Y)es or (N)o : ");
            String answerToModify = sc.next();
            if (answerToModify.toUpperCase().equals("Y")) {
                authorRepository.save(selectedAuthor.get());
                System.out.println("The author info has been updated.");
            }
        }
    }
}
