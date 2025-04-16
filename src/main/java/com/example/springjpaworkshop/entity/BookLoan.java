package com.example.springjpaworkshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;
    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private AppUser borrower;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}