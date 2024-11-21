package com.fullstack.labb2journal.dto;

public class ConditionDTO {
    private String conditionName;
    private String conditionDescription;
    private int patientId;
    private int doctorId;

    public ConditionDTO(String conditionName, String conditionDescription, int patientId, int doctorId) {
        this.conditionName = conditionName;
        this.conditionDescription = conditionDescription;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public ConditionDTO() {
        super();
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
