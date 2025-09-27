package com.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Student {

@Id
private int id;

private Integer age;
private String student_name;
private String student_address;

// Default constructor (required by JPA)
public Student() {}

// Parameterized constructor (without id)
public Student(String student_name, String student_address, int age) {
this.student_name = student_name;
this.student_address = student_address;
this.age = age;
}

// Getters and Setters
public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getStudent_name() {
return student_name;
}

public void setStudent_name(String student_name) {
this.student_name = student_name;
}

public String getStudent_address() {
return student_address;
}

public void setStudent_address(String student_address) {
this.student_address = student_address;
}

public int getAge() {
return age;
}

public void setAge(int age) {
this.age = age;
}

// toString method
@Override
public String toString() {
return "Student [id=" + id + ", student_name=" + student_name +
", student_address=" + student_address + ", age=" + age + "]";
}
}