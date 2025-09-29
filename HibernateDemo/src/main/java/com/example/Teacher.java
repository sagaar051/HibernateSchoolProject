package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teacher {

    @Id
    private int id; // manual ID

    private String name;
    private String address;
    private String subject; // replace age

    // Default constructor
    public Teacher() {}

    // Parameterized constructor (without ID)
    public Teacher(String name, String address, String subject) {
        this.name = name;
        this.address = address;
        this.subject = subject;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    @Override
    public String toString() {
        return "Teacher [id=" + id + ", name=" + name +
               ", address=" + address + ", subject=" + subject + "]";
    }
}
