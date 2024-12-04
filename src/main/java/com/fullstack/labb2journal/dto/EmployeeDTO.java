package com.fullstack.labb2journal.dto;

public class EmployeeDTO {
    private int userId;
    private String Name;
    private int age;
    private boolean isDoctor;
    private String speciality;
    private String role;
    private String orginizationName;


    public EmployeeDTO() {

        super();
    }
    public EmployeeDTO(int userId, String name, int age, boolean isDoctor, String speciality, String role, String orginizationName) {
        this.userId = userId;
        Name = name;
        this.age = age;
        this.isDoctor = isDoctor;
        this.speciality = speciality;
        this.role = role;
        this.orginizationName = orginizationName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrginizationName() {
        return orginizationName;
    }

    public void setOrginizationName(String orginizationName) {
        this.orginizationName = orginizationName;
    }
}
