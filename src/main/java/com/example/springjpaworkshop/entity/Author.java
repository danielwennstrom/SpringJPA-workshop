package com.example.springjpaworkshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String firstName;
    private String lastName;
    @ManyToMany
    @Singular
    private Set<Book> writtenBooks;
}
