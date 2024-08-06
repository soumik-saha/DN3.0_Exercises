public class LinearSearch {
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // Book not found
    }

    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "Java Programming", "Soumik Saha"),
                new Book(2, "Data Structures", "Parthiv Modak"),
                new Book(3, "Algorithms", "Pankaj Goel")
        };

        Book result = linearSearch(books, "Data Structures");
        if (result != null) {
            System.out.println("Book found: " + result.getTitle() + " by " + result.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }
}
