package com.example.SQLitedemo;

import java.sql.Date;

public class Student {
    String id;
    String name;
    String birthday;
    String email;
    String country;

    public Student(String id, String name, String birthday, String email, String country) {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.email = email;
        this.country = country;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
