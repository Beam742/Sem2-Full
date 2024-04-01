import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    private final String name;
    private final String faculty;
    private final String nim;
    private final String program;
    private final ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student(String name, String faculty, String nim, String program) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.program = program;
    }


    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getNim() {
        return nim;
    }

    public String getProgram() {
        return program;
    }

    public void viewBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }
        System.out.println("\n===== Buku Terpinjam =====");
        System.out.printf("%-10s %-20s %-20s %-10s\n", "ID", "Judul", "Penulis", "Stok");
        for (Book book : borrowedBooks) {
            System.out.printf("%-10s %-20s %-20s %-10d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getStock());
        }
    }

    public void borrowBook(ArrayList<Book> books, Scanner scanner) {
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String id = scanner.next();
        System.out.print("Masukkan ID buku yang ingin dipinjam: ");
        String durasi = scanner.next();

        if (id.isEmpty()) {
            System.out.println("ID buku tidak boleh kosong. Kembali ke menu mahasiswa...");
            return;
        }

        Book borrowedBook = null;
        for (Book book : books) {
            if (book.getId().equals(id)) {
                borrowedBook = book;
                break;
            }
        }

        if (borrowedBook == null) {
            System.out.println("Buku tidak ditemukan. Kembali ke menu mahasiswa...");
            return;
        }


        if (borrowedBooks.contains(borrowedBook)) {
            System.out.println("Buku sudah dipinjam oleh Anda.");
            return;
        }

        if (borrowedBook.getStock() > 0) {

            Book newBorrowedBook = new Book(borrowedBook.getId(), borrowedBook.getTitle(), borrowedBook.getAuthor(), borrowedBook.getCategory(), borrowedBook.getStock());
            borrowedBooks.add(newBorrowedBook);
            System.out.println("Buku berhasil dipinjam!");
        } else {
            System.out.println("Maaf, stok buku habis.");
        }
    }

    public void returnBook(ArrayList<Book> books, Scanner scanner) {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }

        System.out.print("Masukkan ID buku yang ingin dikembalikan: ");
        String id = scanner.next();

        Book bookToReturn = null;
        for (Book book : borrowedBooks) {
            if (book.getId().equals(id)) {
                bookToReturn = book;
                break;
            }
        }

        if (bookToReturn == null) {
            System.out.println("Buku tidak ditemukan dalam daftar pinjaman Anda.");
            return;
        }

        borrowedBooks.remove(bookToReturn);
        int newStock = bookToReturn.getStock() + 1;
        bookToReturn.setStock(newStock);

        System.out.println("Pengembalian buku berhasil!");
    }
    public void displayAvailableBook(ArrayList<Book> books, Scanner scanner) {
        if (books.isEmpty()) {
            System.out.println("Belum ada buku di dalam library.");
            return;
        }

        System.out.println("\n===== Buku Tersedia =====");
        System.out.printf("%-10s %-20s %-20s %-20s %-10s\n", "ID", "Judul", "Penulis", "Kategori", "Stok");
        for (Book book : books) {
            System.out.printf("%-10s %-20s %-20s %-20s %-10d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
    }
    void borrowAllBook(ArrayList<Book> books, Scanner scanner) {
        System.out.println("Are you sure you want to borrow ALL available books (y/n)?");
        String confirmation = scanner.next().toLowerCase();
        if (confirmation.equals("y")) {
            for (Book book : books) {
                if (book.getStock() > 0) {
                    borrowedBooks.add(book);
                    book.setStock(book.getStock() - 1);
                }
            }
            System.out.println("All available books have been borrowed (subject to stock).");
        } else {
            System.out.println("Borrowing cancelled.");
        }
    }


}