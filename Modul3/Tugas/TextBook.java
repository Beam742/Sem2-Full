import java.util.Scanner;

public class TextBook extends Book {

    private final String category;

    public TextBook(String id, String title, String author, String category, int stock) {
        super(id, title, author, "", stock);
        this.category = category;
    }

    public static TextBook createTextBook(Scanner scanner) {
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

        return new TextBook(id, title, author, "Story", stock);
    }
    @Override
    public String getCategory() {
        return category;
    }
    @Override
    public String toString() {
        return """
           *Textbook*
           ID: """ + getId() + "\n" +
                "Judul: " + getTitle() + "\n" +
                "Penulis: " + getAuthor() + "\n" +
                "Kategori: " + category + "\n" +
                "Stok: " + getStock();
    }
}