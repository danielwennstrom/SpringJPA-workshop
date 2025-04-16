package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Author;
import com.example.springjpaworkshop.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    
    Book book;
    Book book2;
    
    @BeforeEach
    void setUp() {
        book = new Book();
        book.setTitle("Book 1");
        book.setMaxLoanDays(30);

        book2 = new Book();
        book2.setTitle("Book 2");
        book2.setMaxLoanDays(30);
        
        bookRepository.save(book);
        bookRepository.save(book2);
        
        Author author = Author.builder()
                .firstName("Sven")
                .lastName("Svensson")
                .writtenBooks(new HashSet<>(Arrays.asList(book, book2)))
                .build();
        
        authorRepository.save(author);
    }
    
    @Test
    public void testFindAll() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        assertEquals(1, authors.size());
        assertEquals("Sven", authors.getFirst().getFirstName());
        assertEquals("Svensson", authors.getFirst().getLastName());
    }
    
    @Test
    public void testFindByFirstName() {
        List<Author> authors = authorRepository.findAuthorByFirstName("Sven");
        assertEquals(1, authors.size());
        assertEquals("Sven", authors.getFirst().getFirstName());

        List<Author> authorsEmpty= authorRepository.findAuthorByFirstName("Erik");
        assertEquals(0, authorsEmpty.size());
    }

    @Test
    public void testFindByLastName() {
        List<Author> authors = authorRepository.findAuthorByLastName("Svensson");
        assertEquals(1, authors.size());
        assertEquals("Svensson", authors.getFirst().getLastName());

        List<Author> authorsEmpty = authorRepository.findAuthorByFirstName("Eriksson");
        assertEquals(0, authorsEmpty.size());
    }
    
    @Test
    public void testFindAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase() {
        List<Author> authors = authorRepository.findAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("Svens");
        assertEquals(1, authors.size());

        List<Author> authorsEmpty = authorRepository.findAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("Eriks");
        assertEquals(0, authorsEmpty.size());
    }
    
    @Test
    public void testFindAuthorByBook_Id() {
        List<Author> authors = authorRepository.findAuthorByBook_Id(book.getId());
        assertEquals(1, authors.size());
    }
    
    @Test
    public void testUpdateAuthorName() {
        Author author = authorRepository.findAuthorByFirstName("Sven").getFirst();
        assertEquals("Sven", author.getFirstName());
        
        author.setFirstName("Greger");
        authorRepository.save(author);
        
        Author updatedAuthor = authorRepository.findAuthorByFirstName("Greger").getFirst();
        assertEquals("Greger", updatedAuthor.getFirstName());
    }
}
