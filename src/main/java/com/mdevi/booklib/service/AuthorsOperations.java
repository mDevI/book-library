package com.mdevi.booklib.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorsOperations {
//
//    private final AuthorDAO authorDAO;
//
//    @Autowired
//    public AuthorsOperations(AuthorRepository repository) {
//        this.authorDAO = repository;
//    }
//
//    @Transactional(readOnly = true)
//    public void showAuthors(){
//        List<Author> authors = authorDAO.findAll();
//        if (authors != null && authors.size()>0) {
//            System.out.println("_______________________        Authors       _______________________");
//            System.out.printf("| %2s | %40s | %10s | %4s |\n", "ID", "NAME", "DATE-BIRTH", "RANK");
//            for (Author author : authors) {
//                System.out.printf("| %2d | %40s | %10s | %4d |\n",
//                        author.getId(),author.getName(), author.getDateOfBirth().toString(),author.getRank());
//            }
//            System.out.println("_______________________        Authors       _______________________");
//            System.out.printf("Total %3d authors found.\n", authorDAO.count());
//        }
//    }
//
//    public void addNewAuthor(String name, String dob) {
//        Author newAuthor = new Author();
//        newAuthor.setName(name);
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            java.util.Date date = sdf1.parse(dob);
//            java.sql.Date sqlDateOfBirth = new java.sql.Date(date.getTime());
//            newAuthor.setDateOfBirth(sqlDateOfBirth);
//        } catch (ParseException ex) {
//            ex.printStackTrace();
//        }
//        //System.out.println(newAuthor.toString());
//        Integer idCreated = authorDAO.insert(newAuthor);
//        if (idCreated != null) {
//            System.out.println("A new author info has been saved successfully.");
//            System.out.println(authorDAO.findById(idCreated).toString());
//        } else {
//            System.out.println("An author info hasn't been saved.");
//        }
//    }
//
//    public void deleteAuthor(Integer id) {
//        authorDAO.deleteById(id);
//    }
//
//    public void updateAuthorByID(Integer id) {
//        Author selectedAuthor = authorDAO.findById(id);
//        if (selectedAuthor != null) {
//
//            System.out.println("Please enter new author's info step by step.");
//            final Scanner sc = new Scanner(System.in);
//            System.out.print("Author's name: (" + selectedAuthor.getName() + "): ");
//            String newNameAuthor = sc.nextLine();
//            if (!newNameAuthor.isEmpty() && !newNameAuthor.equals(selectedAuthor.getName())) selectedAuthor.setName(newNameAuthor);
//
//            System.out.printf("Author's date of birth: (%10s): ", selectedAuthor.getDateOfBirth().toString());
//            String newAuthorBirthDate = sc.nextLine();
//            if (!newAuthorBirthDate.equals(selectedAuthor.getDateOfBirth().toString()) && !newAuthorBirthDate.isEmpty())
//                selectedAuthor.setDateOfBirth(Date.valueOf(newAuthorBirthDate));
//
//            System.out.printf("Author's rank: (%2d): ", selectedAuthor.getRank());
//            Integer newAuthorRank = sc.nextInt();
//            if (!newNameAuthor.equals(selectedAuthor.getName())) selectedAuthor.setRank(newAuthorRank);
//
//            System.out.println("Modified author's info is: " + selectedAuthor.toString());
//            System.out.println();
//            System.out.print("\nAre you sure to save it? (Y)es or (N)o : ");
//            String answerToModify =  sc.next();
//            if (answerToModify.toUpperCase().equals("Y")){
//                authorDAO.update(selectedAuthor);
//                System.out.println("The author's info has been updated.");
//            }
//        }
//    }
}
