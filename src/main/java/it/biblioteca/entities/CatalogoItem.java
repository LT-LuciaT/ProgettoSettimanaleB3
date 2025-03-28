package it.biblioteca.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "catalogo_item")
@Getter
@Setter
@NoArgsConstructor
@ToString

public abstract class CatalogoItem {
    @Id
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    private String title;
    private int publicationYear;
    private int pageCount;

    @OneToMany(mappedBy = "borrowedItem", cascade = CascadeType.ALL)
    private List<Loan> loans;
}