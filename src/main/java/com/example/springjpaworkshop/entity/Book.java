package com.example.springjpaworkshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String isbn;
    private String title;
    private int maxLoanDays;
    private boolean available = true;
    
    @PrePersist
    public void prePersist() {
        if (this.isbn == null) {
            this.isbn = "ISBN" + this.id;
        }
    }
}
