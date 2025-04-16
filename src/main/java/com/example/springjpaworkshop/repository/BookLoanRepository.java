package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.BookLoan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {
    List<BookLoan> findByBorrower_Id(int borrowerId);
    List<BookLoan> findByBook_Id(int bookId);
    List<BookLoan> findByReturnedIsFalse();
    List<BookLoan> findByDueDateBefore(LocalDate dueDate);
    List<BookLoan> findByLoanDateBetween(LocalDate loanDateAfter, LocalDate loanDateBefore);
    @Modifying
    @Query("UPDATE BookLoan b SET b.returned = true WHERE b.id = :id")
    BookLoan markAsReturned(@Param("id") Integer id);
}
