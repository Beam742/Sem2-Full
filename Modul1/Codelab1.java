package Modul1;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Codelab1 {
    public static void main(String[] args){
        System.out.println("\\input");
        Scanner input = new Scanner(System.in);

        System.out.println("Nama : ");
        String inputNama = input.nextLine();
        System.out.println("Jenis Kelamin (L/P) : ");
        String inputKel = input.next();
        System.out.println("Tanggal Lahir (yyyy-mm-dd) : ");
        String inputUmur = input.next();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate tanggalLahir = LocalDate.parse(inputUmur, formatter);
        LocalDate curDate = LocalDate.now();

        Period umur = Period.between(tanggalLahir, curDate);

        System.out.format("Nama : %s %n", inputNama);
        if (inputKel.equals("L") || inputKel.equals("l")) {
            System.out.println("Jenis Kelamin : Laki-Laki");
        }else if (inputKel.equals("P") || inputKel.equals("p")) {
            System.out.println("Jenis Kelamin : Perempuan");
        }else {
            System.out.println("Jenis Kelamin : Salah");
        }
        System.out.printf("Umur Anda : %d tahun %d bulan dan %d hari.", umur.getYears(), umur.getMonths(), umur.getDays());  


    }
}
