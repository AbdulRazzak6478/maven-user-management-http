package com.example.usermanagement.services;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.models.User;
import com.example.usermanagement.repositories.UserRepository;
import com.example.usermanagement.validators.UserValidator;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {
        System.out.println("Inside the service :"+request.firstName);
        
        UserValidator.validate(request);
        System.out.println("after validations");

        User user = new User();

        user.setId(UUID.randomUUID().toString());
        user.setFirstName(request.firstName);
        user.setLastName(request.lastName);
        user.setEmail(request.email);
        user.setMobileNumber(request.mobileNumber);
        user.setProfession(request.profession);
        user.setEducation(request.education);
        user.setDOB(request.dob);
        user.setGender(request.gender);
        user.setSalary(request.salary);
        user.setUserActiveStatus(request.isActive);
        user.setUpdatedAt(LocalDateTime.now());
        user.setCreatedAt(LocalDateTime.now());

        this.userRepository.save(user);

        return user;
    }

    public Collection<User> getAllUsers() {
        return this.userRepository.getAllUsers();
    }

    public User getUserById(String id) {
        return this.userRepository.getUserById(id);
    }

    public void deleteUser(String id) {
        this.userRepository.deleteUser(id);
    }
}
