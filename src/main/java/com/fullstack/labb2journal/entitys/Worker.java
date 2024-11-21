package com.fullstack.labb2journal.entitys;

import jakarta.persistence.*;

@Entity
@Table(name = "T_worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workerId;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", unique = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_worker_user"))
    private User user;

    @Column(name = "role", length = 100)
    private String role;

    @ManyToOne
    @JoinColumn(name = "orgId", referencedColumnName = "orgId",
            foreignKey = @ForeignKey(name = "fk_worker_organization"))
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId",
            foreignKey = @ForeignKey(name = "fk_worker_location"))
    private Location location;

    // Constructors
    public Worker() {}

    public Worker(User user, String name, String role, Organization organization, Location location) {
        this.user = user;
        this.role = role;
        this.organization = organization;
        this.location = location;
    }

    // Getters and Setters
    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
