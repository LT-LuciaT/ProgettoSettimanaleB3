package it.biblioteca.dao;


import it.biblioteca.entities.Loan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


import java.time.LocalDate;
import java.util.List;

public class LoanDAO {
    private final EntityManager em;

    public LoanDAO(EntityManager em) {
        this.em = em;
    }

    public List<Loan> findCurrentLoansByCardNumber(String cardNumber) {
        TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan l WHERE l.user.cardNumber = :cardNumber AND l.actualReturnDate IS NULL",
                Loan.class);
        query.setParameter("cardNumber", cardNumber);
        return query.getResultList();
    }

    public List<Loan> findExpiredLoans() {
        TypedQuery<Loan> query = em.createQuery(
                "SELECT l FROM Loan l WHERE l.expectedReturnDate < CURRENT_DATE AND l.actualReturnDate IS NULL",
                Loan.class);
        return query.getResultList();
    }

    public void registerReturn(Loan loan, LocalDate returnDate) {
        loan.setActualReturnDate(returnDate);
        em.merge(loan);
    }
}