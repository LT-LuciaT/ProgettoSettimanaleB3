package it.biblioteca.dao;

import it.biblioteca.entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BookDAO {
    private final EntityManager em;

    public BookDAO(EntityManager em) {
        this.em = em;
    }

    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.author) LIKE LOWER(:author)", Book.class);
        query.setParameter("author", "%" + author + "%");
        return query.getResultList();
    }
}

