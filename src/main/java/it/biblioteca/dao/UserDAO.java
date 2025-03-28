package it.biblioteca.dao;


import it.biblioteca.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void save(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println("Utente salvato correttamente");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio dell'utente: " + e.getMessage());
        }
    }

    public User findByCardNumber(String cardNumber) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.cardNumber = :cardNumber", User.class);
        query.setParameter("cardNumber", cardNumber);
        return query.getSingleResult();
    }
}