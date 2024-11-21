package com.fullstack.labb2journal.repositories;

import com.fullstack.labb2journal.entitys.Patient;
import com.fullstack.labb2journal.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Optional<Patient> findByUser(User user); // Använd User-objekt direkt
    boolean existsByUser(User user); // Lägg till exists-metoden

}