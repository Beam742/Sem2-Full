import java.util.ArrayList;
import java.util.Scanner;

class Admin {
    private final String adminUsername;
    private final String adminPassword;
    private final ArrayList<Student> students;

    public Admin(String adminUsername, String adminPassword) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.students = new ArrayList<>();
    }


    public void addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama mahasiswa: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan fakultas mahasiswa: ");
        String faculty = scanner.nextLine();
        System.out.print("Masukkan NIM mahasiswa: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan program studi mahasiswa: ");
        String program = scanner.nextLine();

        students.add(new Student(name, null, null, null));
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    public void displayRegisteredStudents() {
        System.out.println("\n===== Mahasiswa Terdaftar =====");
        System.out.printf("%-20s %-20s %-15s %-20s\n", "Nama", "Fakultas", "NIM", "Program Studi");
        for (Student student : students) {
            System.out.printf("%-20s %-20s %-15s %-20s\n", student.getName(), student.getFaculty(), student.getNim(), student.getProgram());
        }
    }

}