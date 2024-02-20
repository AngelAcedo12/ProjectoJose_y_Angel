package com.example.projectojose_y_angel.repositorys;

import com.example.projectojose_y_angel.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositoryUserImpLocal implements RepositoryUser{

    private List<User> list = new ArrayList<>();

    public RepositoryUserImpLocal() {
        list.add(new User("Angel","angelacedomelli@gmail.com","1234"));
    }


    @Override
    public Optional<User> findByName(String name) {
        return list.stream().filter(user -> name.equals(user.getUser())).findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return list.stream().filter(user -> email.equals(user.getEmail())).findFirst();

    }

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean deleteUserByName() {
        return false;
    }
}
