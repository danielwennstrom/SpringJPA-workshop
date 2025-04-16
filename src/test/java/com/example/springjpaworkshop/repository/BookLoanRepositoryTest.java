package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.AppUser;
import com.example.springjpaworkshop.entity.Book;
import com.example.springjpaworkshop.entity.BookLoan;
import com.example.springjpaworkshop.entity.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookLoanRepositoryTest {
    @Autowired
    BookLoanRepository bookLoanRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DetailsRepository detailsRepository;
    
    Details details;
    AppUser appUser;
    
    Book book;
    Book book2;
    
    @BeforeEach
    public void setUp() {
        details = new Details();
        details.setName("Sven Svensson");
        details.setEmail("sven.svensson@example.com");
        detailsRepository.save(details);

        appUser = new AppUser();
        appUser.setUsername("testuser");
        appUser.setPassword("password123");
        appUser.setRegDate(LocalDate.now());
        appUser.setUserDetails(details);
        appUserRepository.save(appUser);
        
        book = new Book();
        book.setTitle("Book 1");
        book.setMaxLoanDays(30);

        book2 = new Book();
        book2.setTitle("Book 2");
        book2.setMaxLoanDays(60);
        
        bookRepository.save(book);
        bookRepository.save(book2);
        
        BookLoan bookLoan = BookLoan.builder()
                .book(book)
                .borrower(appUser)
                .loanDate(LocalDate.of(2025, 4, 1))
                .dueDate(LocalDate.of(2025, 4, 25))
                .returned(true)
                .build();

        BookLoan bookLoan2 = BookLoan.builder()
                .book(book2)
                .borrower(appUser)
                .loanDate(LocalDate.of(2025, 5, 1))
                .dueDate(LocalDate.of(2025, 6, 25))
                .build();
        
        bookLoanRepository.save(bookLoan);
        bookLoanRepository.save(bookLoan2);
    }
    
    @Test
    public void testFindByBorrower_Id() {
        int id = appUser.getId();
        List<BookLoan> bookLoans = bookLoanRepository.findByBorrower_Id(id);
        assertEquals(2, bookLoans.size());
    }
    
    @Test
    public void testFindByBook_Id() {
        int id = book.getId();
        List<BookLoan> bookLoans = bookLoanRepository.findByBook_Id(id);
        assertEquals(1, bookLoans.size());
    }
    
    @Test
    public void testFindByReturnedIsFalse() {
        List<BookLoan> bookLoans = bookLoanRepository.findByReturnedIsFalse();
        assertEquals(1, bookLoans.size());
    }
    
    @Test
    public void testFindByDueDateBefore() {
        LocalDate dueDate = LocalDate.of(2025, 4, 30);
        List<BookLoan> bookLoans = bookLoanRepository.findByDueDateBefore(dueDate);
        assertEquals(1, bookLoans.size());
    }
    
    @Test
    public void testFindByLoanDateBetween() {
        LocalDate loanStartDate = LocalDate.of(2025, 4, 1);
        LocalDate loanEndDate = LocalDate.of(2025, 4, 30);
        List<BookLoan> bookLoans = bookLoanRepository.findByLoanDateBetween(loanStartDate, loanEndDate);
        assertEquals(1, bookLoans.size());
    }
}
