package com.example.projectojose_y_angel.models;

public class User {
    private String user;
    private String email;
    private String password;

    public User(String user, String email, String password) {
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getUser() {
        return user;
    }

    public void setName(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
