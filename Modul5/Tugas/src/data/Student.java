package data;

import com.main.LibrarySystem;
import books.Book;
import util.IMenu;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User implements IMenu {
    private String name, faculty, programStudi, NIM;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private static ArrayList<Book> studentBook = new ArrayList<>();

    public Student(String name, String NIM, String faculty, String programStudi) {
        this.name = name;
        this.NIM = NIM;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public Student() {
    }

    @Override
    public void menu() {
        int numberBorrowed = 0;
        boolean isRun = true;
        boolean isFound = true;
        Student student = new Student();
        Scanner inputObj = new Scanner(System.in);
        String input1;

        try {
            while (isRun) {
                System.out.print("Enter Your NIM (input 99 untuk back): ");
                input1 = LibrarySystem.inputNIM();
                if (input1.equals("99")) {
                    break;
                }
                isFound = false;
                for (Student x : Admin.getStudentData()) {
                    if (x.getNIM().equals(input1)) {
                        student = x;
                        isFound = true;
                        isRun = false;
                    }
                }
                if (!isFound) {
                    System.out.println("INVALID NOT FOUNDS");
                }
            }
            if (isFound) {
                Student.setStudentBook();
                String[] arrId = new String[10];
                int[] arrDuration = new int[10];
                isRun = true;
                while (isRun) {
                    student.displayBook();
                    System.out.print(
                            "===== Student Menu =====\n1. Buku Terpinjam \n2. Pinjam Buku\n3. Kembalikan Buku\n4. Pinjam Buku atau Logout\n5. Tampilkan User\nChoose option (1-5) : ");
                    int choose = inputObj.nextInt();
                    inputObj.nextLine();
                    switch (choose) {
                        case 1:
                            if (student.getBorrowedBooks().isEmpty())
                                System.out.println("Tidak ada buku yang dipinjam");
                            else
                                student.showBorrowedBooks();
                            break;
                        case 2:
                            String inputId;
                            do {
                                System.out
                                        .print("Input Id buku yang ingin dipinjam (input 99 untuk kembali)\nInput : ");
                                inputId = inputObj.nextLine();
                                if (inputId.equals("99")) {
                                    break;
                                }
                                boolean isFoundBook = false;
                                for (Book book : Student.getStudentBook()) {
                                    if (book.getBookId().equals(inputId) && book.getStock() > 0) {
                                        isFoundBook = true;
                                        System.out.print(
                                                "Berapa lama buku akan dipinjam ? (maksimal 14 hari)\nInput lama (hari) : ");
                                        int duration = inputObj.nextInt();
                                        inputObj.nextLine();
                                        student.choiceBook(inputId, duration);
                                        System.out.println("Buku berhasil disimpan.");
                                        break;
                                    }
                                }
                                if (!isFoundBook)
                                    System.out.println(
                                            "Buku dengan ID " + inputId + " tidak tersedia atau tidak ada stok.");
                            } while (true);
                            break;
                        case 3:
                            isFound = false;
                            System.out.print("Masukkan Id buku yang ingin dikembalikan : ");
                            String inputAgain = inputObj.nextLine();
                            for (Book book : Admin.getBookList()) {
                                if (book.getBookId().equals(inputAgain)) {
                                    isFound = true;
                                    break;
                                }
                            }
                            if (isFound) {
                                student.returnBook(inputAgain);
                            } else
                                System.out.println("Buku dengan ID " + inputAgain + " tidak ditemukan");
                            break;
                        case 4:
                            if (numberBorrowed > 0) {
                                student.showTempBook(arrId, numberBorrowed, arrDuration);
                                int choose2;
                                System.out.print(
                                        "Apakah anda ingin meminjam buku tersebut\n1. Ya\n2. Tidak\nChoose Option : ");
                                choose2 = inputObj.nextInt();
                                inputObj.nextLine();
                                LibrarySystem.addTempBook(student, numberBorrowed, choose2, arrId, arrDuration);
                            }
                            isRun = false;
                            break;
                        case 5:
                            student.showUser();
                            break;
                        default:
                            System.out.println("INVALID INPUT");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addBook(Book book) {
        borrowedBooks.add(book);
    }

    public void choiceBook(String bookId, int duration) {
        for (Book book : Student.getStudentBook()) {
            if (book.getBookId().equals(bookId) && book.getStock() > 0) {
                Book borrowedBook = new Book(book.getBookId(), book.getTitle(), book.getAuthor(), 1);
                borrowedBook.setDuration(duration);
                borrowedBook.setCategory(book.getCategory());
                this.addBook(borrowedBook);
                book.setStock(book.getStock() - 1);
                System.out.println("Buku berhasil dipinjam.");
                return;
            }
        }
        System.out.println("Buku tidak tersedia atau stok telah habis.");
    }

    public static void setStudentBook() {
        studentBook.clear();
        for (Book book : Admin.getBookList()) {
            Book copiedBook = new Book(book.getBookId(), book.getTitle(), book.getAuthor(), book.getStock());
            copiedBook.setCategory(book.getCategory());
            studentBook.add(copiedBook);
        }
    }

    public void returnBook(String bookId) {
        for (Book book : this.getBorrowedBooks()) {
            if (book.getBookId().equals(bookId)) {
                this.getBorrowedBooks().remove(book); // Menghapus buku dari daftar buku yang dipinjam oleh siswa
                for (Book availableBook : Student.getStudentBook()) {
                    if (availableBook.getBookId().equals(bookId)) {
                        availableBook.setStock(availableBook.getStock() + 1); // Menambahkan kembali stok buku yang
                                                                              // dikembalikan
                        System.out.println("Buku berhasil dikembalikan.");
                        return;
                    }
                }
            }
        }
        System.out.println("Buku tidak dipinjam atau ID buku tidak valid.");
    }

    public static void logOut() {
        System.out.println("LOG OUT");
    }

    @Override
    public void showUser() {
        System.out.println("USER : " + this.NIM);
    }

    // MENAMPILKAN SEMUA DAFTAR BUKU KETIKA BERADA DI MENU STUDENT
    @Override
    public void displayBook() {
        super.printLine();
        System.out.printf("|| %-3s || %-19s || %-20s || %-20s || %-12s || %-6s ||\n", "No.", "Id buku", "Nama Buku",
                "Author", "Category", "Stock");
        super.printLine();
        int count = 1;
        for (Book x : studentBook) {
            System.out.printf("|| %-3d || %-19s || %-20s || %-20s || %-12s || %-6d ||\n", count, x.getBookId(),
                    x.getTitle(), x.getAuthor(), x.getCategory(), x.getStock());
            count++;
        }
        super.printLine();
    }

    public void displayInfo() {
        System.out.println("NAME :" + this.name + "\nNIM : " + this.NIM + "\nFakultas :" + this.faculty
                + "\nProgram Studi :" + this.programStudi + "\n");
    }

    public void showBorrowedBooks() {
        System.out.print("==");
        super.printLine();
        System.out.printf("|| %-3s || %-19s || %-20s || %-20s || %-12s || %-6s ||\n", "No.", "Id buku", "Nama Buku",
                "Author", "Category", "Duration");
        System.out.print("==");
        printLine();
        int count = 1;
        for (Book x : getBorrowedBooks()) {
            System.out.printf("|| %-3d || %-19s || %-20s || %-20s || %-12s || %-6d   ||\n", count, x.getBookId(),
                    x.getTitle(), x.getAuthor(), x.getCategory(), x.getDuration());
            count++;
        }
        System.out.print("==");
        super.printLine();
    }

    // MENAMPILKAN BUKU SEBELUM DIPINJAM
    public void showTempBook(String[] inputId, int amount, int[] duration) {
        super.printLine();
        System.out.printf("|| %-3s || %-19s || %-19s || %-19s || %-12s || %-8s ||\n", "No.", "Id buku", "Nama Buku",
                "Author", "Category", "Duration");
        super.printLine();
        int count = 1;
        for (int i = 0; i < amount; i++) {
            for (Book x : getStudentBook()) {
                if (x.getBookId().equals(inputId[i])) {
                    System.out.printf("|| %-3d || %-19s || %-19s || %-19s || %-12s || %-8s ||\n", count, x.getBookId(),
                            x.getTitle(), x.getAuthor(), x.getCategory(), duration[i]);
                    count++;
                }
            }
        }
        super.printLine();
    }

    public static ArrayList<Book> getStudentBook() {
        return studentBook;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getNIM() {
        return this.NIM;
    }

}
