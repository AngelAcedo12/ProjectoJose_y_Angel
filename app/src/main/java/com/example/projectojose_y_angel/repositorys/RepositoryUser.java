package com.example.projectojose_y_angel.repositorys;

import com.example.projectojose_y_angel.models.User;

import java.util.List;
import java.util.Optional;

public interface RepositoryUser{

    public Optional<User> findByName(String name);
    public Optional<User> findByEmail(String email);
    public boolean create();
    public List<User> findAll();
    public  boolean deleteUserByName();
}
