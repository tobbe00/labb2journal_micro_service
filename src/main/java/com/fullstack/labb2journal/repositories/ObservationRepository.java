package com.fullstack.labb2journal.repositories;

import com.fullstack.labb2journal.entitys.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    // Hämta alla observationer för en specifik patient
    List<Observation> findByPatient_PatientId(int patientId);
}
