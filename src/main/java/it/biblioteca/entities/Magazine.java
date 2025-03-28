package it.biblioteca.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "magazines")
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)

public class Magazine extends CatalogoItem {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(String isbn, String title, int publicationYear, int pageCount, Periodicity periodicity) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setPublicationYear(publicationYear);
        this.setPageCount(pageCount);
        this.periodicity = periodicity;
    }
}
