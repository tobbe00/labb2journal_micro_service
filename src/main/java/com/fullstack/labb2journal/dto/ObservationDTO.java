package com.fullstack.labb2journal.dto;

import java.time.LocalDate;

public class ObservationDTO {
    private String description;
    private LocalDate date;
    private int patientId;
    private int doctorId;

    public ObservationDTO(String description, LocalDate date, int patientId, int doctorId) {
        this.description = description;
        this.date = date;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public ObservationDTO() {
        super();
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

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
