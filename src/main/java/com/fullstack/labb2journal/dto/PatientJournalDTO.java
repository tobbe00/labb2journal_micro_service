package com.fullstack.labb2journal.dto;


import com.fullstack.labb2journal.entitys.User;

import java.util.List;

public class PatientJournalDTO {
    private String name;
    private Integer age;
    private User.Gender gender;
    private List<ConditionDTO> conditions;  // Lägger till conditions
    private List<ObservationDTO> observations;  // Lägger till observations

    // Konstruktor
    public PatientJournalDTO(String name, Integer age, User.Gender gender, List<ConditionDTO> conditions, List<ObservationDTO> observations) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.conditions = conditions;
        this.observations = observations;
    }


    // Getters och setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User.Gender getGender() {
        return gender;
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
    }

    public List<ConditionDTO> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionDTO> conditions) {
        this.conditions = conditions;
    }

    public List<ObservationDTO> getObservations() {
        return observations;
    }

    public void setObservations(List<ObservationDTO> observations) {
        this.observations = observations;
    }
}
