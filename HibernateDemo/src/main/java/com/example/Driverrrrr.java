package com.example;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;

public class Driverrrrr {

    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        System.out.println("Starting Hibernate connection test...");

        factory = Persistence.createEntityManagerFactory("girish");
        EntityManager manager = factory.createEntityManager();

        try {
            // --- CREATE ---
            insertStudent(manager, 101, "Sagar", "bhubneswar", 21);
            insertStudent(manager, 201, "Ashwin", "Berhampur", 21);
            insertTeacher(manager, 301, "Girirsh", "Karnataka", 27);

            // --- UPDATE ---
            updateStudent(manager, 101, "berhampur", 22);
            updateTeacher(manager, 301, "Bangalore");

            // --- DELETE ---
            deleteStudent(manager, 201);
           

            // --- GENERIC FIND ---
            Student s = findEntityById(manager, Student.class, 101);
            if (s != null) System.out.println("Found Student: " + s.getStudent_name());
            else System.out.println("Student not found.");

        } finally {
            manager.close();
            factory.close();
        }

        System.out.println("All operations completed successfully!");
    }

    // ========================= CRUD METHODS =========================

    // Insert Student
    public static void insertStudent(EntityManager manager, int id, String name, String address, int age) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Student student = new Student();
            student.setId(id);
            student.setStudent_name(name);
            student.setStudent_address(address);
            student.setAge(age);
            manager.persist(student);
            transaction.commit();
            System.out.println("Inserted Student ID: " + id);
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Update Student
    public static void updateStudent(EntityManager manager, int id, String newAddress, int newAge) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Student student = manager.find(Student.class, id);
            if (student != null) {
                student.setStudent_address(newAddress);
                student.setAge(newAge);
                System.out.println("Updated Student ID: " + id);
            } else {
                System.out.println("Student ID " + id + " not found for update.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete Student
    public static void deleteStudent(EntityManager manager, int id) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Student student = manager.find(Student.class, id);
            if (student != null) {
                manager.remove(student);
                System.out.println("Deleted Student ID: " + id);
            } else {
                System.out.println("Student ID " + id + " not found for deletion.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Insert Teacher
    public static void insertTeacher(EntityManager manager, int id, String name, String address, int age) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Teacher teacher = new Teacher();
            teacher.setId(id);
            teacher.setName(name);
            teacher.setAddress(address);
            teacher.setAge(age);
            manager.persist(teacher);
            transaction.commit();
            System.out.println("Inserted Teacher ID: " + id);
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Update Teacher
    public static void updateTeacher(EntityManager manager, int id, String newAddress) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Teacher teacher = manager.find(Teacher.class, id);
            if (teacher != null) {
                teacher.setAddress(newAddress);
                System.out.println("Updated Teacher ID: " + id);
            } else {
                System.out.println("Teacher ID " + id + " not found for update.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Delete Teacher
    public static void deleteTeacher(EntityManager manager, int id) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Teacher teacher = manager.find(Teacher.class, id);
            if (teacher != null) {
                manager.remove(teacher);
                System.out.println("Deleted Teacher ID: " + id);
            } else {
                System.out.println("Teacher ID " + id + " not found for deletion.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Generic find method
    public static <T> T findEntityById(EntityManager manager, Class<T> entityClass, int id) {
        return manager.find(entityClass, id);
    }
}
