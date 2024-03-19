package Modul1;

import java.io.IOException;
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tugas1 {
    public static void mainCaller() {
        try {
            Thread.sleep(2500);
            try {
                Process process = new ProcessBuilder("cmd", "/c", "cls").inheritIO().start();
                process.waitFor();
                main(null);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("==== Library System ====");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choose option (1-3): ");
        int option = input.nextInt();

        switch (option) {
            case 1:
                student();
                break;
            case 2:
                admin();
                break;
            case 3:
                System.out.println("TERIMAKASIH DAN JUMPA LAGI");
                break;
            default:
                System.out.println("Input not valid!!");
                break;
        }

    }

    public static void student() {
        Scanner inputNim = new Scanner(System.in);

        BigInteger nim = BigInteger.ZERO;
        try {
            System.out.print("Enter your NIM : ");
            nim = inputNim.nextBigInteger();

            if (nim.toString().length() == 15) {
                System.out.println("Successful Login as Student");
                mainCaller();
            } else {
                System.out.println("User not found!!");
                mainCaller();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid NIM.");
            student();
        }

        inputNim.close();
    }

    public static void admin() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your username (admin) : ");
        String user = input.next();
        System.out.print("Enter your password (admin) : ");
        String pass = input.next();

        if (user.equals("admin") && pass.equals("admin")) {
            System.out.println("Successful Login as Admin");
            mainCaller();
        } else {
            System.out.println("User admin not found!!");
            mainCaller();
        }

        input.close();
    }
}
