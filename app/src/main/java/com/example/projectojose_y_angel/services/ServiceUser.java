package com.example.projectojose_y_angel.services;

import com.example.projectojose_y_angel.models.User;

import java.util.List;

public interface ServiceUser {
    public User getByName();
    public List<User> getAll();
}
