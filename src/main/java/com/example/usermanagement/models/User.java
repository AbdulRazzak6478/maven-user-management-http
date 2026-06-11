package com.example.usermanagement.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String id;
    private String firstName;
    private String lastName;
    private String profession;
    private String education;
    private LocalDate dob;
    private String email;
    private String mobileNumber;
    private Gender gender;
    private Boolean isActive;
    private int salary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(){}

    public String getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getEducation() {
        return this.education;
    }

    public LocalDate getDOB() {
        return this.dob;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Boolean getIsUserActive() {
        return this.isActive;
    }

    public int getSalary() {
        return this.salary;
    }

    public LocalDateTime getOnboardedUserAt() {
        return this.createdAt;
    }
    public LocalDateTime getLastUpdatedAt() {
        return this.updatedAt;
    }


    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setDOB(LocalDate dob) {
        this.dob = dob;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public void setUserActiveStatus(Boolean status) {
        this.isActive = status;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
