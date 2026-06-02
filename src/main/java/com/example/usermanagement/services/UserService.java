package com.example.usermanagement.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.models.DBconnection;
import com.example.usermanagement.models.User;
import com.example.usermanagement.repositories.UserRepository;
import com.example.usermanagement.validators.UserValidator;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequest request) {
        System.out.println("Inside the service :" + request.firstName);

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

        String sqlCreateUser = """
                                INSERT INTO users (

                    id,
                    first_name,
                    last_name,
                    profession,
                    education,
                    dob,
                    email,
                    mobile_number,
                    gender,
                    is_active,
                    salary,
                    created_at,
                    updated_at

                ) VALUES (

                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                                """;

        try (
                Connection conn = DBconnection.getConnection();
                PreparedStatement preStmt = conn.prepareStatement(sqlCreateUser);) {
            System.out.println("Connection String : " + conn + user.getId());
            preStmt.setString(1, user.getId());
            preStmt.setString(2, user.getFirstName());
            preStmt.setString(3, user.getLastName());
            preStmt.setString(4, user.getProfession());
            preStmt.setString(5, user.getEducation());
            preStmt.setDate(
                    6,
                    java.sql.Date.valueOf(
                            "2000-08-21"));

            preStmt.setString(
                    7,
                    user.getEmail());

            preStmt.setString(
                    8,
                    user.getMobileNumber());

            preStmt.setString(
                    9,
                    "MALE");

            preStmt.setBoolean(
                    10,
                    true);

            preStmt.setInt(
                    11,
                    25000);

            preStmt.setTimestamp(
                    12,
                    java.sql.Timestamp.valueOf(
                            user.getOnboardedUserAt()));

            preStmt.setTimestamp(
                    13,
                    java.sql.Timestamp.valueOf(
                            user.getLastUpdatedAt()));

            int row = preStmt.executeUpdate();
            System.out.println("Inserted Row:" + row);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in User Creation");
        }

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
