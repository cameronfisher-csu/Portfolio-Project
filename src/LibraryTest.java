import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Inventory inventory;
    private ByteArrayOutputStream output;
    private PrintStream originalOutput;

    // Set up before each test
    @BeforeEach
    public void setUp() {
        inventory = new Inventory();

        // Save the original console output
        originalOutput = System.out;

        // Capture console output for testing
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    // Restore console output after each test
    @AfterEach
    public void restoreOutput() {
        System.setOut(originalOutput);
    }

    // Test 1: Add a book successfully
    @Test
    public void testAddBook() {
        boolean result = inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        assertTrue(result);
        assertEquals(1, inventory.getMainInventoryCount());
        assertEquals(0, inventory.getLendingInventoryCount());
    }

    // Test 2: Reject a duplicate book ID
    @Test
    public void testDuplicateBookId() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        boolean result = inventory.addBook(
                1, "Python Programming", "Jane Doe",
                "0987654321", 250
        );

        assertFalse(result);
        assertEquals(1, inventory.getMainInventoryCount());
    }

    // Test 3: Borrow an available book
    @Test
    public void testBorrowBook() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        boolean result = inventory.borrowBook(1);

        assertTrue(result);
        assertEquals(0, inventory.getMainInventoryCount());
        assertEquals(1, inventory.getLendingInventoryCount());
    }

    // Test 4: Reject borrowing an already borrowed book
    @Test
    public void testBorrowAlreadyBorrowedBook() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        inventory.borrowBook(1);
        boolean result = inventory.borrowBook(1);

        assertFalse(result);
        assertEquals(0, inventory.getMainInventoryCount());
        assertEquals(1, inventory.getLendingInventoryCount());
    }

    // Test 5: Reject borrowing a nonexistent book
    @Test
    public void testBorrowNonexistentBook() {
        boolean result = inventory.borrowBook(99);

        assertFalse(result);
        assertEquals(0, inventory.getMainInventoryCount());
        assertEquals(0, inventory.getLendingInventoryCount());
    }

    // Test 6: Return a borrowed book
    @Test
    public void testReturnBook() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        inventory.borrowBook(1);
        boolean result = inventory.returnBook(1);

        assertTrue(result);
        assertEquals(1, inventory.getMainInventoryCount());
        assertEquals(0, inventory.getLendingInventoryCount());
    }

    // Test 7: Reject returning a book that was not borrowed
    @Test
    public void testReturnUnborrowedBook() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        boolean result = inventory.returnBook(1);

        assertFalse(result);
        assertEquals(1, inventory.getMainInventoryCount());
        assertEquals(0, inventory.getLendingInventoryCount());
    }

    // Test 8: Search by partial title, ignoring case
    @Test
    public void testSearchByPartialTitle() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        inventory.addBook(
                2, "Introduction to Java", "Jane Doe",
                "0987654321", 250
        );

        inventory.searchByTitle("java");

        String result = output.toString();

        assertTrue(result.contains("Java Programming"));
        assertTrue(result.contains("Introduction to Java"));
    }

    // Test 9: Display a message when no title matches
    @Test
    public void testSearchNoMatch() {
        inventory.addBook(
                1, "Java Programming", "John Smith",
                "1234567890", 350
        );

        inventory.searchByTitle("History");

        assertTrue(
                output.toString().contains("No matching book found.")
        );
    }

    // Test 10: Verify book information and getters
    @Test
    public void testBookInformation() {
        Book book = new Book(
                3, "Computer Science", "Alex Brown",
                "2222222222", 400
        );

        assertEquals(3, book.getId());
        assertEquals("Computer Science", book.getTitle());
        assertEquals("Alex Brown", book.getAuthor());
        assertEquals("2222222222", book.getIsbn());
        assertEquals(400, book.getNumberOfPages());
    }
}
