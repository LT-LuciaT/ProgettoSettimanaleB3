package it.biblioteca.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_isbn")
    private CatalogoItem borrowedItem;

    private LocalDate startDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    @PrePersist
    protected void onCreate() {
        if (startDate != null && expectedReturnDate == null) {
            expectedReturnDate = startDate.plusDays(30);
        }
    }
}