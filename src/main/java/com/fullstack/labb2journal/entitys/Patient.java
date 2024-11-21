package com.fullstack.labb2journal.entitys;

import jakarta.persistence.*;

import jakarta.persistence.*;

@Entity
@Table(name = "T_patient")
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", unique = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_patient_user"))
    private User user;

    @Column(name = "age")
    private Integer age;



    // Constructors
    public Patient() {}

    public Patient(User user) {
        this.user = user;

    }

    // Getters and Setters
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
