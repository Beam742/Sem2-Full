import java.util.ArrayList;
import java.util.Scanner;
public class User {


    private final ArrayList<Book> books = new ArrayList<>();



    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        int bookTypeChoice;

        do {
            System.out.println("\nChoose book type to add:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Enter choice (1-3): ");
            try {
                bookTypeChoice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine();
                bookTypeChoice = 0;
            }
        } while (bookTypeChoice < 1 || bookTypeChoice > 3);

        Book newBook;
        switch (bookTypeChoice) {
            case 1:
                newBook = HistoryBook.createHistoryBook(scanner);
                break;
            case 2:
                newBook = StoryBook.createStoryBook(scanner);
                break;
            case 3:
                newBook = TextBook.createTextBook(scanner);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        Main.books.add(newBook);
        System.out.println("\nBook added successfully:");
        System.out.println(newBook);
    }

    public void displayBook() {

        System.out.println("\nAvailable Books:");
        System.out.printf("%-10s %-20s %-20s %-20s %-10s\n", "ID", "Title", "Author", "Category", "Stock");
        for (Book book : Main.books) {
            System.out.printf("%-10s %-20s %-20s %-20s %-10d\n", book.getId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock());
        }
    }



}