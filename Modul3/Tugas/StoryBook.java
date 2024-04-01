import java.util.Scanner;

public class StoryBook extends Book {

    private final String category;

    public StoryBook(String id, String title, String author, String category, int stock) {
        super(id, title, author, "Story", stock);
        this.category = category;
    }
    public static StoryBook createStoryBook(Scanner scanner) { String id;
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


        return new StoryBook(id, title, author, "Story", stock);
    }

    @Override
    public String getCategory() {
        return category;
    }
}