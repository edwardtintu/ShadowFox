import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {

    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Contact Management System ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.println("6. Search Contact");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n----------------------");
                    addContact();
                    System.out.println("Operation completed.\n");
                    break;
                case 2:
                    System.out.println("\n----------------------");
                    viewContacts();
                    System.out.println("Operation completed.\n");
                    break;
                case 3:
                    System.out.println("\n----------------------");
                    updateContact();
                    System.out.println("Operation completed.\n");
                    break;
                case 4:
                    System.out.println("\n----------------------");
                    deleteContact();
                    System.out.println("Operation completed.\n");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                case 6:
                    System.out.println("\n----------------------");
                    searchContact();
                    System.out.println("Operation completed.\n");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addContact() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        for (Contact c : contacts) {
            if (c.getPhone().equals(phone)) {
                System.out.println("Contact with this phone already exists!");
                return;
            }
        }

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added!");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("Contact " + (i + 1));
            contacts.get(i).display();
        }
    }

    private static void updateContact() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        
        viewContacts();

        System.out.print("Enter contact number to update: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();

        if (index >= 0 && index < contacts.size()) {
            System.out.print("Enter new name: ");
            contacts.get(index).setName(sc.nextLine());

            System.out.print("Enter new phone: ");
            contacts.get(index).setPhone(sc.nextLine());

            System.out.print("Enter new email: ");
            contacts.get(index).setEmail(sc.nextLine());

            System.out.println("Contact updated!");
        } else {
            System.out.println("Invalid contact number!");
        }
    }

    private static void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }
        
        viewContacts();

        System.out.print("Enter contact number to delete: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();

        if (index >= 0 && index < contacts.size()) {
            contacts.remove(index);
            System.out.println("Contact deleted!");
        } else {
            System.out.println("Invalid contact number!");
        }
    }

    private static void searchContact() {
        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine();

        boolean found = false;

        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(searchName)) {
                c.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No contact found with that name.");
        }
    }
}
