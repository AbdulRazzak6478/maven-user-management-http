package com.example.usermanagement;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.usermanagement.controllers.UserController;
import com.example.usermanagement.models.DBconnection;
import com.example.usermanagement.repositories.UserRepository;
import com.example.usermanagement.services.UserService;
import com.sun.net.httpserver.HttpServer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        System.out.println("Started learning the Maven with user management http ");

        UserRepository userRepository = new UserRepository();

        UserService userService = new UserService(userRepository);

        UserController userController = new UserController(userService);

        HttpServer server = HttpServer.create(
                new InetSocketAddress(4000), 0);

        server.createContext("/users", userController);

        server.setExecutor(null);

        server.start();

        System.out.println(
                "Server started on port 4000");

        String uri = "jdbc:postgresql://localhost:5432/dispute_test_db";
        String username = "postgres";
        String password = "post30@MARQ46";
        String test = """
                Testing testing
                """;
        try (
                //
                // Connection conn =/ DriverManager.getConnection(uri, username, password);
                Connection conn = DBconnection.getConnection();

                Statement stmt = conn.createStatement();

                ResultSet result = stmt.executeQuery("SELECT * FROM merchants");

        ) {

            while (result.next()) {
                System.out.println("id : " + result.getString("id"));
                System.out.println("customId : " + result.getString("merchant_id"));
                System.out.println("email : " + result.getString("email"));
                System.out.println("name : " + result.getString("name"));
                System.out.println("mobileNumber : " + result.getString("mobile_number"));
                System.out.println("total Disputes : " + result.getInt("total_disputes"));
                System.out.println("total analyst : " + result.getInt("total_analysts"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in jdbc : " + e.getMessage());
        }
        System.out.println();
        System.out.println();
        String sqlQuery = "SELECT * FROM merchants where email=? AND mobile_number=?";
        try (
                Connection conn = DBconnection.getConnection();
                PreparedStatement preStmt = conn.prepareStatement(sqlQuery);) {
            preStmt.setString(1, "yuzi@skygoalnext.com");
            preStmt.setString(2, "+919515426479");
            ResultSet rs = preStmt.executeQuery();
            // boolean hasData = rs.next();

            // System.out.println("Has Data : " + hasData);
            // System.out.println("Result Set : " + rs);

            while (rs.next()) {
                System.out.println("id: " + rs.getString("id"));
                System.out.println("email: " + rs.getString("email"));
                System.out.println("mobile number: " + rs.getString("mobile_number"));
                System.out.println("total Disputes: " + rs.getInt("total_disputes"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
