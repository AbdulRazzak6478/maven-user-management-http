package com.example.usermanagement.dto;

import java.time.LocalDate;

import com.example.usermanagement.models.Address;
import com.example.usermanagement.models.Gender;

public class CreateUserRequest {
    public String firstName;

    public String lastName;

    public String email;

    public String mobileNumber;
    public String profession;
    public String education;

    public int salary;

    public boolean isActive;

    public Gender gender;

    public LocalDate dob;

    public Address address;
}
