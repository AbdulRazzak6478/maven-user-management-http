package com.example.usermanagement.repositories;

import java.util.Collection;

import com.example.usermanagement.datastore.Datastore;
import com.example.usermanagement.models.User;

public class UserRepository {

    public void save(User user) {
        Datastore.users.put(user.getId(), user);
    }

    public User getUserById(String id) {
        return Datastore.users.get(id);
    }

    public Collection<User> getAllUsers() {
        return Datastore.users.values();
    }

    public void deleteUser(String id)
    {
        Datastore.users.remove(id);
    }
}
