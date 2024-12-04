package com.fullstack.labb2journal.dto;

public class WorkerDTO {
    private int userId;
    private int workerId;
    private String name;
    private String role;
    private String organization;
    private int age;
    private String gender;

    public WorkerDTO() {
        super();
    }
    public WorkerDTO(int userId, String name, int workerId, String role, String organization, int age, String gender) {
        this.userId = userId;
        this.name = name;
        this.workerId = workerId;
        this.role = role;
        this.organization = organization;
        this.age = age;
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
