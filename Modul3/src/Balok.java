import java.util.Scanner;

public class Balok extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double panjang, lebar, tinggi;

    public Balok(String nameBangun) {
        super(nameBangun);
    }

    @Override
    public void inputNilai() {
        super.inputNilai();
        System.out.print("Input Panjang: ");
        panjang = scanner.nextDouble();
        System.out.print("Input Lebar: ");
        lebar = scanner.nextDouble();
        System.out.print("Input Tinggi: ");
        tinggi = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan() {
        double hasil = 2 * ((panjang * lebar) + (lebar * tinggi) + (panjang * tinggi));
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume() {
        double hasil = panjang * lebar * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}
