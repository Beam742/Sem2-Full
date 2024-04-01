import java.util.Scanner;

public class HistoryBook extends Book {

    private final String category;

    public HistoryBook(String id, String title, String author, String category, int stock) {
        super(id, title, author, "History", stock);
        this.category = category;
    }

    public static HistoryBook createHistoryBook(Scanner scanner) {
        String id;
        do {
            System.out.print("Enter book Id:");
            id = scanner.next();
        } while (id.isEmpty());
        System.out.print("Enter book title: ");
        String title = scanner.next();
        System.out.print("Enter author: ");
        String author = scanner.next();
        System.out.print("Enter the stock: ");
        int stock = scanner.nextInt();

        return new HistoryBook(id, title, author, "History", stock);
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return """
        *History Book*
        ID: """ + getId() + "\n" +
                "Title: " + getTitle() + "\n" +
                "Author: " + getAuthor() + "\n" +
                "Stock: " + getStock() + "\n" +
                "Specific Category: " + category;
    }
}