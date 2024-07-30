import java.util.Arrays;

public class BinarySearch {
    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
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

        Arrays.sort(books, (b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()));

        Book result = binarySearch(books, "Algorithms");
        if (result != null) {
            System.out.println("Book found: " + result.getTitle() + " by " + result.getAuthor());
        } else {
            System.out.println("Book not found.");
        }
    }
}
