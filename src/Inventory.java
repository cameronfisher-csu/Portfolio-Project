
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Book> mainInventory;
    private ArrayList<Book> lendingInventory;

    // Constructor
    public Inventory() {
        mainInventory = new ArrayList<>();
        lendingInventory = new ArrayList<>();
    }

    // Check whether an ID already exists
    private boolean idExists(int id) {
        for (Book book : mainInventory) {
            if (book.getId() == id) {
                return true;
            }
        }

        for (Book book : lendingInventory) {
            if (book.getId() == id) {
                return true;
            }
        }

        return false;
    }

    // Add a new book
    public boolean addBook(int id, String title, String author,
                           String isbn, int numberOfPages) {
        if (idExists(id)) {
            System.out.println("A book with this ID already exists.");
            return false;
        }

        Book book = new Book(id, title, author, isbn, numberOfPages);
        mainInventory.add(book);

        System.out.println("Book added to the library.");
        return true;
    }

    // Borrow a book
    public boolean borrowBook(int id) {
        for (int i = 0; i < mainInventory.size(); i++) {
            Book book = mainInventory.get(i);

            if (book.getId() == id) {
                mainInventory.remove(book);
                lendingInventory.add(book);

                System.out.println("Book successfully borrowed.");
                return true;
            }
        }

        System.out.println(
                "Book not found or already borrowed."
        );
        return false;
    }

    // Return a borrowed book
    public boolean returnBook(int id) {
        for (int i = 0; i < lendingInventory.size(); i++) {
            Book book = lendingInventory.get(i);

            if (book.getId() == id) {
                lendingInventory.remove(i);
                mainInventory.add(book);

                System.out.println("Book successfully returned.");
                return true;
            }
        }

        System.out.println("Book is not in the lending inventory.");
        return false;
    }

    // Print all available books
    public void printAll() {
        if (mainInventory.isEmpty()) {
            System.out.println("No books are currently available.");
            return;
        } else {
            System.out.println("\nAvailable Books:");
            for (Book book : mainInventory) {
                book.printBookInfo();
                System.out.println("----------");
            }
        }
    }

    // Search available books by full or partial title
    public void searchByTitle(String title) {
        boolean found = false;
        int booksFound = 0;

        for (Book book : mainInventory) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                booksFound++;
                System.out.println("Book Found " + booksFound + ": ");
                book.printBookInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    // Return the number of available books
    public int getMainInventoryCount() {
        return mainInventory.size();
    }

    // Return the number of borrowed books
    public int getLendingInventoryCount() {
        return lendingInventory.size();
    }
}
