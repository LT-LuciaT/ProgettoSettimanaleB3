package it.biblioteca.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DiscriminatorValue("MAGAZINE")
@Entity
public class Magazine extends CatalogoItem {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(String isbn, String title, int publicationYear, int pageCount, Periodicity periodicity) {
        super(isbn, title, publicationYear, pageCount);
        this.periodicity = periodicity;
    }
}
