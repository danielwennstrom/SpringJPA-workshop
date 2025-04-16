package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {
    List<Author> findAuthorByFirstName(String firstName);
    List<Author> findAuthorByLastName(String lastName);
    @Query("SELECT a FROM Author a WHERE a.firstName LIKE %:keyWord% OR a.lastName LIKE %:keyWord%")
    List<Author> findAuthorByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param("keyWord") String keyWord);
    @Query("SELECT a FROM Author a JOIN a.writtenBooks b WHERE b.id = :bookId")
    Author findAuthorByBook_Id(@Param("bookId") int bookId);
    @Modifying
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName WHERE a.authorId = :authorId")
    void updateAuthorName(@Param("authorId") int authorId, @Param("firstName") String firstName, @Param("lastName") String lastName);
    @Modifying
    @Query("DELETE FROM Author a WHERE a.authorId = :authorId")
    void deleteAuthorById(@Param("authorId") int authorId);
}
