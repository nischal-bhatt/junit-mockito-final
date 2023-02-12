package com.appsdeveloperblog.estore.model;

public class User {

    public User(String firstName,String lastName, String email, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.id=id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    private String firstName;
    private String email;
    private String lastName;
    private String id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }
}
