package com.fullstack.labb2journal.entitys;
import jakarta.persistence.*;
import com.fullstack.labb2journal.GenderConverter;


@Entity
@Table(name = "T_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "age")
    private Integer age;
    @Convert(converter = GenderConverter.class)  // Apply the converter here
    @Column(name = "gender")
    private User.Gender gender;

    // Constructors
    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters and Setters


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Inner enum for Gender
    public enum Gender {
        MALE, FEMALE, OTHER
    }
}