import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {
    // BUILD A BUBBLE SORT NUMBER FROM USER INPUT AND PRINT OUT THE RESULT
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Banyak Data : ");
        int n = input.nextInt();
        int[] arr = new int[n];
        System.out.println("Masukkan Data : ");
        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        System.out.println("Data : " + Arrays.toString(arr));
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Hasil : " + Arrays.toString(arr));
    }
}
