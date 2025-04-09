package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DetailsRepositoryTest {
    @Autowired
    DetailsRepository detailsRepository;
    
    Details details;
    Details details2;
    
    @BeforeEach
    public void setUp() {
        details = new Details();
        details.setName("Sven Svensson");
        details.setEmail("sven.svensson@example.com");

        details2 = new Details();
        details2.setName("Erik Eriksson");
        details2.setEmail("erik.eriksson@example.com");
        
        detailsRepository.save(details);
        detailsRepository.save(details2);
    }
    
    @Test
    void testFindById() {
        Details savedDetails = detailsRepository.findById(details.getId()).orElse(null);
        assertNotNull(savedDetails);
        assertEquals("Sven Svensson", savedDetails.getName());
        assertEquals("sven.svensson@example.com", savedDetails.getEmail());
    }
    
    @Test
    void testDelete() {
        detailsRepository.delete(details);
        Details foundDetails = detailsRepository.findById(details.getId()).orElse(null);
        assertNull(foundDetails);
    }
    
    @Test
    void testUpdate() {
        details.setName("Erik Göransson");
        detailsRepository.save(details);
        Details updatedDetails = detailsRepository.findById(details.getId()).orElse(null);
        assertNotNull(updatedDetails);
        assertEquals("Erik Göransson", updatedDetails.getName());
    }
    
    @Test
    void testFindByNameContains() {
        List<Details> foundDetails = detailsRepository.findByNameContains("Sven");
        assertEquals(1, foundDetails.size());
    }

    @Test
    void testFindByNameIgnoreCase() {
        List<Details> foundDetails = detailsRepository.findByNameIgnoreCase("ERIK Eriksson");
        assertEquals(1, foundDetails.size());
    }
    
    @Test
    void testFindByEmail() {
        Details savedDetails = detailsRepository.findByEmail("sven.svensson@example.com");
        assertNotNull(savedDetails);
    }
}
