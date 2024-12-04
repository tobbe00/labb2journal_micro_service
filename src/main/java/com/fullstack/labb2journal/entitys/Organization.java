package com.fullstack.labb2journal.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "T_organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orgId;

    @Column(name = "orgName", nullable = false, length = 100)
    private String orgName;

    // Constructors
    public Organization() {}

    public Organization(String orgName) {
        this.orgName = orgName;
    }

    // Getters and Setters
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
