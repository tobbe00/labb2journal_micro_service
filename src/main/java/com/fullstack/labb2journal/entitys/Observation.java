package com.fullstack.labb2journal.entitys;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="T_observation")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "observation_id")  // Använd kolumnnamnet från databasen
    private Long observationId;

    @Column
    private String description;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "patient_id")  // Foreign key for Patient
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")  // Foreign key for Doctor
    private Doctor doctor;

    // Getters and Setters

    public Long getObservationId() {
        return observationId;
    }

    public void setObservationId(Long observationId) {
        this.observationId = observationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
