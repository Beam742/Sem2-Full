import java.util.ArrayList;
import java.util.Scanner;

class Codelab {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> mahasiswa = new ArrayList<>();

        int i = 1;
        while (true) {
            System.out.print("Masukkan nama ke-" + i + ":");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("selesai")) {
                System.out.println("Daftar mahasiswa yang diinputkan :");
                for (int n = 0; n < mahasiswa.size(); n++) {
                    System.err.println("- " + mahasiswa.get(n));
                }
                break;
            }

            try {
                validateInput(input);
                mahasiswa.add(input);
                i++;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public static boolean validateInput(String input) {
        if (input.length() == 0) {
            throw new IllegalArgumentException("Input tidak boleh kosong");
        }

        return true;
    }
}