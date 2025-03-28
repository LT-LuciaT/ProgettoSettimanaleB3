package it.biblioteca.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Book extends CatalogoItem {
    private String author;
    private String genre;

    public Book(String isbn, String title, int publicationYear, int pageCount, String author, String genre) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setPublicationYear(publicationYear);
        this.setPageCount(pageCount);
        this.author = author;
        this.genre = genre;
    }
}
