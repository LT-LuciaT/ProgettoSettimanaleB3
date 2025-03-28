package it.biblioteca.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private LocalDate birthDate;
    private String cardNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Loan> loans;

    public User(String name, String surname, LocalDate birthDate, String cardNumber) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.cardNumber = cardNumber;
    }
}