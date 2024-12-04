package com.fullstack.labb2journal.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "T_condition")
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer conditionId;

    @Column(name = "conditionName", nullable = false, length = 100)
    private String conditionName;

    @Column(name = "conditionDescription", columnDefinition = "TEXT")
    private String conditionDescription;

    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "patientId", nullable = false,
            foreignKey = @ForeignKey(name = "fk_condition_patient"))
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId",
            foreignKey = @ForeignKey(name = "fk_condition_doctor"))
    private Doctor doctor;

    // Constructors
    public Condition() {}

    public Condition(String conditionName, String conditionDescription, Patient patient, Doctor doctor) {
        this.conditionName = conditionName;
        this.conditionDescription = conditionDescription;
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getters and Setters
    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(String conditionDescription) {
        this.conditionDescription = conditionDescription;
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
