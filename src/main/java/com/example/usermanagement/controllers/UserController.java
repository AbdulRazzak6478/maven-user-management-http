package com.example.usermanagement.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.usermanagement.dto.CreateUserRequest;
import com.example.usermanagement.models.User;
import com.example.usermanagement.services.UserService;
import com.example.usermanagement.utils.JsonUtil;
import com.example.usermanagement.utils.ResponseUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class UserController implements HttpHandler {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        try {

            if (method.equals("POST") && path.equals("/users")) {
                System.out.println("User Creation : " + "Method : " + method + " path : " + path);
                this.CreateUser(exchange);
            } else if (method.equals("GET") && path.equals("/users")) {
                this.getAllUsers(exchange);
            } else if (method.equals("GET") && path.equals("/users/user")) {
                String queries = exchange.getRequestURI().getQuery();
                Map<String, String> queryParams = new ConcurrentHashMap<>();
                System.out.println("before Query");
                if (queries != null) {
                    System.out.println("inside query : " + queries);
                    String[] pairs = queries.split("&");
                    for (String pair : pairs) {
                        String[] keyValue = pair.split("=");
                        System.out.println("KeyValue: " + keyValue.length);
                        if (keyValue.length == 2) {

                            queryParams.put(
                                    keyValue[0],
                                    keyValue[1]);
                        }
                        // queryParams.put(query[0], query[1]);
                    }
                    String userId = queryParams.get("userId");
                    User user = this.userService.getUserById(userId);
                    ResponseUtil.sendResponse(
                            exchange,
                            200,
                            JsonUtil.gson.toJson(user));
                }
                System.out.println("outside query");
            } else if (method.equals("DELETE") && path.equals("/users")) {
                String queries = exchange.getRequestURI().getQuery();
                Map<String, String> queryParams = new ConcurrentHashMap<>();
                System.out.println("before Query");
                if (queries != null) {
                    System.out.println("inside query : " + queries);
                    String[] pairs = queries.split("&");
                    for (String pair : pairs) {
                        String[] keyValue = pair.split("=");
                        System.out.println("KeyValue: " + keyValue.length);
                        if (keyValue.length == 2) {

                            queryParams.put(
                                    keyValue[0],
                                    keyValue[1]);
                        }
                        // queryParams.put(query[0], query[1]);
                    }
                    String userId = queryParams.get("userId");
                    this.userService.deleteUser(userId);
                    ResponseUtil.sendResponse(
                            exchange,
                            200,
                            JsonUtil.gson.toJson("User is Remove Successfully"));
                }
                System.out.println("outside query");
                // this.getAllUsers(exchange);
            } else {
                ResponseUtil.sendResponse(
                        exchange,
                        404,
                        JsonUtil.gson.toJson("Route Not Found"));
            }
        } catch (Exception e) {
            ResponseUtil.sendResponse(exchange, 404, e.getMessage());
        }

    }

    void CreateUser(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        System.out.println("After input stream");

        String body = new String(inputStream.readAllBytes());
        System.out.println("String body: " + body);

        CreateUserRequest request = JsonUtil.gson.fromJson(body, CreateUserRequest.class);
        System.out.println("DTO request: " + request.firstName);

        User user = this.userService.createUser(request);
        System.out.println("user id:" + user.getId());

        ResponseUtil.sendResponse(exchange, 201, JsonUtil.gson.toJson(user));
    }

    void getAllUsers(HttpExchange exchange) throws IOException {

        Collection<User> list = this.userService.getAllUsers();

        System.out.println("Length :" + list.size());

        ResponseUtil.sendResponse(exchange, 200, JsonUtil.gson.toJson(list));
    }
}
