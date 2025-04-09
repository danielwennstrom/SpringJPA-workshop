package com.example.springjpaworkshop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.springjpaworkshop.entity.AppUser;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
    List<AppUser> findByRegDateBetween(LocalDate regDateAfter, LocalDate regDateBefore);
    List<AppUser> findByDetailsId(Integer id);
    AppUser findByDetailsEmailIgnoreCase(String email);
}
