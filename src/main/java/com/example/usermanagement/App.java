package com.example.usermanagement;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.example.usermanagement.controllers.UserController;
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
    }
}
