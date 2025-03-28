package it.biblioteca.main;


import it.biblioteca.dao.BookDAO;
import it.biblioteca.dao.CatalogoItemDAO;
import it.biblioteca.dao.LoanDAO;
import it.biblioteca.dao.UserDAO;
import it.biblioteca.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Biblioteca {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca");
        EntityManager em = emf.createEntityManager();

        CatalogoItemDAO catalogoDAO = new CatalogoItemDAO(em);
        BookDAO bookDAO = new BookDAO(em);
        UserDAO userDAO = new UserDAO(em);
        LoanDAO loanDAO = new LoanDAO(em);

        try {
            // Creazione e salvataggio di alcuni elementi
            Book book1 = new Book("1234567890", "Il Signore degli Anelli", 1954, 1178, "J.R.R. Tolkien", "Fantasy");
            Book book2 = new Book("2345678901", "1984", 1949, 328, "George Orwell", "Distopia");
            Magazine magazine1 = new Magazine("3456789012", "National Geographic", 2023, 100, Periodicity.MENSILE);

            catalogoDAO.save(book1);
            catalogoDAO.save(book2);
            catalogoDAO.save(magazine1);

            // Creazione e salvataggio utenti
            User user1 = new User("Mario", "Rossi", LocalDate.of(1985, 5, 15), "CARD001");
            User user2 = new User("Luigi", "Verdi", LocalDate.of(1990, 8, 22), "CARD002");

            userDAO.save(user1);
            userDAO.save(user2);

            // Creazione prestiti
            Loan loan1 = new Loan(user1, book1, LocalDate.now().minusDays(35));
            Loan loan2 = new Loan(user1, magazine1, LocalDate.now().minusDays(10));
            Loan prestito3 = new Loan(user2, book2, LocalDate.now().minusDays(5));

            em.getTransaction().begin();
            em.persist(loan1);
            em.persist(loan2);
            em.persist(prestito3);
            em.getTransaction().commit();

            // Esempi di ricerca
            System.out.println("\nRicerca per ISBN 1234567890:");
            System.out.println(catalogoDAO.findByIsbn("1234567890"));

            System.out.println("\nRicerca per anno pubblicazione 1954:");
            catalogoDAO.findByPublicationYear(1954).forEach(System.out::println);

            System.out.println("\nRicerca per autore 'Orwell':");
            bookDAO.findByAuthor("Orwell").forEach(System.out::println);

            System.out.println("\nRicerca per titolo 'anelli':");
            catalogoDAO.findByTitle("anelli").forEach(System.out::println);

            System.out.println("\nPrestiti attuali per CARD001:");
            loanDAO.findCurrentLoansByCardNumber("CARD001").forEach(System.out::println);

            System.out.println("\nPrestiti scaduti e non restituiti:");
            loanDAO.findExpiredLoans().forEach(System.out::println);

            // Registro restituzione
            System.out.println("\nRegistrazione restituzione per loan1:");
            loanDAO.registerReturn(loan1, LocalDate.now());
            System.out.println("Dopo la restituzione:");
            System.out.println(loan1);

        } finally {
            em.close();
            emf.close();
        }
    }
}