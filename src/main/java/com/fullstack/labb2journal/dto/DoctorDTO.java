package com.fullstack.labb2journal.dto;

public class DoctorDTO {
    private int userId;
    private int doctorid;
    private String name;
    private String speciality;
    private String organization;
    private int age;
    private String gender;


    public DoctorDTO() {

        super();
    }
    public DoctorDTO(int userId, int doctorid, String name, String speciality, String organization, int age, String gender) {
        this.userId = userId;
        this.doctorid = doctorid;
        this.name = name;
        this.speciality = speciality;
        this.organization = organization;
        this.age = age;
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userid) {
        this.userId = userid;
    }

    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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
