package it.biblioteca.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CatalogoItem {
    @Id
    private String isbn;
    private String title;
    private int publicationYear;
    private int pageCount;

    @OneToMany(mappedBy = "borrowedItem", cascade = CascadeType.ALL)
    private List<Prestito> prestiti;
}