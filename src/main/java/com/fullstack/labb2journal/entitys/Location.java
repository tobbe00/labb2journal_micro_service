package com.fullstack.labb2journal.entitys;

import jakarta.persistence.*;
//gpt
@Entity
@Table(name = "T_location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(name = "address", nullable = false, length = 255)
    private String address;


    // Constructors
    public Location() {}

    public Location(String address) {
        this.address = address;

    }

    // Getters and Setters
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
