package com.app.contactbook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class contact {

    @Id
    private long phonenumber;

    private String name;
    private String email;

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
