import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {

    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:library.db";
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "title TEXT NOT NULL," +
                     "author TEXT NOT NULL" +
                     ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table created or already exists.");

        } catch (Exception e) {
            System.out.println("Error creating table!");
            e.printStackTrace();
        }
    }

    public static void addBook(String title, String author) {

        String sql = "INSERT INTO books(title, author) VALUES(?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.executeUpdate();

            System.out.println("Book added successfully!");

        } catch (Exception e) {
            System.out.println("Error adding book!");
        }
    }

    public static void viewBooks() {

        String sql = "SELECT * FROM books";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Book List ---");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("title") + " | " +
                    rs.getString("author")
                );
            }

        } catch (Exception e) {
            System.out.println("Error retrieving books!");
        }
    }

    public static void deleteBook(int id) {

        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("Book not found!");
            }

        } catch (Exception e) {
            System.out.println("Error deleting book!");
        }
    }

    public static void main(String[] args) {
        createTable();
        addBook("Java Basics", "James Gosling");
        viewBooks();
    }
}
