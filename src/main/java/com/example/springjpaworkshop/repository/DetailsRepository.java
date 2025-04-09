package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {
    Details findByEmail(String email);
    List<Details> findByNameContains(String name);
    List<Details> findByNameIgnoreCase(String name);
}
