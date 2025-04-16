package com.example.springjpaworkshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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