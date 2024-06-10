package com.example.yok;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.example.yok.books.Book;
import com.example.yok.data.Admin;
import com.example.yok.data.Student;
import com.example.yok.data.User;
import com.example.yok.exception.custom.illegalAdminAccess;

public class LibrarySystem extends Application {

    private Stage primaryStage;
    private Admin admin;
    private Student currentStudent;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.admin = new Admin();
        primaryStage.setTitle("Library Management System");

        showLoginMenu();
    }

    private void showLoginMenu() {
        // Create buttons for login options
        Button studentLoginButton = new Button("Login as Student");
        studentLoginButton.setOnAction(e -> showStudentLogin());

        Button adminLoginButton = new Button("Login as Admin");
        adminLoginButton.setOnAction(e -> showAdminLogin());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        VBox loginLayout = new VBox(10, studentLoginButton, adminLoginButton, exitButton);
        loginLayout.setPadding(new Insets(10));
        Scene loginScene = new Scene(loginLayout, 300, 200);

        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showStudentLogin() {
        // Create login form for student
        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        Button loginButton = new Button("Login");
        Label errorMessage = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showLoginMenu());

        loginButton.setOnAction(e -> {
            String nim = nimField.getText();
            for (Student student : Admin.getStudentData()) {
                if (student.getNIM().equals(nim)) {
                    currentStudent = student;
                    showStudentMenu();
                    return;
                }
            }
            errorMessage.setText("Invalid NIM");
        });

        VBox loginLayout = new VBox(10, nimField, loginButton, errorMessage, backButton);
        loginLayout.setPadding(new Insets(10));
        Scene studentLoginScene = new Scene(loginLayout, 300, 200);

        primaryStage.setScene(studentLoginScene);
    }

    private void showStudentMenu() {
        Button viewBooksButton = new Button("View Available Books");
        viewBooksButton.setOnAction(e -> showAvailableBooks());

        Button viewBorrowedBooksButton = new Button("View Borrowed Books");
        viewBorrowedBooksButton.setOnAction(e -> showBorrowedBooks());

        Button borrowBookButton = new Button("Borrow Book");
        borrowBookButton.setOnAction(e -> showBorrowBookForm());

        Button returnBookButton = new Button("Return Book");
        returnBookButton.setOnAction(e -> showReturnBookForm());

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> showLoginMenu());

        VBox studentLayout = new VBox(10, viewBooksButton, viewBorrowedBooksButton, borrowBookButton, returnBookButton, logoutButton);
        studentLayout.setPadding(new Insets(10));
        Scene studentScene = new Scene(studentLayout, 300, 300);

        primaryStage.setScene(studentScene);
    }

    private void showAvailableBooks() {
        TableView<Book> bookTable = new TableView<>();
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
        bookIdColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookId()));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStock()).asObject());

        bookTable.getColumns().addAll(bookIdColumn, titleColumn, authorColumn, categoryColumn, stockColumn);

        // Add books to the table
        bookTable.getItems().addAll(User.getBookList());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu());

        VBox layout = new VBox(bookTable, backButton);
        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setScene(scene);
    }

    private void showBorrowedBooks() {
        TableView<Book> bookTable = new TableView<>();
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
        bookIdColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookId()));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        TableColumn<Book, Integer> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getDuration()).asObject());

        bookTable.getColumns().addAll(bookIdColumn, titleColumn, authorColumn, categoryColumn, durationColumn);

        // Add borrowed books to the table
        bookTable.getItems().addAll(currentStudent.getBorrowedBooks());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu());

        VBox layout = new VBox(bookTable, backButton);
        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setScene(scene);
    }

    private void showBorrowBookForm() {
        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Book ID");

        TextField durationField = new TextField();
        durationField.setPromptText("Duration (days)");

        Button borrowButton = new Button("Borrow");
        Label message = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu());

        borrowButton.setOnAction(e -> {
            String bookId = bookIdField.getText();
            int duration = Integer.parseInt(durationField.getText());
            boolean isFoundBook = false;
            for (Book book : User.getBookList()) {
                if (book.getBookId().equals(bookId) && book.getStock() > 0) {
                    isFoundBook = true;
                    currentStudent.choiceBook(bookId, duration);
                    message.setText("Book successfully borrowed.");
                    break;
                }
            }
            if (!isFoundBook) {
                message.setText("Book not found or out of stock.");
            }
        });

        VBox borrowLayout = new VBox(10, bookIdField, durationField, borrowButton, message, backButton);
        borrowLayout.setPadding(new Insets(10));
        Scene borrowScene = new Scene(borrowLayout, 300, 200);

        primaryStage.setScene(borrowScene);
    }

    private void showReturnBookForm() {
        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Book ID");

        Button returnButton = new Button("Return");
        Label message = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu());

        returnButton.setOnAction(e -> {
            String bookId = bookIdField.getText();
            Book borrowedBook = currentStudent.returnBook(bookId);
            if (borrowedBook != null) {
                for (Book book : User.getBookList()) {
                    if (book.getBookId().equals(bookId)) {
                        book.setStock(book.getStock() + 1);
                        message.setText("Book successfully returned.");
                        break;
                    }
                }
            } else {
                message.setText("Book not found.");
            }
        });

        VBox returnLayout = new VBox(10, bookIdField, returnButton, message, backButton);
        returnLayout.setPadding(new Insets(10));
        Scene returnScene = new Scene(returnLayout, 300, 200);

        primaryStage.setScene(returnScene);
    }

    private void showAdminLogin() {
        // Create login form for admin
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        Label errorMessage = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showLoginMenu());

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if (admin.isAdmin(username, password)) {
                    showAdminMenu();
                } else {
                    throw new illegalAdminAccess("Invalid username or password");
                }
            } catch (illegalAdminAccess ex) {
                errorMessage.setText(ex.getMessage());
            }
        });

        VBox loginLayout = new VBox(10, usernameField, passwordField, loginButton, errorMessage, backButton);
        loginLayout.setPadding(new Insets(10));
        Scene adminLoginScene = new Scene(loginLayout, 300, 200);

        primaryStage.setScene(adminLoginScene);
    }

    private void showAdminMenu() {
        Button addStudentButton = new Button("Add Student");
        addStudentButton.setOnAction(e -> showAddStudentForm());

        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(e -> showAddBookForm());

        Button displayStudentsButton = new Button("Display Students");
        displayStudentsButton.setOnAction(e -> showDisplayStudents());

        Button displayBooksButton = new Button("Display Books");
        displayBooksButton.setOnAction(e -> showDisplayBooks());

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> showLoginMenu());

        VBox adminLayout = new VBox(10, addStudentButton, addBookButton, displayStudentsButton, displayBooksButton, logoutButton);
        adminLayout.setPadding(new Insets(10));
        Scene adminScene = new Scene(adminLayout, 300, 300);

        primaryStage.setScene(adminScene);
    }

    private void showAddStudentForm() {
        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField nimField = new TextField();
        nimField.setPromptText("NIM");

        TextField facultyField = new TextField();
        facultyField.setPromptText("Faculty");

        TextField programStudiField = new TextField();
        programStudiField.setPromptText("Program Studi");

        Button addButton = new Button("Add");
        Label message = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu());

        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String nim = nimField.getText();
            String faculty = facultyField.getText();
            String programStudi = programStudiField.getText();
            admin.addStudent(new Student(name, nim, faculty, programStudi));
            message.setText("Student added successfully.");
        });

        VBox addStudentLayout = new VBox(10, nameField, nimField, facultyField, programStudiField, addButton, message, backButton);
        addStudentLayout.setPadding(new Insets(10));
        Scene addStudentScene = new Scene(addStudentLayout, 300, 300);

        primaryStage.setScene(addStudentScene);
    }

    private void showAddBookForm() {
        TextField bookIdField = new TextField();
        bookIdField.setPromptText("Book ID");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");

        TextField stockField = new TextField();
        stockField.setPromptText("Stock");

        Button addButton = new Button("Add");
        Label message = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu());

        addButton.setOnAction(e -> {
            String bookId = bookIdField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            int stock = Integer.parseInt(stockField.getText());
            Book newBook = new Book(bookId, title, author, stock, category);
            admin.addBook(newBook);
            User.getBookList().add(newBook);
            message.setText("Book added successfully.");
        });

        VBox addBookLayout = new VBox(10, bookIdField, titleField, authorField, categoryField, stockField, addButton, backButton, message);
        addBookLayout.setPadding(new Insets(10));
        Scene addBookScene = new Scene(addBookLayout, 300, 300);

        primaryStage.setScene(addBookScene);
    }

    private void showDisplayStudents() {
        TableView<Student> studentTable = new TableView<>();
        TableColumn<Student, String> nimColumn = new TableColumn<>("NIM");
        nimColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNIM()));
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        TableColumn<Student, String> facultyColumn = new TableColumn<>("Faculty");
        facultyColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFaculty()));
        TableColumn<Student, String> programStudiColumn = new TableColumn<>("Program Studi");
        programStudiColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProgramStudi()));

        studentTable.getColumns().addAll(nimColumn, nameColumn, facultyColumn, programStudiColumn);
        studentTable.getItems().addAll(Admin.getStudentData());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showAdminMenu());

        VBox studentLayout = new VBox(studentTable, backButton);
        Scene studentScene = new Scene(studentLayout, 800, 600);

        primaryStage.setScene(studentScene);
    }

    private void showDisplayBooks() {
        TableView<Book> bookTable = new TableView<>();
        TableColumn<Book, String> bookIdColumn = new TableColumn<>("Book ID");
        bookIdColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookId()));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthor()));
        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));
        TableColumn<Book, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getStock()).asObject());

        bookTable.getColumns().addAll(bookIdColumn, titleColumn, authorColumn, categoryColumn, stockColumn);
        bookTable.getItems().addAll(User.getBookList());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showStudentMenu());

        VBox bookLayout = new VBox(bookTable, backButton);
        Scene bookScene = new Scene(bookLayout, 800, 600);

        primaryStage.setScene(bookScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

