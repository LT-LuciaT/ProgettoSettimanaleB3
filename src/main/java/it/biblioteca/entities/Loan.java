package it.biblioteca.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Item_id")
    private CatalogoItem borrowedItem;

    private LocalDate startDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    public Loan(User user, CatalogoItem borrowedItem, LocalDate startDate) {
        this.user = user;
        this.borrowedItem = borrowedItem;
        this.startDate = startDate;
        this.expectedReturnDate = startDate.plusDays(30);
    }
}