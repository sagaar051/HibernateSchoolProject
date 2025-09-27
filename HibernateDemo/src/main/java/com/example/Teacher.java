package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Teacher {

@Id
private int id;

private String name;
private String address;
private int age;

// Default constructor (required by JPA)
public Teacher() {}

// Parameterized constructor
public Teacher(String name, String address, int age) {
this.name = name;
this.address = address;
this.age = age;
}

// Getters and setters
public int getId() { return id; }
public void setId(int id) { this.id = id; }

public String getName() { return name; }
public void setName(String name) { this.name = name; }

public String getAddress() { return address; }
public void setAddress(String address) { this.address = address; }

public int getAge() { return age; }
public void setAge(int age) { this.age = age; }

// toString method
@Override
public String toString() {
return "Teacher [id=" + id + ", name=" + name +
", address=" + address + ", age=" + age + "]";
}
}