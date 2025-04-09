package com.example.springjpaworkshop.repository;

import com.example.springjpaworkshop.entity.AppUser;
import com.example.springjpaworkshop.entity.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AppUserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DetailsRepository detailsRepository;
    
    Details details;
    AppUser appUser;

    Details details2;
    AppUser appUser2;

    @BeforeEach
    void setUp() {
        details = new Details();
        details.setName("Sven Svensson");
        details.setEmail("sven.svensson@example.com");

        appUser = new AppUser();
        appUser.setUsername("testuser");
        appUser.setPassword("password123");
        appUser.setRegDate(LocalDate.now());
        appUser.setUserDetails(details);
        
        details2 = new Details();
        details2.setName("Erik Eriksson");
        details2.setEmail("erik.eriksson@example.com");
        
        appUser2 = new AppUser();
        appUser2.setUsername("testuser2");
        appUser2.setPassword("password456");
        appUser2.setRegDate(LocalDate.now());
        appUser2.setUserDetails(details2);

        
        detailsRepository.save(details);
        detailsRepository.save(details2);
        appUserRepository.save(appUser);
        appUserRepository.save(appUser2);
    }
    
    @Test
    void testFindById() {
        Optional<AppUser> savedAppUser = appUserRepository.findById(appUser.getId());
        
        assertTrue(savedAppUser.isPresent());
        assertEquals("testuser", savedAppUser.get().getUsername());
        assertEquals("password123", savedAppUser.get().getPassword());
        assertEquals("Sven Svensson", savedAppUser.get().getUserDetails().getName());
    }
    
    @Test
    void testDelete() {
        appUserRepository.delete(appUser);
        
        Optional<AppUser> deletedAppUser = appUserRepository.findById(appUser.getId());
        
        assertTrue(deletedAppUser.isEmpty());
    }
    
    @Test
    void testUpdate() {
        assertEquals("testuser", appUser.getUsername());
        
        appUser.setUsername("newusername");
        appUserRepository.save(appUser);
        appUser = appUserRepository.findByUsername("newusername");
        
        assertEquals("newusername", appUser.getUsername());
    }
    
    @Test
    void testFindAll() {
        List<AppUser> allAppUsers = (List<AppUser>) appUserRepository.findAll();
        
        assertEquals(2, allAppUsers.size());
        assertNotEquals(1, allAppUsers.size());
    }
    
    @Test
    void testFindByUserDetailsId() {
        List<AppUser> usersByDetailsId = appUserRepository.findByUserDetailsId(appUser.getUserDetails().getId());

        assertEquals(1, usersByDetailsId.size());
        assertEquals(appUser.getId(), usersByDetailsId.getFirst().getId());
        assertNotEquals(appUser.getId(), usersByDetailsId.getFirst().getId() + 1);
    }
    
    @Test
    void testFindByUserDetailsEmail() {
        AppUser userByEmail = appUserRepository.findByUserDetailsEmailIgnoreCase(appUser.getUserDetails().getEmail().toUpperCase());
        assertEquals(appUser.getUsername(), userByEmail.getUsername());
    }
    
    @Test
    void testFindByRegDateBetween() {
        List<AppUser> usersByRegDateBetween = appUserRepository.findByRegDateBetween(LocalDate.of(2023, 1, 1), LocalDate.of(2025, 4, 10));
        
        assertEquals(2, usersByRegDateBetween.size());
        assertNotEquals(1, usersByRegDateBetween.size());
    }
}
