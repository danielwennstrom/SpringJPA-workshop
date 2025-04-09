package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {
    Details findByEmail(String email);
    Details findByNameContains(String name);
    Details findByNameIgnoreCase(String name);
}
