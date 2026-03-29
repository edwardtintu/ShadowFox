import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Ensure table exists
        DBConnection.createTable();

        try {
            while (true) {
                System.out.println("\n--- Library Menu ---");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Delete Book");
                System.out.println("4. Exit");
                System.out.print("Choose: ");

                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); // discard bad input
                    continue;
                }

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {

                    case 1:
                        System.out.print("Enter title: ");
                        String title = sc.nextLine();

                        System.out.print("Enter author: ");
                        String author = sc.nextLine();

                        DBConnection.addBook(title, author);
                        break;

                    case 2:
                        DBConnection.viewBooks();
                        break;

                    case 3:
                        System.out.print("Enter book ID to delete: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid ID.");
                            sc.nextLine();
                            break;
                        }
                        int id = sc.nextInt();
                        DBConnection.deleteBook(id);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice! Please enter 1-4.");
                }
            }
        } finally {
            sc.close();
        }
    }
}
