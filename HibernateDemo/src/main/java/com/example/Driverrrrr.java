package com.example;

import jakarta.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Driverrrrr {

    public static void main(String[] args) {
        System.out.println("Starting Hibernate connection test...");

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("girish");
        EntityManager manager = factory.createEntityManager();
        Scanner sc = new Scanner(System.in);

        try {
            boolean exit = false;
            while (!exit) {
            	System.out.println("1: Insert Student");
            	System.out.println("2: Update Student");
            	System.out.println("3: Delete Student");
            	System.out.println("4: Insert Teacher");
            	System.out.println("5: Update Teacher");
            	System.out.println("6: Delete Teacher");
            	System.out.println("7: View All Students and Teachers");
            	System.out.println("8: Find Student by Age (Named Parameter)");
            	System.out.println("9: Find Student by Address (Positional Parameter)");
            	System.out.println("0: Exit");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1: { // Insert Student
                        EntityTransaction tx = manager.getTransaction();
                        try {
                            tx.begin();
                            System.out.print("Enter Student ID: ");
                            int sId = sc.nextInt(); sc.nextLine();
                            if (manager.find(Student.class, sId) != null) {
                                System.out.println("Student ID already exists!");
                                break;
                            }
                            Student s = new Student();
                            s.setId(sId);
                            System.out.print("Enter Name: ");
                            s.setStudent_name(sc.nextLine());
                            System.out.print("Enter Address: ");
                            s.setStudent_address(sc.nextLine());
                            System.out.print("Enter Age: ");
                            s.setAge(sc.nextInt()); sc.nextLine();
                            manager.persist(s);
                            tx.commit();
                            System.out.println("Student inserted successfully!");
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 2: { // Update Student
                        EntityTransaction tx = manager.getTransaction();
                        try {
                            tx.begin();
                            System.out.print("Enter Student ID to update: ");
                            int suId = sc.nextInt(); sc.nextLine();
                            Student sToUpdate = manager.find(Student.class, suId);
                            if (sToUpdate != null) {
                                System.out.print("Enter new Address: ");
                                sToUpdate.setStudent_address(sc.nextLine());
                                System.out.print("Enter new Age: ");
                                sToUpdate.setAge(sc.nextInt()); sc.nextLine();
                                tx.commit();
                                System.out.println("Student updated successfully!");
                            } else {
                                System.out.println("Student not found!");
                                tx.rollback();
                            }
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 3: { // Delete Student
                        EntityTransaction tx = manager.getTransaction();
                        try {
                            tx.begin();
                            System.out.print("Enter Student ID to delete: ");
                            int sdId = sc.nextInt(); sc.nextLine();
                            Student sToDelete = manager.find(Student.class, sdId);
                            if (sToDelete != null) {
                                manager.remove(sToDelete);
                                tx.commit();
                                System.out.println("Student deleted successfully!");
                            } else {
                                System.out.println("Student not found!");
                                tx.rollback();
                            }
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 4: { // Insert Teacher
                        EntityTransaction tx = manager.getTransaction();
                        try {
                            tx.begin();
                            System.out.print("Enter Teacher ID: ");
                            int tId = sc.nextInt(); sc.nextLine();
                            if (manager.find(Teacher.class, tId) != null) {
                                System.out.println("Teacher ID already exists!");
                                break;
                            }
                            Teacher t = new Teacher();
                            t.setId(tId);
                            System.out.print("Enter Name: ");
                            t.setName(sc.nextLine());
                            System.out.print("Enter Address: ");
                            t.setAddress(sc.nextLine());
                            System.out.print("Enter Subject: ");
                            t.setSubject(sc.nextLine());
                            manager.persist(t);
                            tx.commit();
                            System.out.println("Teacher inserted successfully!");
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 5: { // Update Teacher
                        EntityTransaction tx = manager.getTransaction();
                        try {
                            tx.begin();
                            System.out.print("Enter Teacher ID to update: ");
                            int tuId = sc.nextInt(); sc.nextLine();
                            Teacher tToUpdate = manager.find(Teacher.class, tuId);
                            if (tToUpdate != null) {
                                System.out.print("Enter new Subject: ");
                                tToUpdate.setSubject(sc.nextLine());
                                tx.commit();
                                System.out.println("Teacher updated successfully!");
                            } else {
                                System.out.println("Teacher not found!");
                                tx.rollback();
                            }
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 6: { // Delete Teacher
                        EntityTransaction tx = manager.getTransaction();
                        try {
                            tx.begin();
                            System.out.print("Enter Teacher ID to delete: ");
                            int tdId = sc.nextInt(); sc.nextLine();
                            Teacher tToDelete = manager.find(Teacher.class, tdId);
                            if (tToDelete != null) {
                                manager.remove(tToDelete);
                                tx.commit();
                                System.out.println("Teacher deleted successfully!");
                            } else {
                                System.out.println("Teacher not found!");
                                tx.rollback();
                            }
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 7: { // View All
                        System.out.println("\n--- All Students ---");
                        List<Student> students = manager.createQuery("from Student", Student.class).getResultList();
                        students.forEach(System.out::println);

                        System.out.println("\n--- All Teachers ---");
                        List<Teacher> teachers = manager.createQuery("from Teacher", Teacher.class).getResultList();
                        teachers.forEach(System.out::println);
                        break;
                    }
                    case 8: { // Find Student by Age (using named parameter)
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        try {
                            List<Student> students = manager.createQuery(
                                    "SELECT s FROM Student s WHERE s.age = :age", Student.class)
                                    .setParameter("age", age)   // named parameter
                                    .getResultList();

                            if (students.isEmpty()) {
                                System.out.println("No students found with age " + age);
                            } else {
                                System.out.println("\n--- Students with age " + age + " ---");
                                students.forEach(System.out::println);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 9: { // Find Student by Address (using positional parameter)
                        System.out.print("Enter Address: ");
                        String addr = sc.nextLine();

                        try {
                            List<Student> students = manager.createQuery(
                                    "SELECT s FROM Student s WHERE s.student_address = ?1", Student.class)
                                    .setParameter(1, addr)   // positional parameter
                                    .getResultList();

                            if (students.isEmpty()) {
                                System.out.println("No students found at address: " + addr);
                            } else {
                                System.out.println("\n--- Students living at " + addr + " ---");
                                students.forEach(System.out::println);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    case 0:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            }

        } finally {
            manager.close();
            factory.close();
            sc.close();
        }
    }
}
