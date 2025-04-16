package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    Book findByIsbnIgnoreCase(String isbn);
    List<Book> findByTitleContains(String title);
    List<Book> findByMaxLoanDaysLessThan(int maxLoanDaysIsLessThan);
}
