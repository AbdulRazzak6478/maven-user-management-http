package com.example.usermanagement.datastore;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.usermanagement.models.User;

public class Datastore {
    public static Map<String, User> users = new ConcurrentHashMap<>();
}

 