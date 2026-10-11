import java.util.Scanner;

public class Main {

    // Read an integer and handle invalid input
    public static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Please enter a whole number."
                );
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();

        boolean running = true;

        while (running) {
            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search by Title");
            System.out.println("5. Print All Books");
            System.out.println("6. Exit");

            int choice = readInt(input, "Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        int id = readInt(input, "Enter book ID: ");

                        System.out.print("Enter book title: ");
                        String title = input.nextLine();

                        System.out.print("Enter author: ");
                        String author = input.nextLine();

                        System.out.print("Enter ISBN number: ");
                        String isbn = input.nextLine();

                        int pages = readInt(
                                input, "Enter number of pages: "
                        );

                        if (id <= 0 || pages <= 0
                                || title.trim().isEmpty()
                                || author.trim().isEmpty()
                                || isbn.trim().isEmpty()) {
                            System.out.println(
                                    "Invalid book details. "
                                            + "ID and pages must be positive, "
                                            + "and text fields cannot be empty."
                            );
                        } else {
                            inventory.addBook(
                                    id, title, author, isbn, pages
                            );
                        }
                        break;

                    case 2:
                        int borrowId = readInt(
                                input, "Enter book ID to borrow: "
                        );
                        inventory.borrowBook(borrowId);
                        break;

                    case 3:
                        int returnId = readInt(
                                input, "Enter book ID to return: "
                        );
                        inventory.returnBook(returnId);
                        break;

                    case 4:
                        System.out.print(
                                "Enter full or partial book title: "
                        );
                        String searchTitle = input.nextLine().trim();

                        if (searchTitle.isEmpty()) {
                            System.out.println(
                                    "Please enter a title to search."
                            );
                        } else {
                            inventory.searchByTitle(searchTitle);
                        }
                        break;

                    case 5:
                        inventory.printAll();
                        System.out.println(
                                "Available copies: "
                                        + inventory.getMainInventoryCount()
                        );
                        System.out.println(
                                "Borrowed copies: "
                                        + inventory.getLendingInventoryCount()
                        );
                        break;

                    case 6:
                        running = false;
                        System.out.println(
                                "Exiting the program. Goodbye!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid option. Please select a number 1 through 6."
                        );
                }
            } catch (Exception e) {
                System.out.println(
                        "An error occurred: " + e.getMessage()
                );
            }
        }

        input.close();
    }
}
