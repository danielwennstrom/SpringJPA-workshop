package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;
    
    Book book;
    Book book2;

    @BeforeEach
    public void setUp() {
        book = new Book();
        book.setTitle("Book 1");
        book.setMaxLoanDays(30);
        
        book2 = new Book();
        book2.setTitle("Book 2");
        book2.setMaxLoanDays(60);
        
        bookRepository.save(book);
        bookRepository.save(book2);
    }
    
    @Test
    public void testFindAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        List<Book> bookList = (List<Book>) books;
        assertEquals(2, bookList.size());
    }
    
    @Test
    public void testUpdateBook() {
        book.setTitle("Updated Book 1");
        bookRepository.save(book);
        
        book = bookRepository.findByTitleContains("Updated Book 1").getFirst();
        assertEquals("Updated Book 1", book.getTitle());
    }
    
    @Test
    public void testDeleteBook() {
        bookRepository.delete(book);
        Optional<Book> getBook = bookRepository.findById(1);
        assertTrue(getBook.isEmpty());
    }
    
    @Test
    public void testFindByTitle() {
        Book foundBook = bookRepository.findByTitleContains("Book 1").getFirst();
        assertNotNull(foundBook);
        assertEquals("Book 1", foundBook.getTitle());
    }
    
    @Test
    public void testMaxLoanDaysLessThan() {
        List<Book> books = bookRepository.findByMaxLoanDaysLessThan(35);
        assertEquals(1, books.size());
    }
}
