package com.main;

import books.Book;
import data.Admin;
import data.Student;
import exception.custom.illegalAdminAccess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean isRun = true;
        Student student = new Student();
        Scanner inputObj = new Scanner(System.in);
        try {
            while (isRun) {
                System.out.print(
                        "==== Library System ====\n1. Login as Student\n2. Login as Admin\n3. Exit\nChoose option (1-3) : ");
                int choose = inputObj.nextInt();
                inputObj.nextLine();
                switch (choose) {
                    case 1:
                        student.menu();
                        break;
                    case 2:
                        try {
                            Admin admin = new Admin();
                            boolean isValid = admin.isAdmin();
                            if (isValid)
                                admin.menu();
                        } catch (illegalAdminAccess e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        isRun = false;
                        break;
                    default:
                        System.out.println("INVALID INPUT");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error : Invalid input. Please enter a number (1-3).");
            inputObj.nextLine();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static String inputNIM() {
        Scanner inputObj = new Scanner(System.in);
        return inputObj.nextLine();
    }

    public static void addTempStudent() {
        Admin admin = new Admin();
        Scanner inputObj = new Scanner(System.in);
        String name, NIM, faculty, program;
        System.out.print("Enter student name : ");
        name = inputObj.nextLine();
        do {
            System.out.print("Enter NIM : ");
            NIM = inputObj.nextLine();
            if (NIM.length() != 15) {
                System.out.println("NIM MUST 15 CHARACTERS");
            }
        } while (NIM.length() != 15);
        System.out.print("Enter Faculty : ");
        faculty = inputObj.nextLine();
        System.out.print("Enter Student Program : ");
        program = inputObj.nextLine();
        Student student = checkNIM(name, NIM, faculty, program);
        if (student != null) {
            admin.addStudent(student);
            System.out.println("Student successfully registered ");
        } else
            System.out.println("Student with the same NIM already exists!");
    }

    public static Student checkNIM(String name, String NIM, String faculty, String program) {
        ArrayList<Student> studentList = Admin.getStudentData();
        for (Student x : studentList) {
            if (x.getNIM().equals(NIM)) {
                return null;
            }
        }
        return new Student(name, NIM, faculty, program);
    }

    public static void addTempBook(Student student, int numberBorrowed, int choose, String[] arrId, int[] arrDuration) {
        if (choose == 1) {
            for (int i = 0; i < numberBorrowed; i++) {
                student.choiceBook(arrId[i], arrDuration[i]);
            }
        } else if (choose == 2 && numberBorrowed > 0) {
            for (int i = 0; i < numberBorrowed; i++) {
                for (Book book : Student.getStudentBook()) {
                    if (book.getBookId().equals(arrId[i])) {
                        book.setStock(book.getStock() + 1);
                    }
                }
            }
        } else
            Student.logOut();
    }
}