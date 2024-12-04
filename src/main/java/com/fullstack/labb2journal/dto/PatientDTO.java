package com.fullstack.labb2journal.dto;

public class PatientDTO {
    private int userId;
    private int patientId;
    private String name;
    private String gender;
    private int age;

    public PatientDTO(int userId, int patientId, String name, String gender, int age) {
        this.userId = userId;
        this.patientId = patientId;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public PatientDTO() {
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
