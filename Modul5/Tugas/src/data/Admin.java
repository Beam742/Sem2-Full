package data;

import com.main.LibrarySystem;
import books.Book;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import util.IMenu;
import data.Student;
import exception.custom.illegalAdminAccess;
import books.Book;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin extends User implements IMenu {
    Scanner inputObj = new Scanner(System.in);
    private String adminUserName = "admin", adminPassword = "admin";
    private static ArrayList<Student> studentData = new ArrayList<>();
    private static ArrayList<String> studentList = new ArrayList<>();

    @Override
    public void menu() {
        boolean isRun = true;
        Scanner inputObj = new Scanner(System.in);
        try {
            while (isRun) {
                System.out.print(
                        "===== Admin Menu =====\n1. Add Student\n2. Add Book \n3. Display Registered Student \n4. Display Available Books\n5. Display User\n6. Logout\nChoose option (1-5) : ");
                int choose = inputObj.nextInt();
                inputObj.nextLine();
                switch (choose) {
                    case 1:
                        LibrarySystem.addTempStudent();
                        break;
                    case 2:
                        this.inputBook();
                        break;
                    case 3:
                        this.displayStudent();
                        break;
                    case 4:
                        this.displayBook();
                        break;
                    case 5:
                        this.showUser();
                        break;
                    case 6:
                        isRun = false;
                        break;
                    default:
                        System.out.println("INVALID INPUT");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        studentData.add(student);
        studentList.add(student.getNIM());
    }

    public void inputBook() {
        String newTitle, newAuthor, newStock, choose;
        do {
            System.out.print("Select book category:\n1. Story\n2. History\n3. Text Book\nChoose Category : ");
            choose = inputObj.nextLine();
            if (!choose.matches("[1-3]")) {
                System.out.println("Invalid category. Please choose between 1 to 3.");
            }
        } while (!choose.matches("[1-3]"));
        System.out.print("Input book title : ");
        newTitle = inputObj.nextLine();
        System.out.print("Input book Author : ");
        newAuthor = inputObj.nextLine();
        do {
            System.out.print("Input book stock : ");
            newStock = inputObj.nextLine();

            if (!newStock.matches("\\d+")) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!newStock.matches("\\d+"));
        switch (Integer.parseInt(choose)) {
            case 1:

                StoryBook storybook = new StoryBook(generateId(), newTitle, newAuthor, Integer.parseInt(newStock));
                super.addBook(storybook);
                break;
            case 2:
                HistoryBook historyBook = new HistoryBook(generateId(), newTitle, newAuthor,
                        Integer.parseInt(newStock));
                super.addBook(historyBook);
                break;
            case 3:
                TextBook textbook = new TextBook(generateId(), newTitle, newAuthor, Integer.parseInt(newStock));
                super.addBook(textbook);
                break;
            default:
                break;
        }
        System.out.println("Book sucessfully added to the library");
    }

    @Override
    public void showUser() {
        System.out.println("USER ADMIN");
    }

    @Override
    public void displayBook() {
        super.displayBook();
    }

    public void displayStudent() {
        for (Student x : studentData) {
            x.displayInfo();
        }
    }

    public boolean isAdmin() throws illegalAdminAccess {
        String username, pass;
        System.out.print("Input username : ");
        username = inputObj.nextLine();
        System.out.print("Input passeord : ");
        pass = inputObj.nextLine();
        if (username.equals(getAdminUserName()) && pass.equals(getAdminPassword()))
            return true;
        else
            throw new illegalAdminAccess("Invalid credentials");
    }

    public String generateId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int part = random.nextInt(0xFFFF + 1);
            sb.append(String.format("%04x", part));
            if (i < 3) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public static ArrayList<Student> getStudentData() {
        return studentData;
    }

    public static ArrayList<String> getStudentList() {
        return studentList;
    }

    public String getAdminPassword() {
        return adminPassword;
    }
}