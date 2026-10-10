
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int numberOfPages;

    // Default constructor
    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.numberOfPages = 0;
    }

    // Constructor
    public Book(int id, String title, String author, String isbn, int numberOfPages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    // Print book information
    public void printBookInfo() {
        System.out.println("Book ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Number of Pages: " + numberOfPages);
    }
}
