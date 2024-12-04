package com.fullstack.labb2journal.repositories;

import com.fullstack.labb2journal.entitys.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition, Integer> {

    // Hämta alla tillstånd för en specifik patient
    List<Condition> findByPatient_PatientId(int patientId);
}
