package com.example.usermanagement.validators;

import java.util.regex.Pattern;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.exceptions.ValidationException;

public class UserValidator {
    public static void validate(CreateUserRequest request) {
        System.out.println("Request from validator : " + request);
        if (request == null) {
            throw new ValidationException("Request body is required");
        }
        System.out.println("before firstName");
        if (request.firstName == null ||
                request.firstName.length() < 3 ||
                request.firstName.length() > 30) {

            throw new ValidationException(
                    "First name should be between 3 and 30 chars");
        }

        System.out.println("before email + " + request.email);
        if (request.email == null ||
                !Pattern.matches(
                        "^[A-Za-z0-9_]+(?:\\.[A-Za-z0-9_]+)*@[A-Za-z0-9]+(?:\\.[A-Za-z0-9]+)+$",
                        request.email)) {

            throw new ValidationException("Invalid email");
        }

        System.out.println("before mobileNumber "+request.mobileNumber);
        if (request.mobileNumber == null || !Pattern.matches("^[0-9]{10}$", request.mobileNumber)) {
            throw new ValidationException("Invalid phone");
        }

        System.out.println("before dob");
        if (request.dob == null || request.dob.isAfter(java.time.LocalDate.now())) {
            throw new ValidationException("Invalid dob");
        }

        System.out.println("before salary");
        if (request.salary < 0) {
            throw new ValidationException("Invalid salary");
        }

        System.out.println("before address");

        if (request.address == null) {
            throw new ValidationException("Address required");
        }
    }
}
