package com.example.springjpaworkshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Book {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @GeneratedValue(strategy = GenerationType.UUID)
    private String isbn;
    private String title;
    private int maxLoanDays;
}
