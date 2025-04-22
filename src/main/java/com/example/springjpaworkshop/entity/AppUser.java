package com.example.springjpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private LocalDate regDate;
    @OneToOne
    @JoinColumn(name = "details_id")
    private Details userDetails;
    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookLoan> bookLoans = new ArrayList<>();
    
    public void addBookLoan(BookLoan bookLoan) {
        this.bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }
    
    public void removeBookLoan(BookLoan bookLoan) {
        this.bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
    }
}
