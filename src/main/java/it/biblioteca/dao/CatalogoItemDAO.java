package it.biblioteca.dao;


import it.biblioteca.entities.CatalogoItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CatalogoItemDAO {
    private final EntityManager em;

    public CatalogoItemDAO(EntityManager em) {
        this.em = em;
    }

    public void save(CatalogoItem element) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(element);
            transaction.commit();
            System.out.println("Elemento salvato correttamente");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante il salvataggio dell'elemento: " + e.getMessage());
        }
    }

    public void deleteByIsbn(String isbn) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            CatalogoItem element = em.find(CatalogoItem.class, isbn);
            if (element != null) {
                em.remove(element);
                transaction.commit();
                System.out.println("Elemento rimosso correttamente");
            } else {
                System.out.println("Elemento non trovato con ISBN: " + isbn);
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Errore durante la rimozione dell'elemento: " + e.getMessage());
        }
    }

    public CatalogoItem findByIsbn(String isbn) {
        return em.find(CatalogoItem.class, isbn);
    }

    public List<CatalogoItem> findByPublicationYear(int year) {
        TypedQuery<CatalogoItem> query = em.createQuery(
                "SELECT e FROM CatalogoItem e WHERE e.publicationYear = :year", CatalogoItem.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    public List<CatalogoItem> findByTitle(String title) {
        TypedQuery<CatalogoItem> query = em.createQuery(
                "SELECT i FROM CatalogoItem i WHERE LOWER(i.title) LIKE LOWER(:title)", CatalogoItem.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
}